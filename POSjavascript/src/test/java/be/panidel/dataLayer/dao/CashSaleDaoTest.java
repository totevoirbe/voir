package be.panidel.dataLayer.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

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
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.model.CashSaleModel;

public class CashSaleDaoTest {

	private final static Logger LOG = LoggerFactory.getLogger(CashSaleDaoTest.class);

	private static CashSaleDao cashSaleDao;
	private static ItemProductDao itemProductDao;
	private static ItemPaymentDao itemPaymentDao;
	private static ProductDao productDao;
	private static PaymentMethodDao paymentMethodDao;

	private static CashSaleModel cashSaleModel1;
	private static CashSaleModel cashSaleModel2;
	private static CashSaleModel cashSaleModel3;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);

		itemProductDao = new ItemProductDao();
		itemPaymentDao = new ItemPaymentDao();
		productDao = new ProductDao();
		paymentMethodDao = new PaymentMethodDao();
		cashSaleDao = new CashSaleDao(itemProductDao, itemPaymentDao, productDao, paymentMethodDao);

		EntityManager em = DataLayerHelper.getNewEntityManager();

		EntityTransaction txn = em.getTransaction();

		try {

			txn.begin();
			cashSaleModel1 = ModelMakerForTest.cashSaleMock();
			LOG.debug("Sale 1 : " + cashSaleModel1);
			cashSaleDao.write(cashSaleModel1, em);

			cashSaleModel2 = ModelMakerForTest.cashSaleMock();
			LOG.debug("Sale 2 : " + cashSaleModel2);
			cashSaleDao.write(cashSaleModel2, em);

			cashSaleModel3 = ModelMakerForTest.cashSaleMock();
			LOG.debug("Sale 3 : " + cashSaleModel3);
			cashSaleDao.write(cashSaleModel3, em);

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

	public void testCreateAndDelete() {

		EntityManager em = null;
		EntityTransaction txn = null;

		try {

			em = DataLayerHelper.getNewEntityManager();
			txn = em.getTransaction();

			txn.begin();
			CashSaleModel cashSaleModel = ModelMakerForTest.cashSaleMock();
			cashSaleDao.write(cashSaleModel, em);
			txn.commit();
			assertNotNull(cashSaleDao.find(cashSaleModel.getId(), em));
			txn.begin();
			cashSaleDao.delete(cashSaleModel.getId(), em);
			txn.commit();
			assertNull(cashSaleDao.find(cashSaleModel.getId(), em));

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

	@Test
	public void testFind() {

		EntityManager em = null;

		try {

			em = DataLayerHelper.getNewEntityManager();

			CashSaleModel cashSaleModel = cashSaleDao.find(cashSaleModel1.getId(), em);
			assertNotNull(cashSaleModel);
			LOG.info("" + cashSaleModel);

			cashSaleModel = cashSaleDao.find(cashSaleModel2.getId(), em);
			assertNotNull(cashSaleModel);
			LOG.info("" + cashSaleModel);

			cashSaleModel = cashSaleDao.find(cashSaleModel3.getId(), em);
			assertNotNull(cashSaleModel);
			LOG.info("" + cashSaleModel);

		} catch (DataLayerException e) {
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
			CashSaleModel cashSaleModelRef = cashSaleDao.find(cashSaleModel2.getId(), em);
			cashSaleModelRef.setCashSalePaymentTotal(BigDecimal.TEN);
			assertTrue(cashSaleDao.find(cashSaleModel2.getId(), em).getCashSalePaymentTotal().equals(BigDecimal.TEN));
			CashSaleModel cashSaleModelRef2 = cashSaleDao.find(cashSaleModel2.getId(), em);
			cashSaleModelRef2.setCashSalePaymentTotal(BigDecimal.ZERO);
			assertTrue(cashSaleDao.find(cashSaleModel2.getId(), em).getCashSalePaymentTotal().equals(BigDecimal.ZERO));
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