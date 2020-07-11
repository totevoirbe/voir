package be.panidel.businessLayer;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.frontLayer.model.CashSaleJsonModel;

public class BusinessFacade {

	private final static Logger LOG = LoggerFactory.getLogger(BusinessFacade.class);

	public static Collection<ProductModel> getProducts() {

		Collection<ProductModel> products = null;

		try {

			LOG.info("get products");

			products = DataFacade.instance.getAllProducts();

			LOG.info("return products : " + products.size());

		} catch (DataLayerException e) {
			LOG.error("", e);
		}

		return products;
	}

	public static Collection<PaymentMethodModel> getPaymentMethods() {
		Collection<PaymentMethodModel> paymentMethods = null;

		try {

			LOG.info("get payment methods");

			paymentMethods = DataFacade.instance.getAllPaymentMethod();

			LOG.info("return payment methods : " + paymentMethods.size());

		} catch (DataLayerException e) {
			LOG.error("", e);
		}
		return paymentMethods;
	}

	public static CashSaleModel getCashSale(CashSaleJsonModel cashSaleAsJson) throws DataLayerException {
		CashSaleModel saleModelRetValue = DataFacade.instance.getCashSale(cashSaleAsJson.getId());
		LOG.info("" + saleModelRetValue);

		return saleModelRetValue;
	}

}
