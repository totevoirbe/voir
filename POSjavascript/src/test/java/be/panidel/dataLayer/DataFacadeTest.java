package be.panidel.dataLayer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.AllTests;
import be.panidel.dataLayer.DataFacade.PosIdentification;
import be.panidel.dataLayer.dao.ModelMakerForTest;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.ProductModel;

public class DataFacadeTest {

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	private final static Logger LOG = LoggerFactory.getLogger(DataFacadeTest.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);
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
	public void testGetUUIDCashSale() {

		for (int i = 0; i < 100; i++) {
			Date transactionDate = new Date();
			PosIdentification posIdentification = PosIdentification.SERVER;
			boolean quickGenerator = true;
			Long UUID = DataFacade.getUUIDCashSale(transactionDate, posIdentification, quickGenerator);
			LOG.debug("UUID : " + UUID);
		}

	}

	@Test
	public void testCreateCashSaleCashSaleModel() {

		LOG.debug("Start testCreateSale()");

		try {

			CashSaleModel cashSaleModel = ModelMakerForTest.cashSaleMock();

			LOG.debug("Interm 1.");

			DataFacade.instance.createCashSale(cashSaleModel);

			LOG.debug("Sale is created.");

			CashSaleModel cashSaleModelEval = DataFacade.instance.getCashSale(cashSaleModel.getId());

			LOG.debug("Sale model avant : " + cashSaleModel);

			LOG.debug("Sale model après : " + cashSaleModelEval);

			assertTrue(cashSaleModelEval != null && cashSaleModelEval.equals(cashSaleModel));

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testCreateSale()");
	}

	@Test
	public void testGetCashSale() {

		LOG.info("Start testCreateSale()");

		try {

			CashSaleModel cashSaleModel = ModelMakerForTest.cashSaleMock();

			DataFacade.instance.createCashSale(cashSaleModel);

			LOG.info("Sale is created.");

			CashSaleModel cashSaleModelEval = DataFacade.instance.getCashSale(cashSaleModel.getId());

			LOG.info("Sale model avant : " + cashSaleModel);

			LOG.info("Sale model après : " + cashSaleModelEval);

			assertEquals(cashSaleModelEval, cashSaleModel);

		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testCreateSale()");
	}

	@Test
	public void testDeleteCashSale() {
		// fail("Not yet implemented");
	}

	@Test
	public void testGetAllCashSales() {

		LOG.debug("Start testGetAllCashSales()");

		try {
			CashSaleModel cashSaleModel = ModelMakerForTest.cashSaleMock();

			DataFacade.instance.createCashSale(cashSaleModel);

			List<CashSaleModel> cashSaleModels = DataFacade.instance.getAllCashSales(10);

			for (CashSaleModel cashSaleModelItem : cashSaleModels) {
				LOG.debug("" + cashSaleModelItem);
			}

			assertTrue(cashSaleModels != null && cashSaleModels.size() > 0);

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testGetAllCashSales()");
	}

	// @Test
	public void testGetCashSalesStartDateEndDate() {

		LOG.debug("Start testGetCashSalesStartDateEndDate()");

		try {

			Date startDate = simpleDateFormat.parse("01/03/2015");
			Date endDate = simpleDateFormat.parse("31/12/2020");

			List<CashSaleModel> cashSaleModels = DataFacade.instance.getCashSalesBetween(startDate, endDate, 10);

			LOG.debug("Period [" + simpleDateFormat.format(startDate) + "-" + simpleDateFormat.format(endDate)
					+ "], count = " + cashSaleModels.size());

			for (CashSaleModel cashSaleModelItem : cashSaleModels) {
				LOG.debug("" + cashSaleModelItem);
			}

			assertTrue(cashSaleModels != null && cashSaleModels.size() > 0);

		} catch (DataLayerException | ParseException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testGetCashSalesStartDateEndDate()");
	}

	@Test
	public void testGetVatRateByCode() {

		LOG.info("Start testGetVatRateByCode()");

		try {

			DataFacade.instance.getVatRateByCode("0");

		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testCountCashSalesStartDateEndDate()");
	}

	@Test
	public void testCountCashSalesStartDateEndDate() {

		LOG.debug("Start testCountCashSalesStartDateEndDate()");

		try {

			Date startDate = simpleDateFormat.parse("01/01/1980");
			Date endDate = simpleDateFormat.parse("30/12/2018");

			long count = DataFacade.instance.countCashSales(startDate, endDate);

			LOG.debug("Period [" + simpleDateFormat.format(startDate) + "-" + simpleDateFormat.format(endDate)
					+ "], count = " + count);

			assertTrue(count > 0);

		} catch (DataLayerException | ParseException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testCountCashSalesStartDateEndDate()");
	}

	@Test
	public void testGetAllProducts() {

		LOG.debug("Start testGetAllCashSales()");

		try {

			List<ProductModel> productModels = DataFacade.instance.getAllProducts();

			for (ProductModel productModel : productModels) {
				LOG.debug("" + productModel);
			}

			assertTrue(productModels != null && productModels.size() > 0);

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testGetAllCashSales()");
	}

	@Test
	public void testGetProductById() {

		LOG.debug("Start testGetProductById()");

		try {

			ProductModel product = DataFacade.instance.getProductById(ModelMakerForTest.getRandomProductId());

			assertTrue(product != null);

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testCreateSale()");
	}

	@Test
	public void testGetProductByCode() {

		LOG.debug("Start testGetProductByCode()");

		try {

			ProductModel product = DataFacade.instance.getProductById(ModelMakerForTest.getRandomProductId());

			ProductModel retrievedProduct = DataFacade.instance.getProductByCode(product.getCode());

			assertTrue(retrievedProduct != null);

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testGetProductByCode()");

	}

	@Test
	public void testDelete() {

		LOG.debug("Start testDeleteAll()");
		try {

			long count = DataFacade.instance.countCashSales();
			LOG.info("Number of sales : " + count);

			CashSaleModel saleModel = ModelMakerForTest.cashSaleMock();
			DataFacade.instance.createCashSale(saleModel);

			assertTrue(DataFacade.instance.countCashSales() == count + 1);

			DataFacade.instance.deleteCashSale(saleModel);

			assertTrue(DataFacade.instance.countCashSales() == count);

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

		LOG.debug("End testDeleteAll()");

	}

}
