package be.panidel.dao;

import java.io.File;
import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Payement;
import be.panidel.management.impl.PayementModeImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.Tools;

public class PayementModesDAO extends DAOImpl {

	private static final Logger log = Logger.getLogger("PayementModesDAO");

	public final static String TAG_NEEDSOMEVALUE = "needSomeValue";
	public final static String TAG_MAXTOTALAMOUNT = "maxTotalAmount";
	public final static String TAG_MAXQUANTITY = "maxQuantity";
	public final static String TAG_BEALONE = "beAlone";

	public final static String PAYMENTS = "payments";
	public final static String PAYMENT = "payment";

	public Identification extractFromNode(Element paymentNode)
			throws DAOException {

		String id = null;
		String description = null;
		String htmlKeyLabel = null;
		Boolean needSomeValue = null;
		BigDecimal maxTotalAmount = null;
		BigDecimal maxQuantity = null;
		Boolean beAlone = null;

		try {
			id = DOMUtils.getElementId(paymentNode);
			description = DOMUtils.getElementValueByTagName(DESCRIPTION,
					paymentNode);
			htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
					paymentNode);
			needSomeValue = DOMUtils.getElementBooleanByTagName(
					TAG_NEEDSOMEVALUE, paymentNode);
			maxTotalAmount = DOMUtils.getElementBigDecimalByTagName(
					TAG_MAXTOTALAMOUNT, paymentNode);
			maxQuantity = DOMUtils.getElementBigDecimalByTagName(
					TAG_MAXQUANTITY, paymentNode);
			beAlone = DOMUtils.getElementBooleanByTagName(TAG_BEALONE,
					paymentNode);

			return new PayementModeImpl(id, description, description,
					description, htmlKeyLabel, needSomeValue, maxTotalAmount,
					maxQuantity, beAlone);

		} catch (ParameterException e) {
			CoupleMessages cm = new CoupleMessages();

			cm.put("id", id);
			cm.put("description", description);
			cm.put("htmlKeyLabel", htmlKeyLabel);
			cm.put("needSomeValue", needSomeValue);
			cm.put("maxTotalAmount", maxTotalAmount);
			cm.put("maxQuantity", maxQuantity);
			cm.put("beAlone", beAlone);

			throw new DAOException(cm.toString(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getById(java.lang.String)
	 */
	public Payement getById(String id) throws DAOException {
		// log.debug("Get by id : " + id);
		return (Payement) super.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	public Payement getByCode(String code) throws DAOException {
		log.debug("Get by code : " + code);
		return (Payement) super.getByCode(code);
	}

	public String getNodeElementName() {
		return PAYMENT;
	}

	public String getNodeName() {
		return PAYMENTS;
	}

	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlPayementmodeFile();
	}

	public void initEmptyNode(Document document, Element elementNode) {
		DOMUtils.addEmptyNode(elementNode, DESCRIPTION);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);
		DOMUtils.addEmptyNode(elementNode, TAG_NEEDSOMEVALUE);
		DOMUtils.addEmptyNode(elementNode, TAG_MAXQUANTITY);
		DOMUtils.addEmptyNode(elementNode, TAG_MAXTOTALAMOUNT);
		DOMUtils.addEmptyNode(elementNode, TAG_BEALONE);
	}

	@Override
	public void updateNode(Element currentNode, Identification identification)
			throws DAOException {

		PayementModeImpl payementMode = (PayementModeImpl) identification;

		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				DESCRIPTION).item(0), payementMode.getDescription());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				HTMLKEYLABEL).item(0), payementMode.getHtmlKeyLabel());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				TAG_NEEDSOMEVALUE).item(0), Tools.toString(payementMode
				.isNeedSomeValue()));
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				TAG_MAXQUANTITY).item(0), Tools
				.toStringMultiplyNotNull(payementMode.getMaxQuantity()));
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				TAG_MAXTOTALAMOUNT).item(0), Tools
				.toStringMultiplyNotNull(payementMode.getMaxTotalAmount()));
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				TAG_BEALONE).item(0), Tools.toString(payementMode.isBeAlone()));
	}

	@Override
	public Identification getNewElement() {
		return new PayementModeImpl();
	}
}
