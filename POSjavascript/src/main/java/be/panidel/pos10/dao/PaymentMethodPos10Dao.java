package be.panidel.pos10.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.helper.EnumHelper.PaymentMethodComputation;
import be.panidel.dataLayer.dao.MarshalHelper;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.pos10.model.PaymentMethodPos10;
import be.panidel.pos10.model.RootPaymentMethodsPos10;

public class PaymentMethodPos10Dao {

	private final static Logger LOG = LoggerFactory.getLogger(PaymentMethodPos10Dao.class);

	public static final PaymentMethodPos10Dao instance = new PaymentMethodPos10Dao();

	public static final File paymentMethodFile = new File(DAOConfig.RESOURCE_BASE + DAOConfig.POS10_PAYMENTMODE);

	private List<PaymentMethodModel> paymentMethods;

	public synchronized List<PaymentMethodModel> getPaymentMethodList() {

		LOG.debug("paymentMethodFile : " + paymentMethodFile.isFile() + "/" + paymentMethodFile.getAbsolutePath());

		if (paymentMethods == null) {

			try {

				RootPaymentMethodsPos10 rootPaymentMethodsPos10 = (RootPaymentMethodsPos10) MarshalHelper
						.unmarchalXml(RootPaymentMethodsPos10.class, paymentMethodFile);

				LOG.debug("" + rootPaymentMethodsPos10);

				paymentMethods = new ArrayList<>();

				for (PaymentMethodPos10 paymentMethodPos10 : rootPaymentMethodsPos10.getPayments().getPayment()) {

					Long id = paymentMethodPos10.getId();
					String code = paymentMethodPos10.getCode();
					String label = paymentMethodPos10.getDescription();
					String ticketLabel = paymentMethodPos10.getDescription();
					String htmlKeyLabel = paymentMethodPos10.getDescription();
					Boolean canMerge = paymentMethodPos10.getBeAlone();

					Boolean needSomeValue = paymentMethodPos10.getNeedSomeValue();
					BigDecimal maxQuantity = paymentMethodPos10.getMaxQuantity();
					BigDecimal maxTotalAmount = paymentMethodPos10.getMaxTotalAmount();
					Boolean beAlone = paymentMethodPos10.getBeAlone();
					PaymentMethodComputation paymentMethodComputation = paymentMethodPos10
							.getPaymentMethodComputation();

					PaymentMethodModel paymentMethod = new PaymentMethodModel(id, code, label, ticketLabel,
							htmlKeyLabel, needSomeValue, maxQuantity, maxTotalAmount, beAlone, canMerge,
							paymentMethodComputation);

					paymentMethods.add(paymentMethod);

				}

				LOG.debug("" + paymentMethods);

			} catch (JAXBException e) {
				LOG.error("", e);
			}

		}

		return paymentMethods;

	}

	public PaymentMethodModel getById(Long id) {

		getPaymentMethodList();

		for (PaymentMethodModel paymentMethod : paymentMethods) {
			if (paymentMethod.getId() != null && paymentMethod.getId().equals(id)) {
				return paymentMethod;
			}
		}

		return null;

	}

	public PaymentMethodModel getByCode(String code) {

		getPaymentMethodList();

		for (PaymentMethodModel paymentMethod : paymentMethods) {
			if (paymentMethod.getLabel() != null && paymentMethod.getLabel().equals(code)) {
				return paymentMethod;
			}
		}

		return null;

	}

}
