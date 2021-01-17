package be.panidel.dao;

import java.math.BigDecimal;

import org.w3c.dom.Element;

import be.panidel.common.CoupleMessages;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.Product;
import be.panidel.management.impl.ProductItemImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.Tools;
import be.panidel.tools.XMLHelper;

public class ProductItemDAO implements ItemDAO {

	ProductItemDAO() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.ItemDAO#extract(org.w3c.dom.Element)
	 */
	public ProductItemImpl extract(Element itemNode) throws DAOException {

		ProductItemImpl productItem = null;

		String productId = null;
		BigDecimal quantity = null;
		String description = null;

		BigDecimal unitPrice = null;

		BigDecimal tvaTakeAway = null;
		BigDecimal tvaTakeOnPlace = null;

		try {
			productId = DOMUtils.getElementValueByTagName(
					TAG_SALES_ITEM_PRODUCT, itemNode);
			Product product = FacadeDAO.instance().getProductsDAO()
					.getById(productId);

			if (product == null) {
				throw new DAOException("Product is null : [" + productId + "]");
			}

			quantity = DOMUtils.getElementBigDecimalByTagName(
					TAG_SALES_ITEM_QUANTITY, itemNode);

			description = DOMUtils.getElementValueByTagName(
					TAG_SALES_ITEM_DESCRIPTION, itemNode);

			unitPrice = DOMUtils.getElementBigDecimalByTagName(
					TAG_SALES_ITEM_UNITPRICE, itemNode);

			tvaTakeAway = DOMUtils.getElementBigDecimalByTagName(
					TAG_SALES_ITEM_TVATAKEAWAY, itemNode);
			tvaTakeOnPlace = DOMUtils.getElementBigDecimalByTagName(
					TAG_SALES_ITEM_TVATAKEONPLACE, itemNode);

			if (Tools.isNullOrEmpty(description) && product != null) {
				description = product.getDescription();
			}

			productItem = new ProductItemImpl(product, description, quantity,
					unitPrice, tvaTakeAway, tvaTakeOnPlace);
		} catch (ParameterException e) {

			CoupleMessages cm = new CoupleMessages();
			cm.put("productId", productId);
			cm.put("quantity", quantity);
			cm.put("description", description);
			cm.put("unitPrice", unitPrice);
			cm.put("tvaTakeAway", tvaTakeAway);
			cm.put("tvaTakeOnPlace", tvaTakeOnPlace);

			throw new DAOException(cm.toString(), e);

		}

		return productItem;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.ItemDAO#append(be.panidel.management.Item,
	 * java.lang.StringBuffer)
	 */
	public void append(Item productItem, StringBuffer xml) throws DAOException {
		XMLHelper.openTag(TAG_SALES_ITEM, xml);
		XMLHelper.addTag(productItem.getQuantity()
				.multiply(new BigDecimal(100)).toPlainString(),
				TAG_SALES_ITEM_QUANTITY, xml);
		if (productItem.getItem() != null) {
			XMLHelper.addTag(productItem.getItem().getId(),
					TAG_SALES_ITEM_PRODUCT, xml);

		} else {
			throw new DAOException(productItem.getDescription()
					+ " item is null");
		}
		XMLHelper.addTag(productItem.getDescription(),
				TAG_SALES_ITEM_DESCRIPTION, xml);
		XMLHelper.addTag(
				productItem.getUnitPrice().multiply(new BigDecimal(100))
						.toPlainString(), TAG_SALES_ITEM_UNITPRICE, xml);
		XMLHelper.addTag(
				productItem.getTvaTakeAway().multiply(new BigDecimal(100))
						.toPlainString(), TAG_SALES_ITEM_TVATAKEAWAY, xml);
		XMLHelper.addTag(
				productItem.getTvaTakeOnPlace().multiply(new BigDecimal(100))
						.toPlainString(), TAG_SALES_ITEM_TVATAKEONPLACE, xml);
		XMLHelper.closeTag(TAG_SALES_ITEM, xml);
	}
}