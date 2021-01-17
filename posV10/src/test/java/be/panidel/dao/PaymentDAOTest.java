package be.panidel.dao;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Payement;

public class PaymentDAOTest {

	private static final Logger log = Logger.getLogger("PaymentDAOTest");

	@Test
	public void testGetById() {
		try {
			PayementModesDAO paymentModeDAO = FacadeDAO.instance()
					.getPayementModesDAO();
			Payement payment = paymentModeDAO.getById("1");
			log.debug(payment);
		} catch (DAOException e) {
			fail("ParserConfigurationException");
		}
	}

}
