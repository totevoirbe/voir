package be.panidel.dao;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Company;
import be.panidel.management.impl.CompanyImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;

public class CompanyDAO extends DAOImpl {

	private static final Logger log = Logger.getLogger("CompanyDAO");

	public final static String COMPANIES = "companies";
	public final static String COMPANY = "company";

	private Map<String, Company> items;

	CompanyDAO() {
		super();
		log.debug("Init companies");
		items = new HashMap<String, Company>();
		items.put("1", new CompanyImpl("1", "PAN", "Panidel", "Panidel sprl",
				"Panidel", "24, rue de Steinfort", "6700", "+3263/225194",
				"+3263/227315"));
	}

	public Identification getNewElement() {
		return new CompanyImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getById(java.lang.String)
	 */
	public Company getById(String id) throws DAOException {
		return (Company) super.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	public Company getByCode(String code) throws DAOException {
		return (Company) super.getByCode(code);
	}

	public Identification extractFromNode(Element productNode) {

		String id = DOMUtils.getElementId(productNode);
		String code = DOMUtils.getElementValueByTagName(CODE, productNode);
		String name = DOMUtils.getElementValueByTagName(NAME, productNode);
		String description = DOMUtils.getElementValueByTagName(DESCRIPTION,
				productNode);
		String htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
				productNode);
		String adress = DOMUtils.getElementValueByTagName(ADRESS, productNode);
		String postalCode = DOMUtils.getElementValueByTagName(POSTALCODE,
				productNode);
		String phone = DOMUtils.getElementValueByTagName(PHONE, productNode);
		String fax = DOMUtils.getElementValueByTagName(FAX, productNode);

		return new CompanyImpl(id, code, name, description, htmlKeyLabel,
				adress, postalCode, phone, fax);
	}

	public void initEmptyNode(Document document, Element elementNode) {

		DOMUtils.addEmptyNode(elementNode, CODE);
		DOMUtils.addEmptyNode(elementNode, NAME);
		DOMUtils.addEmptyNode(elementNode, DESCRIPTION);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);
		DOMUtils.addEmptyNode(elementNode, ADRESS);
		DOMUtils.addEmptyNode(elementNode, POSTALCODE);
		DOMUtils.addEmptyNode(elementNode, PHONE);
		DOMUtils.addEmptyNode(elementNode, FAX);

	}

	public String getNodeElementName() {
		return COMPANY;
	}

	public String getNodeName() {
		return COMPANIES;
	}

	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlCompanyFile();
	}

	@Override
	public void updateNode(Element currentNode, Identification identification)
			throws DAOException {

		CompanyImpl company = (CompanyImpl) identification;

		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				CODE).item(0), company.getCode());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				NAME).item(0), company.getName());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				DESCRIPTION).item(0), company.getDescription());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				HTMLKEYLABEL).item(0), company.getHtmlKeyLabel());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				ADRESS).item(0), company.getAdress());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				POSTALCODE).item(0), company.getPostalCode());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				PHONE).item(0), company.getPhone());
		DOMUtils.setElementValue((Element) currentNode
				.getElementsByTagName(FAX).item(0), company.getFax());

	}
}
