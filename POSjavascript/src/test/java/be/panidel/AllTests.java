package be.panidel;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.CashSaleComputationTest;
import be.panidel.businessLayer.CashsaleReportTest;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.DataFacadeTest;
import be.panidel.dataLayer.dao.BussinessTest;
import be.panidel.dataLayer.dao.CashSaleDaoTest;
import be.panidel.dataLayer.dao.PaymentMethodDaoTest;
import be.panidel.dataLayer.dao.ProductDaoTest;
import be.panidel.dataLayer.helper.DAOConfig.PersistenceUnit;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.pos10.tool.ProcessAsCashSalesTest;

@RunWith(Suite.class)
@SuiteClasses({ ProductDaoTest.class, PaymentMethodDaoTest.class, CashSaleDaoTest.class, DataFacadeTest.class,
		BussinessTest.class, CashSaleComputationTest.class, ProcessAsCashSalesTest.class, CashsaleReportTest.class })
public class AllTests {

	public static PersistenceUnit persistenceUnitTest = PersistenceUnit.POS_EMBEDDED;

	private final static Logger LOG = LoggerFactory.getLogger(AllTests.class);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		try {
			DataLayerHelper.selectCurrentPersistenceUnit(persistenceUnitTest);

			if (DataFacade.instance.countProducts() <= 0) {
				LOG.error("There is no product !");
			}
		} catch (Exception e) {
			LOG.error("", e);
			throw new Exception(e);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		LOG.debug("Number of cashsales : " + DataFacade.instance.countCashSales());
		List<ProductModel> products = DataFacade.instance.getAllProducts();
		LOG.debug("Number of products : " + products.size());
	}

}
