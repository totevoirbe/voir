package be.panidel.dao;

import java.io.File;
import java.util.List;

import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;

public interface DAO {

	public final static String ID = "id";
	public final static String CODE = "code";
	public final static String NAME = "nom";
	public final static String DESCRIPTION = "description";
	public final static String HTMLKEYLABEL = "htmlKeyLabel";
	public final static String ADRESS = "adress";
	public final static String POSTALCODE = "postalCode";
	public final static String PHONE = "phone";
	public final static String FAX = "fax";
	public final static String TOUCH_COLOR = "touchcolor";

	Element getTable() throws DAOException;

	void saveTable() throws DAOException;

	Identification getNew() throws DAOException;

	Identification getEmpty();

	boolean isIdExisting(String id) throws DAOException;

	Identification getById(String id) throws DAOException;

	String[] listOfId() throws DAOException;

	Identification getByCode(String code) throws DAOException;

	List<Identification> getList() throws DAOException;

	String getNodeName();

	String getNodeElementName();

	File getXmlFile() throws ParameterException;

	/**
	 * Set each attributes of content to node
	 * 
	 * @param node
	 *            node to set
	 * @param content
	 *            element to set to node
	 */
	void updateNode(Element currentNode, Identification identification)
			throws DAOException;

	boolean updateElement(Identification identification) throws DAOException;

	void newElement(Element identificationNode, Identification identification)
			throws DAOException;

	Element deleteAll() throws DAOException;
}