package be.panidel.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public class ComputeSalesOfDayTest {

	// private static final Logger log =
	// Logger.getLogger("ComputeSalesOfDayTest");

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

	// @Test
	// public void byDaySales() {
	//		
	// ComputeSalesOfDay computeSalesOfDay = new ComputeSalesOfDay();
	//
	// == File[] files = FileHelper.getListOfFiles(".xml", null, null, null,
	// null);
	//
	// // log.debug("Compute document");
	// Map<String, DayResult> byDaySales = computeSalesOfDay.byDaySales(files);
	//
	// StringBuffer sb = new StringBuffer();
	// log.debug("START ITERATE byDaySales");
	// for (Iterator<String> iterator = byDaySales.keySet().iterator(); iterator
	// .hasNext();) {
	// String dateAsString = iterator.next();
	// DayResult dr = byDaySales.get(dateAsString);
	// if (dr != null) {
	// dr.appendCSV(sb);
	// } else {
	// sb.append(dateAsString);
	// }
	// sb.append("\r");
	// }
	// log.debug(sb);
	// log.debug("STOP ITERATE byDaySales");
	// }

	// public void byArticlesTest() {
	//
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	// Date date = new Date();
	//
	// ++ File [] files = FileHelper.getListOfFiles("20091025",".xml",
	// POSConstants.STORAGE);
	//
	// try {
	// Document document = FileHelper.readSales(files);
	// DOMUtils.XMLFileWriter(document, sdf.format(date));
	//
	// log.debug("Compute document");
	//
	// // CashRegisterModel posDocument = new
	// ComputeSalesOfDay().byArticles(files);
	// // log.debug("Document computed : " +
	// posDocument.getTicketTable().toXML(false));
	// // posDocument.writeFile("testCompute" + sdf.format(date), false);
	//
	// } catch (IOException e) {
	// log.error("byArticlesTest",e);
	// } catch (ParserConfigurationException e) {
	// log.error("byArticlesTest",e);
	// } catch (SAXException e) {
	// log.error("byArticlesTest",e);
	// } catch (TransformerException e) {
	// log.error("byArticlesTest",e);
	// }
	//		
	// }
}
