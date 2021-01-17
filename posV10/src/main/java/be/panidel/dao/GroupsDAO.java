package be.panidel.dao;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Group;
import be.panidel.management.impl.GroupImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;

/**
 * @author franzph
 * 
 */
public class GroupsDAO extends DAOImpl {

	public final static String GROUPS = "groups";
	public final static String GROUP_NODE = "group";

	public Identification getNewElement() {
		return new GroupImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	public Group getByCode(String code) throws DAOException {
		return (Group) super.getByCode(code);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getById(java.lang.String)
	 */
	public Group getById(String id) throws DAOException {
		return (Group) super.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#extractFromNode(org.w3c.dom.Element)
	 */
	public Group extractFromNode(Element productNode) {

		String id = DOMUtils.getElementId(productNode);
		String code = DOMUtils.getElementValueByTagName(CODE, productNode);
		String name = DOMUtils.getElementValueByTagName(NAME, productNode);
		String description = DOMUtils.getElementValueByTagName(DESCRIPTION,
				productNode);
		String touchColor = DOMUtils.getElementValueByTagName(TOUCH_COLOR,
				productNode);
		String htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
				productNode);

		return new GroupImpl(id, code, name, description, htmlKeyLabel,
				touchColor);
	}

	public void initEmptyNode(Document document, Element elementNode) {
		DOMUtils.addEmptyNode(elementNode, CODE);
		DOMUtils.addEmptyNode(elementNode, NAME);
		DOMUtils.addEmptyNode(elementNode, DESCRIPTION);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);
		DOMUtils.addEmptyNode(elementNode, TOUCH_COLOR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#setNode(be.panidel.common.Identification,
	 * be.panidel.common.Identification, boolean)
	 */
	public void updateNode(Element currentNode, Identification identification)
			throws DAOException {

		Group group = (Group) identification;

		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				CODE).item(0), group.getCode());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				NAME).item(0), group.getName());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				DESCRIPTION).item(0), group.getDescription());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				HTMLKEYLABEL).item(0), group.getHtmlKeyLabel());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				TOUCH_COLOR).item(0), group.getTouchColor());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getNodeElementName()
	 */
	public String getNodeElementName() {
		return GROUP_NODE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getNodeName()
	 */
	public String getNodeName() {
		return GROUPS;
	}

	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlGroupsFile();
	}
}