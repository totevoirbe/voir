package be.panidel.pos10.tool;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

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
import be.panidel.frontLayer.model.ItemProductJsonModel;
import be.panidel.pos10.model.Item;

public class ProcessAsCashSalesTest {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessAsCashSalesTest.class);

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

	public void testPersistCashSales() {
	}

	@Test
	public void testMapProductPos10toProduct() {

		try {

			DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);

			ProcessAsCashSalesPos10 processAsCashSales = new ProcessAsCashSalesPos10(null, null, 0);

			ItemProductJsonModel itemProduct = null;

			BigDecimal quantity = BigDecimal.ONE;
			Integer product = new Integer("1234");
			String description = "description";
			BigDecimal unitPrice = BigDecimal.ONE;
			BigDecimal tvaTakeAway = BigDecimal.ONE;
			BigDecimal tvaTakeOnPlace = BigDecimal.ONE;

			Item fakeItem = new Item(quantity, product, description, unitPrice, tvaTakeAway, tvaTakeOnPlace);

			itemProduct = processAsCashSales.mapProductPos10toProduct(fakeItem, "GJAMBONFROMAGEFIT");
			assertNotNull("GJAMBONFROMAGEFIT", itemProduct);
			itemProduct = processAsCashSales.mapProductPos10toProduct(fakeItem, "JAMBONFROMAGEFIT");
			assertNotNull("JAMBONFROMAGEFIT", itemProduct);
			itemProduct = processAsCashSales.mapProductPos10toProduct(fakeItem, "MJAMBONFROMAGEFIT");
			assertNotNull("MJAMBONFROMAGEFIT", itemProduct);
			itemProduct = processAsCashSales.mapProductPos10toProduct(fakeItem, "GJAMBONFROMAGE");
			assertNotNull("GJAMBONFROMAGE", itemProduct);
			itemProduct = processAsCashSales.mapProductPos10toProduct(fakeItem, "JAMBONFROMAGE");
			assertNotNull("JAMBONFROMAGE", itemProduct);
			itemProduct = processAsCashSales.mapProductPos10toProduct(fakeItem, "MJAMBONFROMAGE");
			assertNotNull("MJAMBONFROMAGE", itemProduct);

		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		}

	}

}
