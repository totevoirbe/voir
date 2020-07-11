package be.panidel.dataLayer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.DataModelInterface;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.ProductModel;

public class ItemProductDao extends GenericDaoImpl<ItemProductModel> {

	private final static Logger LOG = LoggerFactory.getLogger(ItemProductDao.class);

	/*
	 * The ID is generated at storage level. Check if item exist is not possible
	 * 
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dataLayer.dao.GenericDao#write(java.lang.Object,
	 * javax.persistence.EntityManager)
	 */
	@Override
	public ItemProductModel write(ItemProductModel itemProduct, EntityManager em) {

		ProductModel product = itemProduct.getProduct();

		if (product == null) {
			LOG.error("Product not found : " + itemProduct);
		}

		super.persist(itemProduct, em);

		return itemProduct;

	}

	@Override
	public List<ItemProductModel> findAll(EntityManager em) {

		return em.createNamedQuery(DataModelInterface.POS_ITEMPRODUCT_ALL, ItemProductModel.class).getResultList();

	}

	@Override
	public void populate(ItemProductModel persistedEntity, ItemProductModel entity) {
		throw new IllegalAccessError("Item is invariable");
	}

}