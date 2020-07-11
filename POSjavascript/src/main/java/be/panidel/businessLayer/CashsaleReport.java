package be.panidel.businessLayer;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.businessLayer.helper.EnumHelper.PeriodType;
import be.panidel.businessLayer.model.CashSaleReportModel;
import be.panidel.businessLayer.model.ItemKey;
import be.panidel.businessLayer.model.ItemPaymentReportModel;
import be.panidel.businessLayer.model.ItemProductReportModel;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.PourcentHelper;
import be.panidel.dataLayer.helper.SysHelper;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.ItemPaymentModel;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;

public class CashsaleReport {

	private final static Logger LOG = LoggerFactory.getLogger(CashsaleReport.class);

	public static Map<Date, CashSaleReportModel> mergeCashSalesAsReport(ReportPeriod reportPeriod,
			PeriodType subPeriodType) throws DataLayerException {

		Map<Date, CashSaleReportModel> periodCashSaleReports = new TreeMap<>();

		List<CashSaleModel> cashSales = DataFacade.instance.getCashSalesBetween(reportPeriod.getBeginTime(),
				reportPeriod.getEndTime(), -1);

		LOG.info("Cashsale size [FROM/TO included] : " + cashSales.size() + "["
				+ SimpleDateFormat.getDateInstance().format(reportPeriod.getBeginTime()) + "/"
				+ SimpleDateFormat.getDateInstance().format(reportPeriod.getEndTime()) + "/ ["
				+ reportPeriod.getPeriodType() + "]]");

		PourcentHelper pourcentHelper = new PourcentHelper((cashSales.size() > 0 ? cashSales.size() : 1),
				"[mergeCashSalesAsReport]", LOG);

		for (CashSaleModel cashSale : cashSales) {

			if (CashSaleStatus.DONE == cashSale.getCashSaleStatus()) {

				Date keyDate = ReportPeriod.getKeyDate(cashSale.getEndDate(), subPeriodType);
				ReportPeriod subReportPeriod = new ReportPeriod(cashSale.getEndDate(), subPeriodType);

				CashSaleReportModel cashSaleReport = periodCashSaleReports.get(keyDate);

				if (cashSaleReport == null) {

					String id = subPeriodType.getDateFormat().format(cashSale.getEndDate());
					BigDecimal cashSaleTotal = BigDecimal.ZERO;
					BigDecimal cashSaleExcludVAT = BigDecimal.ZERO;
					BigDecimal cashSaleDeducedExcludVAT = BigDecimal.ZERO;
					BigDecimal cashSaleFree = BigDecimal.ZERO;
					BigDecimal cashSaleLost = BigDecimal.ZERO;
					BigDecimal cashSaleTrash = BigDecimal.ZERO;
					BigDecimal cashSaleCancelled = BigDecimal.ZERO;
					BigDecimal cashSalePaymentTotal = BigDecimal.ZERO;
					BigDecimal cashSaleNbArticles = BigDecimal.ZERO;

					Map<ItemKey, ItemProductReportModel> itemReportProducts = null;
					Map<ItemKey, ItemPaymentReportModel> itemReportPayments = null;
					Map<ItemKey, BigDecimal> vatSplit = new TreeMap<>();

					List<VatRateModel> vatRates = DataFacade.instance.getAllVatRates(false);

					for (VatRateModel vatRate : vatRates) {
						ItemKey itemKey = new ItemKey(vatRate.getId());
						vatSplit.put(itemKey, BigDecimal.ZERO);
					}

					cashSaleReport = new CashSaleReportModel(id, cashSaleTotal, cashSaleExcludVAT,
							cashSaleDeducedExcludVAT, cashSaleFree, cashSaleLost, cashSaleTrash, cashSaleCancelled,
							cashSalePaymentTotal, cashSaleNbArticles, subReportPeriod, itemReportProducts,
							itemReportPayments, vatSplit);

					periodCashSaleReports.put(keyDate, cashSaleReport);

				}

				cashSaleReport = mapCashSaleReport(cashSaleReport, cashSale, reportPeriod);

				LOG.debug("New Period cashsale " + cashSaleReport);

			}

			long warnLevel = 95;
			long errorLevel = 98;
			String message = "mergeCashSaleReport";

			SysHelper.checkMemoryLevelAndWarn(warnLevel, errorLevel, message);

			pourcentHelper.increment(1);

		}

		return periodCashSaleReports;
	}

	public static CashSaleReportModel mapCashSaleReport(CashSaleReportModel periodCashSaleReport,
			CashSaleModel cashSale, ReportPeriod reportPeriodParam) {

		if (CashSaleStatus.DONE.equals(cashSale.getCashSaleStatus())) {

			if (cashSale.getCashSaleTotal() != null) {
				periodCashSaleReport
						.setCashSaleTotal(periodCashSaleReport.getCashSaleTotal().add(cashSale.getCashSaleTotal()));
			}
			if (cashSale.getCashSaleExcludVAT() != null) {
				periodCashSaleReport.setCashSaleExcludVAT(
						periodCashSaleReport.getCashSaleExcludVAT().add(cashSale.getCashSaleExcludVAT()));
			}
			if (cashSale.getCashSaleDeducedExcludVAT() != null) {
				periodCashSaleReport.setCashSaleDeducedExcludVAT(
						periodCashSaleReport.getCashSaleDeducedExcludVAT().add(cashSale.getCashSaleDeducedExcludVAT()));
			}
			if (cashSale.getCashSaleFree() != null) {
				periodCashSaleReport
						.setCashSaleFree(periodCashSaleReport.getCashSaleFree().add(cashSale.getCashSaleFree()));
			}
			if (cashSale.getCashSaleLost() != null) {
				periodCashSaleReport
						.setCashSaleLost(periodCashSaleReport.getCashSaleLost().add(cashSale.getCashSaleLost()));
			}
			if (cashSale.getCashSaleTrash() != null) {
				periodCashSaleReport
						.setCashSaleTrash(periodCashSaleReport.getCashSaleTrash().add(cashSale.getCashSaleTrash()));
			}
			if (cashSale.getCashSaleCancelled() != null) {
				periodCashSaleReport.setCashSaleCancelled(
						periodCashSaleReport.getCashSaleCancelled().add(cashSale.getCashSaleCancelled()));
			}
			if (cashSale.getCashSalePaymentTotal() != null) {
				periodCashSaleReport.setCashSalePaymentTotal(
						periodCashSaleReport.getCashSalePaymentTotal().add(cashSale.getCashSalePaymentTotal()));
			}
			if (cashSale.getCashSaleNbArticles() != null) {
				periodCashSaleReport.setCashSaleNbArticles(
						periodCashSaleReport.getCashSaleNbArticles().add(cashSale.getCashSaleNbArticles()));
			}
			LOG.debug("Updated Period cashsale : " + periodCashSaleReport);
		} else {
			LOG.debug("Period cashsale not done : " + periodCashSaleReport);
		}

		Map<ItemKey, ItemProductReportModel> itemReportProducts = periodCashSaleReport.getItemReportProducts();

		if (cashSale.getItemProducts() != null) {

			if (itemReportProducts == null) {
				itemReportProducts = new HashMap<>();
				periodCashSaleReport.setItemReportProducts(itemReportProducts);
			}

			for (ItemProductModel itemProduct : cashSale.getItemProducts()) {
				if (itemProduct.getDeleted() == null || !itemProduct.getDeleted()) {
					ItemKey itemKey = new ItemKey(itemProduct.getProduct().getId());
					ItemProductReportModel newItemProduct = null;
					newItemProduct = itemReportProducts.get(itemKey);
					BigDecimal nbreProduits = itemProduct.getQuantity();
					BigDecimal total = itemProduct.getQuantity().multiply(itemProduct.getUnitPrice());
					if (newItemProduct == null) {
						ProductModel product = itemProduct.getProduct();
						newItemProduct = new ItemProductReportModel(product, nbreProduits, total);
						itemReportProducts.put(itemKey, newItemProduct);
					} else {
						newItemProduct.setNbreProduits(newItemProduct.getNbreProduits().add(nbreProduits));
						newItemProduct.setTotal(newItemProduct.getTotal().add(total));
					}
				} else {
					LOG.error("Quantity or unit price is null : " + itemProduct);
				}
			}
		}

		Map<ItemKey, ItemPaymentReportModel> itemReportPayments = periodCashSaleReport.getItemReportPayments();

		if (cashSale.getItemPayments() != null) {

			if (itemReportPayments == null) {
				itemReportPayments = new HashMap<>();
				periodCashSaleReport.setItemReportPayments(itemReportPayments);
			}

			for (ItemPaymentModel itemPayment : cashSale.getItemPayments()) {

				ItemKey itemKey = new ItemKey(itemPayment.getPaymentMethod().getId());
				ItemPaymentReportModel newItemPayment = null;

				if (itemPayment.getDeleted() == null || !itemPayment.getDeleted()) {
					if (itemPayment.getQuantity() != null && itemPayment.getUnitPrice() != null) {
						newItemPayment = itemReportPayments.get(itemKey);
						BigDecimal operation = itemPayment.getQuantity();
						BigDecimal total = itemPayment.getQuantity().multiply(itemPayment.getUnitPrice());
						if (newItemPayment == null) {
							PaymentMethodModel paymentMethod = itemPayment.getPaymentMethod();
							newItemPayment = new ItemPaymentReportModel(paymentMethod, operation, total);
							itemReportPayments.put(itemKey, newItemPayment);
						} else {
							newItemPayment.setOperation(newItemPayment.getOperation().add(operation));
							newItemPayment.setTotal(newItemPayment.getTotal().add(total));
						}
					} else {
						LOG.error("Quantity or unit price is null : " + itemPayment);
					}
				}
			}
		}

		Map<ItemKey, BigDecimal> vatSplit = periodCashSaleReport.getVatSplit();

		if (cashSale.getCashSaleExcludVAT() != null && BigDecimal.ZERO.compareTo(cashSale.getCashSaleExcludVAT()) < 0) {

			if (vatSplit == null) {
				vatSplit = new HashMap<>();
				periodCashSaleReport.setVatSplit(vatSplit);
			}

			for (ItemProductModel itemProduct : cashSale.getItemProducts()) {
				if (itemProduct.getDeleted() == null || !itemProduct.getDeleted()) {
					ItemKey itemKey = new ItemKey(itemProduct.getVatRate().getId());
					BigDecimal persistedIncludedVAT = null;
					persistedIncludedVAT = vatSplit.get(itemKey);
					BigDecimal includedVAT = itemProduct.getQuantity().multiply(itemProduct.getUnitPrice());
					if (persistedIncludedVAT == null) {
						persistedIncludedVAT = includedVAT;
					} else {

						persistedIncludedVAT = persistedIncludedVAT.add(includedVAT);
					}
					vatSplit.put(itemKey, persistedIncludedVAT);
				}
			}
		}

		return periodCashSaleReport;
	}
}
