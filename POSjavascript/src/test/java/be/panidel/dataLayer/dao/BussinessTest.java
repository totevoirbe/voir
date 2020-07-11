package be.panidel.dataLayer.dao;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.AllTests;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.model.CashSaleModel;

public class BussinessTest {

	private final static Logger LOG = LoggerFactory.getLogger(BussinessTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		LOG.debug("Start testCreateSale()");

		DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);

		CashSaleModel cashSale = ModelMakerForTest.cashSaleMock();
		LOG.debug("Cash Sale 1 : " + cashSale);
		DataFacade.instance.createCashSale(cashSale);
		LOG.debug("Cash Sale 1 (persisted) : " + cashSale);

		cashSale = ModelMakerForTest.cashSaleMock();
		LOG.debug("Cash Sale 2 : " + cashSale);
		DataFacade.instance.createCashSale(cashSale);
		LOG.debug("Cash Sale 2 (persisted) : " + cashSale);

		cashSale = ModelMakerForTest.cashSaleMock();
		LOG.debug("Cash Sale 3 : " + cashSale);
		DataFacade.instance.createCashSale(cashSale);
		LOG.debug("Cash Sale 3 (persisted) : " + cashSale);

		LOG.debug("End testCreateSale()");

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
	public void simpleTest() {

		try {

			CashSaleModel cashSale = ModelMakerForTest.cashSaleMock();
			DataFacade.instance.createCashSale(cashSale);

		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

	}

}