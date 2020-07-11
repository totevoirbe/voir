package be.panidel.dataLayer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.DataModelInterface;
import be.panidel.dataLayer.model.PaymentMethodModel;

public class PaymentMethodDao extends GenericDaoImpl<PaymentMethodModel> {

	private final static Logger LOG = LoggerFactory.getLogger(PaymentMethodDao.class);

	@Override
	public PaymentMethodModel write(PaymentMethodModel paymentMethod, EntityManager em) {

		PaymentMethodModel persistedPaymentMethod = find(paymentMethod.getId(), em);

		if (persistedPaymentMethod == null) {

			super.persist(paymentMethod, em);

			persistedPaymentMethod = paymentMethod;

			LOG.debug("Payment method entity ceated : " + persistedPaymentMethod);

		} else {

			populate(persistedPaymentMethod, persistedPaymentMethod);

			LOG.debug("Payment method entity updated : " + persistedPaymentMethod);

		}

		return persistedPaymentMethod;

	}

	@Override
	public List<PaymentMethodModel> findAll(EntityManager em) {
		return em.createNamedQuery(DataModelInterface.POS_PAYMENTMETHOD_ALL, PaymentMethodModel.class).getResultList();
	}

	public PaymentMethodModel getById(long id, EntityManager em) {

		List<PaymentMethodModel> paymentMethods = em
				.createNamedQuery(DataModelInterface.POS_PAYMENTMETHOD_BYID, PaymentMethodModel.class)
				.setParameter("id", id).getResultList();

		if (paymentMethods != null && paymentMethods.size() > 0) {
			if (paymentMethods.size() > 1) {
				LOG.warn("Product number with id " + id + " is multiple : " + paymentMethods.size());
			}
			return paymentMethods.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void populate(PaymentMethodModel persistedPaymentMethod, PaymentMethodModel paymentMethod) {

		persistedPaymentMethod.setId(paymentMethod.getId());
		persistedPaymentMethod.setCode(paymentMethod.getCode());
		persistedPaymentMethod.setLabel(paymentMethod.getLabel());
		persistedPaymentMethod.setTicketLabel(paymentMethod.getTicketLabel());
		persistedPaymentMethod.setHtmlKeyLabel(paymentMethod.getHtmlKeyLabel());
		persistedPaymentMethod.setNeedSomeValue(paymentMethod.getNeedSomeValue());
		persistedPaymentMethod.setMaxQuantity(paymentMethod.getMaxQuantity());
		persistedPaymentMethod.setMaxTotalAmount(paymentMethod.getMaxTotalAmount());
		persistedPaymentMethod.setBeAlone(paymentMethod.getBeAlone());
		persistedPaymentMethod.setCanMerge(paymentMethod.getCanMerge());
		persistedPaymentMethod.setPaymentMethodComputation(paymentMethod.getPaymentMethodComputation());

	}

}
