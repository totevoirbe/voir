package be.panidel.dao;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Product;

public class ProductsDAOTest {

	private static final Logger log = Logger.getLogger("ProductsDAOTest");

	@Test
	public void testGetById() {
		try {
			ProductsDAO productDAO = FacadeDAO.instance().getProductsDAO();
			Product product = productDAO.getById("1");
			log.debug(product);
		} catch (DAOException e) {
			fail("DAOException");
		}

	}

	@Test
	public void testGetByCode() {
		try {
			ProductsDAO productDAO = FacadeDAO.instance().getProductsDAO();
			Product product = productDAO.getByCode("BAG");
			log.debug(product);
		} catch (DAOException e) {
			fail();
		}
	}

}
