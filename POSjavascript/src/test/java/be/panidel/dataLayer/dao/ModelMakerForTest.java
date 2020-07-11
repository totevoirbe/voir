package be.panidel.dataLayer.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.DataFacade.ConsumptionPlace;
import be.panidel.dataLayer.DataFacade.PosIdentification;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.ItemPaymentModel;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;

public class ModelMakerForTest {

	public static long prefix_TIME = 0;
	public static String LAST_UUID_TIME = "";

	public static Date getMockDate() {
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.set(Calendar.YEAR, 1999);
		return gregorianCalendar.getTime();
	}

	/**
	 * @param date
	 * @param posIdentification
	 * @param quickGenerator
	 *            manage quick generated dates
	 * @return
	 */
	private static long getRandomUUID(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String UUIDTime = sdf.format(date) + PosIdentification.SERVER.getIdentifier();

		if (LAST_UUID_TIME.equals(UUIDTime)) {
			LAST_UUID_TIME = UUIDTime;
			prefix_TIME = prefix_TIME + 1;
			return new Long(prefix_TIME + UUIDTime);
		} else {
			LAST_UUID_TIME = UUIDTime;
			prefix_TIME = 0;
			return new Long(UUIDTime);
		}
	}

	public static CashSaleModel cashSaleMock() throws DataLayerException {

		Date currentDate = getMockDate();
		long id = getRandomUUID(currentDate);
		CashSaleStatus cashSaleStatus = CashSaleStatus.DONE;
		Date openDate = currentDate;
		Date endDate = currentDate;
		String identifier = "" + id;
		String source = "" + identifier;
		ConsumptionPlace consumptionPlace = ConsumptionPlace.TAKE_AWAY;
		List<ItemProductModel> itemProducts = new ArrayList<>();
		List<ItemPaymentModel> itemPayments = new ArrayList<>();

		BigDecimal cashSaleTotal = new BigDecimal("0.1");
		BigDecimal cashSaleExcludVAT = new BigDecimal("0.2");
		BigDecimal cashSaleDeducedExcludVAT = new BigDecimal("0.3");
		BigDecimal cashSaleFree = new BigDecimal("0.4");
		BigDecimal cashSaleLost = new BigDecimal("0.5");
		BigDecimal cashSaleTrash = new BigDecimal("0.6");
		BigDecimal cashSaleCancelled = new BigDecimal("0.7");
		BigDecimal cashSalePaymentTotal = new BigDecimal("0.8");
		BigDecimal cashSaleNbArticles = new BigDecimal("0.9");

		itemProducts.add(itemProductMock());

		CashSaleModel cashSale = new CashSaleModel(id, cashSaleStatus, openDate, endDate, identifier, source,
				consumptionPlace, cashSaleTotal, cashSaleExcludVAT, cashSaleDeducedExcludVAT, cashSaleFree,
				cashSaleLost, cashSaleTrash, cashSaleCancelled, cashSalePaymentTotal, cashSaleNbArticles, itemPayments,
				itemProducts);

		return cashSale;

	}

	public static ItemProductModel itemProductMock() throws DataLayerException {

		BigDecimal quantity = new BigDecimal("0.1");
		BigDecimal unitPrice = new BigDecimal("0.2");
		VatRateModel vatRate = DataFacade.instance.getVatRateByCode("1");
		Boolean deleted = false;
		PriceCategory priceCategory = PriceCategory.SDWNORMAL;
		ProductModel product = DataFacade.instance.getProductById(getRandomProductId());

		ItemProductModel itemProduct = new ItemProductModel(product, quantity, unitPrice, vatRate, deleted,
				priceCategory);
		return itemProduct;

	}

	public static ItemPaymentModel itemPaymentMock() throws DataLayerException {

		BigDecimal quantity = new BigDecimal("0.1");
		BigDecimal unitPrice = new BigDecimal("0.2");
		Boolean deleted = false;
		PaymentMethodModel paymentMethod = getRandomPaymentMethod();

		ItemPaymentModel itemPaymentModel = new ItemPaymentModel(paymentMethod, quantity, unitPrice, deleted);

		return itemPaymentModel;

	}

	public static Long getRandomProductId() throws DataLayerException {
		List<ProductModel> products = DataFacade.instance.getAllProducts();
		Double index = Math.random() * products.size();
		ProductModel product = products.get(index.intValue());
		return product.getId();
	}

	public static PaymentMethodModel getRandomPaymentMethod() throws DataLayerException {
		List<PaymentMethodModel> paymentMethods = DataFacade.instance.getAllPaymentMethod();
		Double index = Math.random() * paymentMethods.size();
		PaymentMethodModel paymentMethod = paymentMethods.get(index.intValue());
		return paymentMethod;
	}

}