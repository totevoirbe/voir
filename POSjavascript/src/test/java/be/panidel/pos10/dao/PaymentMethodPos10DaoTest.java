package be.panidel.pos10.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.model.PaymentMethodModel;

public class PaymentMethodPos10DaoTest {

	private final static Logger LOG = LoggerFactory.getLogger(PaymentMethodPos10DaoTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetById() {
		PaymentMethodPos10Dao.instance.getPaymentMethodList();
		PaymentMethodModel paymentMethod = PaymentMethodPos10Dao.instance.getById(10L);
		assertNotNull(paymentMethod);
		LOG.debug("" + paymentMethod);
	}

	@Test
	public void testGetByCode() {
		PaymentMethodPos10Dao.instance.getPaymentMethodList();
		PaymentMethodModel paymentMethod = PaymentMethodPos10Dao.instance.getByCode("Jeté");
		assertNotNull(paymentMethod);
		LOG.debug("" + paymentMethod);

	}

}
