package be.panidel.dataLayer.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

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
import be.panidel.dataLayer.model.ProductModel;

public class ProductDaoTest {

	private final static Logger LOG = LoggerFactory.getLogger(ProductDaoTest.class);

	private static ProductDao productDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);
			productDao = new ProductDao();
		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
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
	public void testFind() {

		EntityManager em = null;

		try {

			em = DataLayerHelper.getNewEntityManager();

			ProductModel productModel = productDao.find(ModelMakerForTest.getRandomProductId(), em);
			assertNotNull(productModel);
			LOG.info("" + productModel);

			productModel = productDao.find(ModelMakerForTest.getRandomProductId(), em);
			assertNotNull(productModel);
			LOG.info("" + productModel);

			productModel = productDao.find(ModelMakerForTest.getRandomProductId(), em);
			assertNotNull(productModel);
			LOG.info("" + productModel);
		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

	}

}