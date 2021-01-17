package be.panidel.dao;

import java.io.File;
import java.math.BigDecimal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.RawProduct;
import be.panidel.management.impl.RawProductImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.Tools;

/**
 * @author franzph
 * 
 */
public class RawProductsDAO extends DAOImpl {

	public final static String CANCEL = "-1";

	public final static String PRIXACHAT = "prixachat";

	public final static String RAWPRODUCTS = "rawproducts";
	public final static String RAWPRODUCT = "rawproduct";

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	@Override
	public RawProduct getByCode(String code) throws DAOException {
		return (RawProduct) super.getByCode(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getById(java.lang.String)
	 */
	@Override
	public RawProduct getById(String id) throws DAOException {

		// log.debug("Get product by id : " + id);

		if (CANCEL.equals(id)) {
			return new RawProductImpl(CANCEL, "CANCEL", "Cancelled",
					"Cancelled", "Cancelled", new BigDecimal(0));
		}

		return (RawProduct) super.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#extractFromNode(org.w3c.dom.Element)
	 */
	@Override
	public RawProduct extractFromNode(Element productNode) throws DAOException {

		String id = DOMUtils.getElementId(productNode);
		String code = DOMUtils.getElementValueByTagName(CODE, productNode);
		String name = DOMUtils.getElementValueByTagName(NAME, productNode);
		String description = DOMUtils.getElementValueByTagName(DESCRIPTION,
				productNode);
		String htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
				productNode);
		BigDecimal prixAchat = BigDecimal.ZERO;
		try {
			prixAchat = Tools.toBigDecimalNotNullDivide(DOMUtils
					.getElementValueByTagName(PRIXACHAT, productNode));
		} catch (ParameterException e) {
			description = "! ILLEGAL VALUE - PRIX ! " + description;
		}

		return new RawProductImpl(id, code, name, description, htmlKeyLabel,
				prixAchat);
	}

	@Override
	public void initEmptyNode(Document document, Element elementNode) {
		DOMUtils.addEmptyNode(elementNode, CODE);
		DOMUtils.addEmptyNode(elementNode, NAME);
		DOMUtils.addEmptyNode(elementNode, DESCRIPTION);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);
		DOMUtils.addEmptyNode(elementNode, PRIXACHAT);
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

		RawProductImpl product = (RawProductImpl) identification;

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
				(Element) currentNode.getElementsByTagName(PRIXACHAT).item(0),
				Tools.toStringMultiplyNotNull(product.getPrixAchat()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getNodeElementName()
	 */
	@Override
	public String getNodeElementName() {
		return RAWPRODUCT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getNodeName()
	 */
	@Override
	public String getNodeName() {
		return RAWPRODUCTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getXmlFileName()
	 */
	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlRawProductsFile();
	}

	@Override
	public Identification getNewElement() {
		return new RawProductImpl();
	}
}