package be.panidel.dataLayer.dao;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
import be.panidel.dataLayer.model.CashSaleModel;

public class ResultEvalTest {

	private final static Logger LOG = LoggerFactory.getLogger(ResultEvalTest.class);

	private static CashSaleDao cashSaleDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		DataLayerHelper.selectCurrentPersistenceUnit(AllTests.persistenceUnitTest);
		ItemProductDao itemProductDao = new ItemProductDao();
		ItemPaymentDao itemPaymentDao = new ItemPaymentDao();
		ProductDao productDao = new ProductDao();
		PaymentMethodDao paymentMethodDao = new PaymentMethodDao();
		cashSaleDao = new CashSaleDao(itemProductDao, itemPaymentDao, productDao, paymentMethodDao);

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

	// @Test
	public void productItemsResults() {

	}

	@Test
	public void cashSaleResults() {

		int maxResults = 100000;

		Date startDate = new GregorianCalendar(2015, 0, 1).getTime();
		Date endDate = new GregorianCalendar(2016, 0, 1).getTime();
		cashSalesReults(maxResults, startDate, endDate);

		startDate = new GregorianCalendar(2016, 0, 1).getTime();
		endDate = new GregorianCalendar(2017, 0, 1).getTime();
		cashSalesReults(maxResults, startDate, endDate);

		startDate = new GregorianCalendar(2017, 0, 1).getTime();
		endDate = new GregorianCalendar(2018, 0, 1).getTime();
		cashSalesReults(maxResults, startDate, endDate);

	}

	protected void cashSalesReults(int maxResults, Date startDate, Date endDate) {

		BigDecimal cashSaleTotal = BigDecimal.ZERO;
		BigDecimal saleExcludVAT = BigDecimal.ZERO;
		BigDecimal nbArticles = BigDecimal.ZERO;

		EntityManager em = null;

		try {

			em = DataLayerHelper.getNewEntityManager();

			List<CashSaleModel> cashSaleModels = cashSaleDao.getBetweenDates(startDate, endDate, maxResults, em);
			for (CashSaleModel cashSale : cashSaleModels) {
				cashSaleTotal = cashSaleTotal.add(cashSale.getCashSaleTotal());
				saleExcludVAT = saleExcludVAT.add(cashSale.getCashSaleExcludVAT());
				nbArticles = nbArticles.add(cashSale.getCashSaleNbArticles());
			}
			LOG.info("TOTAL " + SimpleDateFormat.getInstance().format(startDate) + ">"
					+ SimpleDateFormat.getInstance().format(endDate) + " / rows " + cashSaleModels.size()
					+ " / with vat " + cashSaleTotal + " / without VAT " + saleExcludVAT + " / Art. " + nbArticles);
		} catch (DataLayerException e) {
			LOG.error("", e);
			fail(e.getMessage());
		} finally {
			DataLayerHelper.closeEntityManager(em);
		}
	}

}