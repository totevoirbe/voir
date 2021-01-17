package be.panidel.dao;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Product;
import be.panidel.management.impl.ProductImpl;
import be.panidel.management.impl.SubProduct;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.Tools;

/**
 * @author franzph
 * 
 */
public class ProductsDAO extends DAOImpl {

	public final static String CANCEL = "-1";

	public final static String PRIXACHAT = "prixachat";
	public final static String PRIX = "prix";
	public final static String TVATAKEAWAY = "tvaTakeAway";
	public final static String TVATAKEONPLACE = "tvaTakeOnPlace";
	public final static String GROUP = "group";

	public final static String PRODUCTS = "products";
	public final static String PRODUCT = "product";

	public final static String SUBPRODUCTS = "subproducts";
	public final static String SUBPRODUCT = "subproduct";
	public final static String SUBPRODUCTQTY = "spq";
	public final static String SUBPRODUCTID = "spid";

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	@Override
	public Product getByCode(String code) throws DAOException {
		return (Product) super.getByCode(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getById(java.lang.String)
	 */
	@Override
	public Product getById(String id) throws DAOException {

		// log.debug("Get product by id : " + id);

		if (CANCEL.equals(id)) {
			return new ProductImpl(CANCEL, "CANCEL", "Cancelled", "Cancelled",
					"Cancelled", new BigDecimal(0), new BigDecimal(0),
					new BigDecimal(0), new ArrayList<SubProduct>(),
					new String());
		}

		return (Product) super.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#extractFromNode(org.w3c.dom.Element)
	 */
	@Override
	public Product extractFromNode(Element productNode) throws DAOException {

		String id = DOMUtils.getElementId(productNode);
		String code = DOMUtils.getElementValueByTagName(CODE, productNode);
		String name = DOMUtils.getElementValueByTagName(NAME, productNode);
		String description = DOMUtils.getElementValueByTagName(DESCRIPTION,
				productNode);
		String htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
				productNode);
		BigDecimal prix = BigDecimal.ZERO;
		try {
			prix = Tools.toBigDecimalNotNullDivide(DOMUtils
					.getElementValueByTagName(PRIX, productNode));
		} catch (ParameterException e) {
			description = "! ILLEGAL VALUE - PRIX ! " + description;
		}
		BigDecimal tvaTakeAway = BigDecimal.ZERO;
		try {
			tvaTakeAway = Tools.toBigDecimalNotNullDivide(DOMUtils
					.getElementValueByTagName(TVATAKEAWAY, productNode));
		} catch (ParameterException e) {
			description = "! ILLEGAL VALUE - TVA take away ! " + description;
		}
		BigDecimal tvaTakeOnPlace = BigDecimal.ZERO;
		try {
			tvaTakeOnPlace = Tools.toBigDecimalNotNullDivide(DOMUtils
					.getElementValueByTagName(TVATAKEONPLACE, productNode));
		} catch (ParameterException e) {
			description = "! ILLEGAL VALUE - TVA ! " + description;
		}

		String groupAsString = DOMUtils.getElementValueByTagName(GROUP,
				productNode);

		Element subProductEntryNode = (Element) DOMUtils.getElementByTagName(
				SUBPRODUCTS, productNode).item(0);
		List<SubProduct> subproductList = null;
		if (subProductEntryNode != null) {

			NodeList subproductListNode = subProductEntryNode
					.getElementsByTagName(SUBPRODUCT);

			subproductList = new ArrayList<SubProduct>();

			if (subproductListNode != null) {

				for (int i = 0; i < subproductListNode.getLength(); i++) {

					Element subproductNode = (Element) subproductListNode
							.item(i);
					String subProductQtyAsString = DOMUtils
							.getElementValueByTagName(SUBPRODUCTQTY,
									subproductNode);
					String subProductId = DOMUtils.getElementValueByTagName(
							SUBPRODUCTID, subproductNode);

					try {
						SubProduct subProduct = new SubProduct(
								subProductId,
								Tools.toBigDecimalNotNullDivide(subProductQtyAsString));
						subproductList.add(subProduct);
					} catch (ParameterException e) {
						throw new DAOException("subProductId", e);
					}
				}
			}
		}

		return new ProductImpl(id, code, name, description, htmlKeyLabel, prix,
				tvaTakeOnPlace, tvaTakeAway, subproductList, groupAsString);
	}

	@Override
	public void initEmptyNode(Document document, Element elementNode) {
		DOMUtils.addEmptyNode(elementNode, CODE);
		DOMUtils.addEmptyNode(elementNode, NAME);
		DOMUtils.addEmptyNode(elementNode, DESCRIPTION);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);
		DOMUtils.addEmptyNode(elementNode, PRIXACHAT);
		DOMUtils.addEmptyNode(elementNode, PRIX);
		DOMUtils.addEmptyNode(elementNode, TVATAKEAWAY);
		DOMUtils.addEmptyNode(elementNode, TVATAKEONPLACE);
		DOMUtils.addEmptyNode(elementNode, GROUP);
		DOMUtils.addEmptyNode(elementNode, SUBPRODUCTS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#setNode(be.panidel.common.Identification,
	 * be.panidel.common.Identification, boolean)
	 */
	@Override
	public void updateNode(Element currentNode, Identification identification)
			throws DAOException {

		ProductImpl product = (ProductImpl) identification;

		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(CODE).item(0),
				product.getCode());
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(NAME).item(0),
				product.getName());
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(DESCRIPTION).item(0),
				product.getDescription());
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(HTMLKEYLABEL)
						.item(0), product.getHtmlKeyLabel());
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(PRIX).item(0),
				Tools.toStringMultiplyNotNull(product.getPrix()));
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(TVATAKEAWAY).item(0),
				Tools.toStringMultiplyNotNull(product.getTvaTakeAway()));
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(TVATAKEONPLACE)
						.item(0), Tools.toStringMultiplyNotNull(product
						.getTvaTakeOnPlace()));
		DOMUtils.setElementValue(
				(Element) currentNode.getElementsByTagName(GROUP).item(0),
				product.getGroup(false));

		Element subProductListNode = (Element) currentNode
				.getElementsByTagName(SUBPRODUCTS).item(0);

		if (subProductListNode == null) {
			subProductListNode = DOMUtils
					.addEmptyNode(currentNode, SUBPRODUCTS);
		}

		for (SubProduct subProduct : product.getSubProductList()) {
			Element subprodNode = DOMUtils.addEmptyNode(subProductListNode,
					SUBPRODUCT);
			DOMUtils.addNodeWithValue(subprodNode, SUBPRODUCTQTY,
					Tools.toStringMultiplyNotNull(subProduct.getQty()));
			DOMUtils.addNodeWithValue(subprodNode, SUBPRODUCTID,
					subProduct.getRawProductId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getNodeElementName()
	 */
	@Override
	public String getNodeElementName() {
		return PRODUCT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getNodeName()
	 */
	@Override
	public String getNodeName() {
		return PRODUCTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getXmlFileName()
	 */
	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlProductsFile();
	}

	@Override
	public Identification getNewElement() {
		return new ProductImpl();
	}
}