package be.panidel.dataLayer.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.AllTests;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.model.PaymentMethodModel;

public class PaymentMethodDaoTest {

	private final static Logger LOG = LoggerFactory.getLogger(PaymentMethodDaoTest.class);

	private static PaymentMethodDao paymentMethodDao;

	private static PaymentMethodModel paymentMethod1;
	private static PaymentMethodModel paymentMethod2;
	private static PaymentMethodModel paymentMethod3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);

		paymentMethodDao = new PaymentMethodDao();

		paymentMethod1 = ModelMakerForTest.getRandomPaymentMethod();
		paymentMethod2 = ModelMakerForTest.getRandomPaymentMethod();
		paymentMethod3 = ModelMakerForTest.getRandomPaymentMethod();

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
	public void testFind() {

		EntityManager em = null;

		try {

			em = DataLayerHelper.getNewEntityManager();

			LOG.debug("Test find payment methods : " + paymentMethod1.getId() + " / " + paymentMethod2.getId() + " / "
					+ paymentMethod3.getId() + " / ");
			PaymentMethodModel paymentMethod = paymentMethodDao.find(paymentMethod1.getId(), em);
			assertNotNull(paymentMethod);
			LOG.info("" + paymentMethod);

			paymentMethod = paymentMethodDao.find(paymentMethod2.getId(), em);
			assertNotNull(paymentMethod);
			LOG.info("" + paymentMethod);

			paymentMethod = paymentMethodDao.find(paymentMethod3.getId(), em);
			assertNotNull(paymentMethod);
			LOG.info("" + paymentMethod);

		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

	}

	@Test
	public void testUpdate() {

		EntityManager em = null;
		EntityTransaction txn = null;

		try {

			em = DataLayerHelper.getNewEntityManager();
			txn = em.getTransaction();

			txn.begin();
			PaymentMethodModel paymentMethodRef = paymentMethodDao.find(paymentMethod2.getId(), em);
			paymentMethodRef.setLabel("label for test");
			assertTrue(paymentMethodDao.find(paymentMethod2.getId(), em).getLabel().equals("label for test"));
			PaymentMethodModel paymentMethodRef2 = paymentMethodDao.find(paymentMethod2.getId(), em);
			paymentMethodRef2.setLabel("second label for test");
			assertTrue(paymentMethodDao.find(paymentMethod2.getId(), em).getLabel().equals("second label for test"));
			txn.commit();

		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			DataLayerHelper.closeEntityManager(em);
		}
	}

}