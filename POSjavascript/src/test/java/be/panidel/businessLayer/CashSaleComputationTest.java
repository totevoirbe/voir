package be.panidel.businessLayer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CashSaleComputationTest {

	// private final static Logger LOG =
	// LoggerFactory.getLogger(CashSaleComputationTest.class);

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
	public void testCashSalePersistFrontTestJsonModel() {

		// try {
		//
		// CashSaleComputation cashSaleComputation = new CashSaleComputation();
		//
		// Collection<CashSaleJsonModel> cashSaleJsons = new ArrayList<>();
		//
		// CashSaleModel cashSaleModel1 = ModelMakerForTest.cashSaleMock();
		// CashSaleModel cashSaleModel2 = ModelMakerForTest.cashSaleMock();
		//
		// cashSaleJsons.add(ModelMapping.map(cashSaleModel1, new BigDecimal(1)));
		// cashSaleJsons.add(ModelMapping.map(cashSaleModel2, new BigDecimal(1)));
		//
		// FrontJsonCheckModel frontTestJson = new FrontJsonCheckModel();
		//
		// frontTestJson.setCashSaleJsons(cashSaleJsons);
		//
		// CashSaleValuatorModel cashSaleValuatorOPEN = null;
		// CashSaleValuatorModel cashSaleValuatorCANCEL = null;
		// CashSaleValuatorModel cashSaleValuatorDONE = new CashSaleValuatorModel();
		//
		// frontTestJson.setCashSaleValuatorCANCEL(cashSaleValuatorCANCEL);
		// frontTestJson.setCashSaleValuatorDONE(cashSaleValuatorDONE);
		// frontTestJson.setCashSaleValuatorOPEN(cashSaleValuatorOPEN);
		//
		// Collection<String> errors = new ArrayList<>();
		//
		// cashSaleValuatorDONE.setCount(2);
		// cashSaleValuatorDONE
		// .setCashSaleTotal(cashSaleModel1.getCashSaleTotal().add(cashSaleModel2.getCashSaleTotal()));
		// cashSaleValuatorDONE
		// .setPaymentTotal(cashSaleModel1.getPaymentTotal().add(cashSaleModel2.getPaymentTotal()));
		// cashSaleValuatorDONE.setNbArticles(cashSaleModel1.getNbArticles().add(cashSaleModel2.getNbArticles()));
		// cashSaleValuatorDONE.setRemainValue(cashSaleModel1.getRemainValue().add(cashSaleModel2.getRemainValue()));
		//
		// cashSaleComputation.reset();
		// cashSaleComputation.cashSalePersist(frontTestJson, errors);
		//
		// cashSaleComputation.reset();
		// cashSaleComputation.cashSalePersist(frontTestJson, errors);
		//
		// cashSaleComputation.reset();
		// cashSaleComputation.cashSalePersist(frontTestJson, errors);
		//
		// cashSaleComputation.reset();
		// cashSaleComputation.cashSalePersist(frontTestJson, errors);
		//
		// if (errors != null && errors.size() > 0) {
		// for (String error : errors) {
		// LOG.debug(error);
		// }
		// fail("Errors : " + errors);
		// }
		// } catch (DataLayerException e) {
		// LOG.error("", e);
		// fail(e.getMessage());
		// }

	}

}
