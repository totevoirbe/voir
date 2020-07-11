package be.panidel.dataLayer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.DataModelInterface;
import be.panidel.dataLayer.model.ItemPaymentModel;
import be.panidel.dataLayer.model.PaymentMethodModel;

public class ItemPaymentDao extends GenericDaoImpl<ItemPaymentModel> {

	private final static Logger LOG = LoggerFactory.getLogger(ItemProductDao.class);

	/*
	 * The ID is generated at storage level. Check if item exist is not possible
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dataLayer.dao.GenericDao#write(java.lang.Object,
	 * javax.persistence.EntityManager)
	 */
	@Override
	public ItemPaymentModel write(ItemPaymentModel itemPayment, EntityManager em) {

		PaymentMethodModel paymentMethod = itemPayment.getPaymentMethod();

		if (paymentMethod == null) {
			LOG.error("Product not found : " + paymentMethod);
		}

		super.persist(itemPayment, em);

		return itemPayment;

	}

	@Override
	public List<ItemPaymentModel> findAll(EntityManager em) {

		return em.createNamedQuery(DataModelInterface.POS_ITEMPAYMENT_ALL, ItemPaymentModel.class).getResultList();

	}

	@Override
	public void populate(ItemPaymentModel persistedEntity, ItemPaymentModel entity) {
		throw new IllegalAccessError("Item is invariable");
	}

}
