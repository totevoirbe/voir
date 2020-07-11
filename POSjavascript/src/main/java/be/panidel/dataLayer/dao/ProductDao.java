package be.panidel.dataLayer.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.DataModelInterface;
import be.panidel.dataLayer.model.ProductModel;

public class ProductDao extends GenericDaoImpl<ProductModel> {

	private final static Logger LOG = LoggerFactory.getLogger(ProductDao.class);

	@Override
	public ProductModel write(ProductModel product, EntityManager em) {

		ProductModel persistedProduct = find(product.getId(), em);

		if (persistedProduct == null) {

			super.persist(product, em);

			persistedProduct = product;

			LOG.debug("Product entity created : " + persistedProduct);

		} else {

			populate(persistedProduct, product);

			LOG.debug("Product entity updated : " + persistedProduct);

		}

		return persistedProduct;

	}

	@Override
	public List<ProductModel> findAll(EntityManager em) {
		return em.createNamedQuery(DataModelInterface.POS_PRODUCT_ALL, ProductModel.class).getResultList();
	}

	public ProductModel getById(long id, EntityManager em) {

		List<ProductModel> products = em.createNamedQuery(DataModelInterface.POS_PRODUCT_BYID, ProductModel.class)
				.setParameter("id", id).getResultList();

		if (products != null && products.size() > 0) {
			if (products.size() > 1) {
				LOG.warn("Product number with id " + id + " is multiple : " + products.size());
			}
			return products.get(0);
		} else {
			return null;
		}
	}

	public ProductModel getByCode(String code, EntityManager em) {

		List<ProductModel> products = em.createNamedQuery(DataModelInterface.POS_PRODUCT_BYCODE, ProductModel.class)
				.setParameter("code", code).getResultList();

		if (products != null && products.size() > 0) {
			if (products.size() > 1) {
				LOG.warn("Product number with code " + code + " is multiple : " + products.size());
			}
			return products.get(0);
		} else {
			return null;
		}

	}

	void deleteAll(EntityManager em) {
		em.createNamedQuery(DataModelInterface.POS_PRODUCT_DELETE_ALL).executeUpdate();
	}

	public long count(EntityManager em) {

		long count = (long) em.createNamedQuery(DataModelInterface.POS_PRODUCT_COUNT).getSingleResult();

		return count;

	}

	@Override
	public void populate(ProductModel persistedProduct, ProductModel product) {

		persistedProduct.setId(product.getId());
		persistedProduct.setLabel(product.getLabel());
		persistedProduct.setTicketLabel(product.getTicketLabel());
		persistedProduct.setCode(product.getCode());
		persistedProduct.setName(product.getName());
		persistedProduct.setHtmlKeyLabel(product.getHtmlKeyLabel());
		persistedProduct.setType(product.getType());
		persistedProduct.setImage(product.getImage());
		persistedProduct.setVatRateOnPlace(product.getVatRateOnPlace());
		persistedProduct.setVatRateTakeAway(product.getVatRateTakeAway());
		persistedProduct.setMini(product.getMini());
		persistedProduct.setNormal(product.getNormal());
		persistedProduct.setGeant(product.getGeant());
		persistedProduct.setFitmini(product.getFitmini());
		persistedProduct.setFitnormal(product.getFitnormal());
		persistedProduct.setFitgeant(product.getFitgeant());
		persistedProduct.setWebDetail(product.getWebDetail());
		persistedProduct.setAfficheDetail(product.getAfficheDetail());
		persistedProduct.setCanMerge(product.getCanMerge());

	}

}
