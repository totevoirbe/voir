package be.panidel.businessLayer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.DataFacade.ConsumptionPlace;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.ModelHelper;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.ItemPaymentModel;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;
import be.panidel.frontLayer.model.CashSaleJsonModel;
import be.panidel.frontLayer.model.ItemPaymentJsonModel;
import be.panidel.frontLayer.model.ItemProductJsonModel;

public class CashSaleComputation {

	private final static Logger LOG = LoggerFactory.getLogger(CashSaleComputation.class);

	public CashSaleModel writeCashSale(CashSaleJsonModel cashSaleJson, EntityManager em) throws DataLayerException {

		Long id = cashSaleJson.getId();
		CashSaleStatus cashSaleStatus = cashSaleJson.getCashSaleStatus();
		Date openDate = cashSaleJson.getOpenDate();
		Date endDate = cashSaleJson.getEndDate();
		String identifier = cashSaleJson.getIdentifier();
		String source = cashSaleJson.getSource();
		ConsumptionPlace consumptionPlace = cashSaleJson.getConsumptionPlace();

		BigDecimal cashSaleTotal = null;
		BigDecimal cashSaleExcludVAT = null;
		BigDecimal cashSaleDeducedExcludVAT = null;
		BigDecimal cashSaleFree = null;
		BigDecimal cashSaleLost = null;
		BigDecimal cashSaleTrash = null;
		BigDecimal cashSaleCancelled = null;
		BigDecimal cashSalePaymentTotal = null;
		BigDecimal cashSaleNbArticles = null;

		Collection<ItemProductModel> itemProducts = null;
		Collection<ItemPaymentModel> itemPayments = null;

		CashSaleModel cashSale = new CashSaleModel(id, cashSaleStatus, openDate, endDate, identifier, source,
				consumptionPlace, cashSaleTotal, cashSaleExcludVAT, cashSaleDeducedExcludVAT, cashSaleFree,
				cashSaleLost, cashSaleTrash, cashSaleCancelled, cashSalePaymentTotal, cashSaleNbArticles, itemPayments,
				itemProducts);

		if (cashSaleJson.getItemProducts() != null) {

			cashSale.setItemProducts(new ArrayList<ItemProductModel>());

			for (ItemProductJsonModel itemProductJson : cashSaleJson.getItemProducts()) {

				try {

					ProductModel product = DataFacade.instance.getProductById(itemProductJson.getProductId());
					BigDecimal itemProductQuantity = itemProductJson.getQuantity();
					BigDecimal itemProductUnitPrice = itemProductJson.getUnitPrice();
					Boolean itemProductDeleted = itemProductJson.getDeleted();
					PriceCategory itemProductPriceCategory = itemProductJson.getPriceCategory();

					VatRateModel vatRate = null;

					ItemProductModel itemProduct = new ItemProductModel(product, itemProductQuantity,
							itemProductUnitPrice, vatRate, itemProductDeleted, itemProductPriceCategory);

					cashSale.getItemProducts().add(itemProduct);

					if (itemProduct.getQuantity() == null || itemProduct.getUnitPrice() == null) {
						LOG.error("item product null value : " + cashSale.getId() + "-" + itemProduct);
					} else {
						compute(cashSale, itemProduct);
					}

				} catch (Exception e) {
					LOG.warn("" + itemProductJson, e);
				}
			}
		}

		if (cashSaleJson.getItemPayments() != null) {

			cashSale.setItemPayments(new ArrayList<ItemPaymentModel>());

			for (ItemPaymentJsonModel itemPaymentJson : cashSaleJson.getItemPayments()) {

				try {

					PaymentMethodModel itemPaymentMethod = DataFacade.instance
							.getPaymentMethodById(itemPaymentJson.getPaymentMethod());
					BigDecimal itemPaymentQuantity = itemPaymentJson.getQuantity();
					BigDecimal itemPaymentUnitPrice = itemPaymentJson.getUnitPrice();
					Boolean itemPaymentDeleted = itemPaymentJson.getDeleted();

					ItemPaymentModel itemPayment = new ItemPaymentModel(itemPaymentMethod, itemPaymentQuantity,
							itemPaymentUnitPrice, itemPaymentDeleted);

					cashSale.getItemPayments().add(itemPayment);

					if (itemPayment.getQuantity() == null || itemPayment.getUnitPrice() == null) {
						LOG.error("item product null value : " + cashSale.getId() + "-" + itemPayment);
					} else {
						compute(cashSale, itemPayment);
					}

				} catch (Exception e) {
					LOG.warn("" + itemPaymentJson, e);
				}
			}
		}

		if (CashSaleStatus.DONE == cashSaleStatus) {

			BigDecimal cashSaleCheckTotal = cashSale.getCashSaleTotal();
			if (cashSaleCheckTotal == null) {
				cashSaleCheckTotal = BigDecimal.ZERO;
			}
			if (cashSale.getCashSaleFree() != null) {
				cashSaleCheckTotal = cashSaleCheckTotal.add(cashSale.getCashSaleFree());
			}
			if (cashSale.getCashSaleLost() != null) {
				cashSaleCheckTotal = cashSaleCheckTotal.add(cashSale.getCashSaleLost());
			}
			if (cashSale.getCashSaleTrash() != null) {
				cashSaleCheckTotal = cashSaleCheckTotal.add(cashSale.getCashSaleTrash());
			}

			if (cashSaleJson.getCashSaleTotal() == null) {
				LOG.debug("CashSale Total is null : " + cashSaleJson);
			}
			if (cashSaleJson.getCashSalePaymentTotal() == null) {
				LOG.debug("CashSale Payment Total is null : " + cashSaleJson);
			}

		}

		LOG.debug("CashSale : " + cashSale);

		CashSaleModel persistedCashSaleModel = DataFacade.instance.createCashSale(cashSale);

		return persistedCashSaleModel;
	}

	private static void compute(CashSaleModel cashSale, ItemProductModel itemProduct) {

		if (itemProduct.getDeleted() == null || !itemProduct.getDeleted()) {

			if (itemProduct.getProduct() == null) {
				LOG.error("item product not identified : " + itemProduct);
				return;
			}
			if (itemProduct.getQuantity() == null || itemProduct.getUnitPrice() == null) {
				LOG.error("Quatity or unit price nulle value : " + itemProduct);
				return;
			}

			VatRateModel vatRate = null;
			ProductModel product = itemProduct.getProduct();
			BigDecimal itemProductQuantity = itemProduct.getQuantity();
			BigDecimal itemTotal = itemProduct.getQuantity().multiply(itemProduct.getUnitPrice());

			switch (cashSale.getConsumptionPlace()) {

			case ON_PLACE:
				vatRate = product.getVatRateOnPlace();
				break;

			case TAKE_AWAY:
				vatRate = product.getVatRateTakeAway();
				break;

			}

			itemProduct.setVatRate(vatRate);

			if (cashSale.getCashSaleExcludVAT() == null) {
				cashSale.setCashSaleExcludVAT(BigDecimal.ZERO);
			}

			BigDecimal itemProductExludedVAT = computeNetValue(vatRate, itemTotal);
			cashSale.setCashSaleExcludVAT(cashSale.getCashSaleExcludVAT().add(itemProductExludedVAT));

			if (cashSale.getCashSaleNbArticles() == null) {
				cashSale.setCashSaleNbArticles(BigDecimal.ZERO);
			}
			cashSale.setCashSaleNbArticles(cashSale.getCashSaleNbArticles().add(itemProductQuantity));

			if (cashSale.getCashSaleTotal() == null) {
				cashSale.setCashSaleTotal(BigDecimal.ZERO);
			}
			cashSale.setCashSaleTotal(cashSale.getCashSaleTotal().add(itemTotal));

		}
	}

	public static BigDecimal computeNetValue(VatRateModel vatRate, BigDecimal includedVatValue) {

		BigDecimal factor = BigDecimal.ONE
				.add(vatRate.getRate().divide(new BigDecimal(100), 2, RoundingMode.HALF_EVEN));
		BigDecimal valueExcludedVAT = includedVatValue.divide(factor, 2, RoundingMode.HALF_EVEN);

		return valueExcludedVAT;

	}

	private static void compute(CashSaleModel cashSale, ItemPaymentModel itemPayment) {

		if (itemPayment.getDeleted() == null || !itemPayment.getDeleted()) {

			if (itemPayment.getPaymentMethod() == null) {
				LOG.error("itemPaymentMethod not identified : " + itemPayment);
				return;
			}
			if (itemPayment.getQuantity() == null || itemPayment.getUnitPrice() == null) {
				LOG.error("itemPaymentQuantity is null or itemPaymentUnitPrice is null " + itemPayment);
				return;
			}

			BigDecimal itemTotal = itemPayment.getQuantity().multiply(itemPayment.getUnitPrice());

			switch (itemPayment.getPaymentMethod().getPaymentMethodComputation()) {

			case FREE:

				if (cashSale.getCashSaleFree() == null) {
					cashSale.setCashSaleFree(BigDecimal.ZERO);
				}
				cashSale.setCashSaleFree(cashSale.getCashSaleFree().add(itemTotal));

				break;

			case ADD:

				if (cashSale.getCashSalePaymentTotal() == null) {
					cashSale.setCashSalePaymentTotal(BigDecimal.ZERO);
				}
				cashSale.setCashSalePaymentTotal(cashSale.getCashSalePaymentTotal().add(itemTotal));

				break;

			case LOST:

				if (cashSale.getCashSaleLost() == null) {
					cashSale.setCashSaleLost(BigDecimal.ZERO);
				}
				cashSale.setCashSaleLost(cashSale.getCashSaleLost().add(itemTotal));
				break;

			case TRASH:

				if (cashSale.getCashSaleTrash() == null) {
					cashSale.setCashSaleTrash(BigDecimal.ZERO);
				}
				cashSale.setCashSaleTrash(cashSale.getCashSaleTrash().add(itemTotal));
				break;

			}
		}
	}

	public static boolean checkCashSales(Collection<CashSaleModel> cashSales) {

		boolean checkResult = true;

		for (CashSaleModel cashSale : cashSales) {

			if (!checkCashSale(cashSale)) {
				checkResult = false;
			}
		}

		return checkResult;

	}

	public static boolean checkCashSale(CashSaleModel cashSale) {

		int numberOfProductItems = 0;
		int numberOfPaymentItems = 0;

		Long id = cashSale.getId();
		CashSaleStatus cashSaleStatus = CashSaleStatus.DONE;
		Date openDate = cashSale.getOpenDate();
		Date endDate = cashSale.getEndDate();
		String identifier = cashSale.getIdentifier();
		String source = cashSale.getSource();
		ConsumptionPlace consumptionPlace = cashSale.getConsumptionPlace();

		BigDecimal cashSaleTotal = null;
		BigDecimal cashSaleExcludVAT = null;
		BigDecimal cashSaleDeducedExcludVAT = null;
		BigDecimal cashSaleFree = null;
		BigDecimal cashSaleLost = null;
		BigDecimal cashSaleTrash = null;
		BigDecimal cashSaleCancelled = null;
		BigDecimal cashSalePaymentTotal = null;
		BigDecimal cashSaleNbArticles = null;

		CashSaleModel cashSaleToCompare = null;

		Collection<ItemProductModel> itemProducts = null;
		Collection<ItemPaymentModel> itemPayments = null;

		cashSaleToCompare = new CashSaleModel(id, cashSaleStatus, openDate, endDate, identifier, source,
				consumptionPlace, cashSaleTotal, cashSaleExcludVAT, cashSaleDeducedExcludVAT, cashSaleFree,
				cashSaleLost, cashSaleTrash, cashSaleCancelled, cashSalePaymentTotal, cashSaleNbArticles, itemPayments,
				itemProducts);

		if (cashSale.getItemProducts() != null) {
			for (ItemProductModel itemProduct : cashSale.getItemProducts()) {
				numberOfProductItems++;

				if (itemProduct.getQuantity() == null || itemProduct.getUnitPrice() == null) {
					LOG.error("item product null value : " + cashSale.getId() + "-" + itemProduct);
				} else {
					compute(cashSaleToCompare, itemProduct);
				}
			}
		}
		if (cashSale.getItemPayments() != null) {
			for (ItemPaymentModel itemPayment : cashSale.getItemPayments()) {
				numberOfPaymentItems++;

				if (itemPayment.getQuantity() == null || itemPayment.getUnitPrice() == null) {
					LOG.error("item product null value : " + cashSale.getId() + "-" + itemPayment);
				} else {
					compute(cashSaleToCompare, itemPayment);
				}
			}
		}

		String equalityMessage = checkEquality(cashSale, cashSaleToCompare);

		if (equalityMessage == null) {
			LOG.debug("-items:" + numberOfProductItems + "/" + numberOfPaymentItems + " / DONE : " + cashSale);
			return true;
		} else {
			LOG.error("-items:" + numberOfProductItems + "/" + numberOfPaymentItems + " / ERROR : " + equalityMessage
					+ " - " + cashSale);
			return false;
		}
	}

	public static String checkEquality(CashSaleModel cashSale, CashSaleModel cashSaleToCompare) {

		StringBuffer errorMessage = new StringBuffer();

		if (!ModelHelper.checkEquals(cashSale.getCashSaleTotal(), cashSaleToCompare.getCashSaleTotal())) {
			errorMessage.append(
					"/total[" + cashSale.getCashSaleTotal() + "<>" + cashSaleToCompare.getCashSaleTotal() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleExcludVAT(), cashSaleToCompare.getCashSaleExcludVAT())) {
			errorMessage.append(
					"/htva[" + cashSale.getCashSaleExcludVAT() + "<>" + cashSaleToCompare.getCashSaleExcludVAT() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleDeducedExcludVAT(),
				cashSaleToCompare.getCashSaleDeducedExcludVAT())) {
			errorMessage.append("/dedHtva[" + cashSale.getCashSaleDeducedExcludVAT() + "<>"
					+ cashSaleToCompare.getCashSaleDeducedExcludVAT() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleFree(), cashSaleToCompare.getCashSaleFree())) {
			errorMessage
					.append("/free[" + cashSale.getCashSaleFree() + "<>" + cashSaleToCompare.getCashSaleFree() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleLost(), cashSaleToCompare.getCashSaleLost())) {
			errorMessage
					.append("/lost[" + cashSale.getCashSaleLost() + "<>" + cashSaleToCompare.getCashSaleLost() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleTrash(), cashSaleToCompare.getCashSaleTrash())) {
			errorMessage.append(
					"/trash[" + cashSale.getCashSaleTrash() + "<>" + cashSaleToCompare.getCashSaleTrash() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleCancelled(), cashSaleToCompare.getCashSaleCancelled())) {
			errorMessage.append("/cancel[" + cashSale.getCashSaleCancelled() + "<>"
					+ cashSaleToCompare.getCashSaleCancelled() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSalePaymentTotal(), cashSaleToCompare.getCashSalePaymentTotal())) {
			errorMessage.append("/pay[" + cashSale.getCashSalePaymentTotal() + "<>"
					+ cashSaleToCompare.getCashSalePaymentTotal() + "]");
		}
		if (!ModelHelper.checkEquals(cashSale.getCashSaleNbArticles(), cashSaleToCompare.getCashSaleNbArticles())) {
			errorMessage.append("/nbArt[" + cashSale.getCashSaleNbArticles() + "<>"
					+ cashSaleToCompare.getCashSaleNbArticles() + "]");
		}

		if (errorMessage.length() > 0) {
			return errorMessage.toString();
		} else {
			return null;
		}

	}
}