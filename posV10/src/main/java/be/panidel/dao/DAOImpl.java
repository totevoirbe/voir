package be.panidel.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import be.panidel.common.Identification;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.XMLSource;

/**
 * @author franzph
 * 
 */
public abstract class DAOImpl implements DAO {

	private static final Logger log = Logger.getLogger("DAOImpl");

	private Element table;
	private XMLSource xmlSource;

	DAOImpl() {
		log.debug("Init");
	}

	public Identification getEmpty() {
		return getNewElement();
	}

	public Identification getNew() throws DAOException {
		Identification element = getNewElement();
		element.setId(getNewId());
		return element;
	}

	abstract Identification getNewElement();

	abstract Identification extractFromNode(Element node) throws DAOException;

	public boolean updateElement(Identification identification)
			throws DAOException {
		if (identification == null) {
			throw new DAOException("Identification is null(" + getClass() + ")");
		}
		String identificationId = identification.getId();
		Element currentNode = DOMUtils.getElementById(identificationId, table);
		if (currentNode != null) {
			updateNode(currentNode, identification);
		} else {
			return false;
		}
		return true;
	}

	public void newElement(Element identificationNode,
			Identification identification) throws DAOException {

		Document document = identificationNode.getOwnerDocument();
		Element elementNode = document.createElement(getNodeElementName());
		identificationNode.appendChild(elementNode);
		elementNode.setAttribute(ID, identification.getId());
		elementNode.setIdAttribute(ID, true);

		initEmptyNode(document, elementNode);

		updateNode(elementNode, identification);

	}

	abstract void initEmptyNode(Document document, Element productsNode);

	public Element deleteAll() throws DAOException {
		try {
			return DOMUtils.clearTable(getNodeName(), xmlSource);
		} catch (ParserConfigurationException e) {
			throw new DAOException(getNodeName() + "/" + xmlSource, e);
		} catch (SAXException e) {
			throw new DAOException(getNodeName() + "/" + xmlSource, e);
		} catch (IOException e) {
			throw new DAOException(getNodeName() + "/" + xmlSource, e);
		}
	}

	private String getNewId() throws DAOException {

		// new id
		int newIndex = 1;
		String newIndexAsString = null;
		do {
			newIndexAsString = Integer.toString(newIndex++);
		} while (isIdExisting(newIndexAsString));

		return newIndexAsString;
	}

	public boolean isIdExisting(String id) throws DAOException {
		return getById(id) != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getTable()
	 */
	public Element getTable() throws DAOException {
		try {
			if (xmlSource == null || table == null) {
				String smlSourceName = getXmlFile().getAbsolutePath();
				xmlSource = new XMLSource(smlSourceName);
				table = xmlSource.getData(getNodeName());
			}
		} catch (ParserConfigurationException e) {
			throw new DAOException("/nodeName:" + getNodeName() + "/xmlsource:"
					+ xmlSource, e);
		} catch (SAXException e) {
			throw new DAOException("/nodeName:" + getNodeName() + "/xmlsource:"
					+ xmlSource, e);
		} catch (IOException e) {
			throw new DAOException("/nodeName:" + getNodeName() + "/xmlsource:"
					+ xmlSource, e);
		} catch (ParameterException e) {
			throw new DAOException("/nodeName:" + getNodeName() + "/xmlsource:"
					+ xmlSource, e);
		}
		return table;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#saveTable()
	 */
	public void saveTable() throws DAOException {
		try {
			xmlSource.save(getXmlFile().getAbsolutePath());
		} catch (TransformerException e) {
			throw new DAOException(getNodeName() + "/" + xmlSource, e);
		} catch (ParameterException e) {
			throw new DAOException(getNodeName() + "/" + xmlSource, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getList()
	 */
	public List<Identification> getList() throws DAOException {

		// log.debug("Get product by id : " + id);

		return getListAsList(new ArrayList<Identification>());

	}

	public Vector<Identification> getListAsVector() throws DAOException {

		return (Vector<Identification>) getListAsList(new Vector<Identification>());

	}

	private List<Identification> getListAsList(
			List<Identification> allIdentifications) throws DAOException {

		List<Element> allElements = DOMUtils.getAsList(getTable());

		if (allElements != null) {
			for (int i = 0; i < allElements.size(); i++) {
				Element element = allElements.get(i);
				allIdentifications.add(extractFromNode(element));
			}
		}

		return allIdentifications;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getById(java.lang.String)
	 */
	public Identification getById(String id) throws DAOException {

		// log.debug("Get product by id : " + id);

		Element elementNode = DOMUtils.getElementById(id, getTable());

		if (elementNode != null) {
			return extractFromNode(elementNode);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#getByCode(java.lang.String)
	 */
	public Identification getByCode(String code) throws DAOException {

		log.debug("Get product by code : " + code);

		Element elementNode = DOMUtils.getElementByCode(code, getTable());

		if (elementNode != null) {
			return extractFromNode(elementNode);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAO#listOfId()
	 */
	public String[] listOfId() throws DAOException {
		log.debug("List of id");

		return DOMUtils.listOfId(getTable(), getNodeElementName());
	}

}