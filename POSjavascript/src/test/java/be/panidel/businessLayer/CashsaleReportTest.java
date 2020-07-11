package be.panidel.businessLayer;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.AllTests;
import be.panidel.businessLayer.helper.EnumHelper.PeriodType;
import be.panidel.businessLayer.model.CashSaleReportModel;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.helper.SysHelper;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.pos10.tool.ProcessAsCashSalesPos10;
import be.panidel.tarif.xlsWriter.XlsCashSaleWriter;
import be.panidel.tarif.xlsWriter.XlsCashSaleWriter.ReportType;

public class CashsaleReportTest {

	private final static Logger LOG = LoggerFactory.getLogger(CashsaleReportTest.class);

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
	public void testPos10Migartion() {

		ReportPeriod reportPeriod = new ReportPeriod(new GregorianCalendar(2017, 0, 1).getTime(),
				new GregorianCalendar(2017, 3, 15).getTime(), null);

		ProcessAsCashSalesPos10.pos10Migration(reportPeriod.getBeginTime(), reportPeriod.getEndTime());
	}

	@Test
	public void testUnitComputeSale() {

		Long cashSaleId = 201707120918051L;

		PeriodType periodType = PeriodType.CASH_SALE_UNIT;
		ReportType reportType = ReportType.WITH_ITEM_DETAILS;

		try {

			CashSaleModel cashSale = DataFacade.instance.getCashSale(cashSaleId);

			assertTrue(CashSaleComputation.checkCashSale(cashSale));

			LOG.info("Merge cash sale : " + cashSale);

			ReportPeriod reportPeriod = new ReportPeriod(cashSale.getOpenDate(), cashSale.getEndDate(), periodType);

			CashSaleReportModel cashSaleReport = CashsaleReport.mapCashSaleReport(null, cashSale, reportPeriod);

			LOG.info("Write cash sale report : " + cashSaleReport);

			XlsCashSaleWriter.writeCashsalesReport(cashSaleReport, reportType, periodType);

			LOG.info("done");

		} catch (Exception e) {
			LOG.error("", e);
		}

	}

	@Test
	public void testComputeCashSalesByYearSimpleMerge() {
		ReportPeriod reportPeriod = new ReportPeriod(new GregorianCalendar(2017, 0, 1).getTime(),
				new GregorianCalendar(2017, 3, 15).getTime(), PeriodType.YEAR);
		testGenerateReport(reportPeriod, ReportType.WITH_ITEM_DETAILS);
	}

	@Test
	public void testComputeCashSalesByMonthSimpleMerge() {
		ReportPeriod reportPeriod = new ReportPeriod(new GregorianCalendar(2017, 0, 1).getTime(),
				new GregorianCalendar(2017, 3, 15).getTime(), PeriodType.MONTH);
		testGenerateReport(reportPeriod, ReportType.WITH_ITEM_DETAILS);
	}

	@Test
	public void testComputeCashSalesByDaySimpleMerge() {
		ReportPeriod reportPeriod = new ReportPeriod(new GregorianCalendar(2017, 0, 1).getTime(),
				new GregorianCalendar(2017, 0, 5).getTime(), PeriodType.DAY);
		testGenerateReport(reportPeriod, ReportType.WITH_ITEM_DETAILS);
	}

	private void testGenerateReport(ReportPeriod reportPeriod, ReportType reportType) {

		try {

			SysHelper.maxMemoryRatio = 0;
			XlsCashSaleWriter.writeCashsalesReport(reportPeriod, reportType);
			SysHelper.displayMemoryUtilization("AFTER testGenerateReport " + reportPeriod + " / " + reportType);

		} catch (Exception e) {
			LOG.error("", e);
			fail(e.getMessage());
		}
	}

	// @Test
	public void testGetPeriodDate() {
		fail("Not yet implemented");
	}

	// @Test
	public void testMergeProducts() {
		fail("Not yet implemented");
	}

	// @Test
	public void testMergePayments() {
		fail("Not yet implemented");
	}

	// @Test
	public void testMergeProduct() {
		fail("Not yet implemented");
	}

	// @Test
	public void testMergePayment() {
		fail("Not yet implemented");
	}

}
