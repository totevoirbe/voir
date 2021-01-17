package be.panidel.dao;

import java.io.File;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.bean.KeyBean;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;

public class KeyboardLayoutDAO extends DAOImpl {

	private static final Logger log = Logger.getLogger(KeyboardLayoutDAO.class);

	public final static String KEYS = "keys";
	public final static String KEY = "key";

	public final static String KEYCODE = "keyCode";
	public final static String OPERATIONTYPE = "operationType";
	public final static String OPERATIONCODE = "operationCode";
	public final static String KEYBOARDCOL = "keyboardCol";
	public final static String KEYBOARDROW = "keyboardRow";

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	public KeyBean getByCode(String code) throws DAOException {
		return (KeyBean) super.getByCode(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getById(java.lang.String)
	 */
	public KeyBean getById(String id) throws DAOException {
		return (KeyBean) super.getById(id);
	}

	public KeyBean extractFromNode(Element keyNode) {

		String id = DOMUtils.getElementId(keyNode);

		String keyCode = DOMUtils.getElementValueByTagName(KEYCODE, keyNode);
		String operationType = DOMUtils.getElementValueByTagName(OPERATIONTYPE,
				keyNode);
		String operationCode = DOMUtils.getElementValueByTagName(OPERATIONCODE,
				keyNode);
		String keyboardCol = DOMUtils.getElementValueByTagName(KEYBOARDCOL,
				keyNode);
		String keyboardRow = DOMUtils.getElementValueByTagName(KEYBOARDROW,
				keyNode);
		String htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
				keyNode);
		if (log.isDebugEnabled()) {
			log.debug("Keyboard bean : " + id);
		}
		return new KeyBean(id, keyCode, operationType, operationCode,
				keyboardCol, keyboardRow, htmlKeyLabel);
	}

	public String getNodeElementName() {
		return KEY;
	}

	public String getNodeName() {
		return KEYS;
	}

	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlKblayoutFile();
	}

	public void initEmptyNode(Document document, Element elementNode) {
		DOMUtils.addEmptyNode(elementNode, KEYCODE);
		DOMUtils.addEmptyNode(elementNode, OPERATIONTYPE);
		DOMUtils.addEmptyNode(elementNode, OPERATIONCODE);
		DOMUtils.addEmptyNode(elementNode, KEYBOARDCOL);
		DOMUtils.addEmptyNode(elementNode, KEYBOARDROW);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);
	}

	@Override
	public void updateNode(Element currentNode, Identification identification)
			throws DAOException {

		KeyBean key = (KeyBean) identification;

		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				KEYCODE).item(0), key.getKeyCode());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				OPERATIONTYPE).item(0), key.getOperationType());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				OPERATIONCODE).item(0), key.getOperationCode());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				KEYBOARDCOL).item(0), key.getKeyboardCol());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				KEYBOARDROW).item(0), key.getKeyboardRow());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				HTMLKEYLABEL).item(0), key.getHtmlKeyLabel());
	}

	@Override
	public Identification getNewElement() {
		return new KeyBean();
	}
}