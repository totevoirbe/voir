package be.panidel.pos10.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;
import be.panidel.pos10.model.ProductPos10;
import be.panidel.pos10.model.RefProductPos10;
import be.panidel.pos10.model.SubproductPos10;
import be.panidel.tarif.exception.IllegalTypeException;

public class ProductPos10Dao {

	private final static Logger LOG = LoggerFactory.getLogger(ProductPos10Dao.class);

	public static final ProductPos10Dao instance = new ProductPos10Dao();

	private Map<Integer, ProductPos10> product10CatalogById;

	public synchronized Map<Integer, ProductPos10> getProductCatalog()
			throws InvalidFormatException, IOException, IllegalTypeException {

		if (product10CatalogById == null) {

			List<RefProductPos10> refProductPos10s = XlsProductReader.parseXlsxfileForOldRefs();

			LOG.debug("" + refProductPos10s);

			product10CatalogById = new HashMap<>();

			for (RefProductPos10 refProductPos10 : refProductPos10s) {

				ProductPos10 product10 = null;

				try {

					product10 = getProductPos10(refProductPos10);

					if (product10 != null) {
						product10CatalogById.put(new Integer(product10.getId()), product10);
					} else {
						LOG.warn("refProductPos10 not found [" + refProductPos10 + "]");
					}

				} catch (Exception e) {
					LOG.error("refProductPos10 error : " + product10.getId());
				} finally {
					LOG.debug("Add product : " + refProductPos10);
				}
			}
		}

		return product10CatalogById;
	}

	public ProductPos10 getProductPos10(RefProductPos10 refProductPos10) throws DataLayerException {

		ItemProductModel itemProduct = getProduct(refProductPos10.getCode());

		ProductPos10 product10 = null;

		if (itemProduct != null && itemProduct.getProduct() != null) {

			ProductModel product = itemProduct.getProduct();
			BigDecimal productPrice = itemProduct.getUnitPrice();

			Integer prix = productPrice.multiply(DAOConfig.POS_MULTIPLIER).intValue();

			String id = refProductPos10.getId();
			String nom = product.getName();
			String description = product.getName();
			String code = refProductPos10.getCode();
			String htmlKeyLabel = product.getHtmlKeyLabel().replaceAll("<br>", ";");
			Integer prixachat = 0;

			Integer tvaTakeAway = product.getVatRateTakeAway().getRate().multiply(DAOConfig.POS_MULTIPLIER).intValue();
			Integer tvaTakeOnPlace = product.getVatRateOnPlace().getRate().multiply(DAOConfig.POS_MULTIPLIER)
					.intValue();
			String group = refProductPos10.getGroup();
			List<SubproductPos10> subproducts = null;

			product10 = new ProductPos10(id, code, nom, description, htmlKeyLabel, prixachat, prix, tvaTakeAway,
					tvaTakeOnPlace, group, subproducts);

		}

		return product10;

	}

	public ItemProductModel getProduct(String productCodePos10) throws DataLayerException {

		String code = productCodePos10;

		ProductModel product = DataFacade.instance.getProductByCode(code);
		BigDecimal quantity = null;
		BigDecimal unitPrice = null;
		VatRateModel vatRate = null;
		Boolean deleted = null;
		PriceCategory priceCategory = null;

		boolean couldBeFIT = code.endsWith("FIT");
		boolean couldBeGEANT = code.startsWith("G");
		boolean couldBeMINI = code.startsWith("M");

		if (product != null) {
			priceCategory = PriceCategory.SDWNORMAL;
			unitPrice = product.getNormal();
		} else if (couldBeFIT) {
			code = productCodePos10.substring(0, code.length() - 3);
			product = DataFacade.instance.getProductByCode(code);
			if (product != null) {
				priceCategory = PriceCategory.FITNORMAL;
				unitPrice = product.getFitnormal();
			} else {
				code = code.substring(1);
				product = DataFacade.instance.getProductByCode(code);
				if (product != null) {
					if (couldBeMINI) {
						priceCategory = PriceCategory.FITMINI;
						unitPrice = product.getFitmini();
					} else if (couldBeGEANT) {
						priceCategory = PriceCategory.FITGEANT;
						unitPrice = product.getFitgeant();
					}
				}
			}
		} else {
			code = productCodePos10.substring(1);
			product = DataFacade.instance.getProductByCode(code);
			if (product != null) {
				if (couldBeMINI) {
					priceCategory = PriceCategory.SDWMINI;
					unitPrice = product.getMini();
				} else if (couldBeGEANT) {
					priceCategory = PriceCategory.SDWGEANT;
					unitPrice = product.getGeant();
				}
			}
		}

		ItemProductModel itemProduct = new ItemProductModel(product, quantity, unitPrice, vatRate, deleted,
				priceCategory);

		return itemProduct;

	}

	public ProductPos10 getById(Integer id) throws DataLayerException {

		try {

			Map<Integer, ProductPos10> product10CatalogById = getProductCatalog();

			ProductPos10 productPos10 = null;

			if (product10CatalogById != null) {
				productPos10 = product10CatalogById.get(id);
				return productPos10;
			} else {
				LOG.error("ProductCatalog is null OR productCatalog.getProductsPos10() is null");
			}

			return productPos10;

		} catch (InvalidFormatException | IOException | IllegalTypeException e) {
			throw new DataLayerException(" not found" + id);
		}

	}

}
