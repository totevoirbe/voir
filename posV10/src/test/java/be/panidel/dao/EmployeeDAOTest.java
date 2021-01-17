package be.panidel.dao;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import be.panidel.dao.exeption.DAOException;
import be.panidel.management.impl.person.Employee;

public class EmployeeDAOTest {

	private static final Logger log = Logger.getLogger("EmployeeDAOTest");

	@Test
	public void testGetById() {
		try {
			EmployeesDAO employeeDAO = FacadeDAO.instance().getEmployeesDAO();
			Employee employee = employeeDAO.getById("1");
			log.debug(employee);
		} catch (DAOException e) {
			fail("ParserConfigurationException");
		}

	}

	@Test
	public void testGetByCode() {
		try {
			EmployeesDAO employeeDAO = FacadeDAO.instance().getEmployeesDAO();
			Employee employee = employeeDAO.getByCode("COM");
			log.debug(employee);
		} catch (DAOException e) {
			fail("ParserConfigurationException");
		}

	}

}
