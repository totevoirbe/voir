package be.panidel.dao;

import java.io.File;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import be.panidel.common.Identification;
import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.person.Employee;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;

public class EmployeesDAO extends DAOImpl {

	private static final Logger log = Logger.getLogger("EmployeesDAO");

	public final static String EMPLOYEES = "employees";
	public final static String EMPLOYEE = "employee";

	public Identification getNewElement() {
		return new Employee();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getById(java.lang.String)
	 */
	public Employee getById(String id) throws DAOException {
		log.debug("Get by id : " + id);
		return (Employee) super.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.dao.DAOImpl#getByCode(java.lang.String)
	 */
	public Employee getByCode(String code) throws DAOException {
		log.debug("Get by code : " + code);
		return (Employee) super.getByCode(code);
	}

	public Identification extractFromNode(Element employeeNode) {

		String id = DOMUtils.getElementId(employeeNode);
		String code = DOMUtils.getElementValueByTagName(CODE, employeeNode);
		String name = DOMUtils.getElementValueByTagName(NAME, employeeNode);
		String htmlKeyLabel = DOMUtils.getElementValueByTagName(HTMLKEYLABEL,
				employeeNode);

		return new Employee(id, code, name, code, htmlKeyLabel, code, code,
				code, "cc", "cc", code);
	}

	public String getNodeElementName() {
		return EMPLOYEE;
	}

	public String getNodeName() {
		return EMPLOYEES;
	}

	@Override
	public File getXmlFile() throws ParameterException {
		return POSParameters.instance().getXmlEmployeesFile();
	}

	public void initEmptyNode(Document document, Element elementNode) {

		DOMUtils.addEmptyNode(elementNode, CODE);
		DOMUtils.addEmptyNode(elementNode, NAME);
		DOMUtils.addEmptyNode(elementNode, DESCRIPTION);
		DOMUtils.addEmptyNode(elementNode, HTMLKEYLABEL);

	}

	public void updateNode(Element currentNode, Identification identification)
			throws DAOException {

		Employee employee = (Employee) identification;

		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				CODE).item(0), employee.getCode());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				NAME).item(0), employee.getName());
		DOMUtils.setElementValue((Element) currentNode.getElementsByTagName(
				HTMLKEYLABEL).item(0), employee.getHtmlKeyLabel());
	}
}