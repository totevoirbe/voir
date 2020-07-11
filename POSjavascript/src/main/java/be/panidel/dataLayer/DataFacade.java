package be.panidel.dataLayer;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.dao.CashSaleDao;
import be.panidel.dataLayer.dao.ItemPaymentDao;
import be.panidel.dataLayer.dao.ItemProductDao;
import be.panidel.dataLayer.dao.MarshalHelper;
import be.panidel.dataLayer.dao.PaymentMethodDao;
import be.panidel.dataLayer.dao.ProductDao;
import be.panidel.dataLayer.dao.VatRateDao;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.CashSaleModelXmlList;
import be.panidel.dataLayer.model.ItemPaymentModel;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.dataLayer.model.ProductModel;
import be.panidel.dataLayer.model.VatRateModel;

public class DataFacade {

	private final static Logger LOG = LoggerFactory.getLogger(DataFacade.class);

	public enum PosIdentification {
		SERVER(9), DAGORUE(1), DAGOSALLE(2), UNDEFINED(0);

		int identifier;

		PosIdentification(int identifier) {
			this.identifier = identifier;
		}

		public int getIdentifier() {
			return identifier;
		}

	};

	public enum ConsumptionPlace {
		ON_PLACE, TAKE_AWAY;
	};

	public static final DataFacade instance = new DataFacade();

	private CashSaleDao cashSaleDao;
	private ItemProductDao itemProductDao;
	private ItemPaymentDao itemPaymentDao;
	private ProductDao productDao;
	private PaymentMethodDao paymentMethodDao;
	private VatRateDao vatRateDao;

	private List<VatRateModel> vatRates;

	private DataFacade() {

		vatRateDao = new VatRateDao();
		itemProductDao = new ItemProductDao();
		itemPaymentDao = new ItemPaymentDao();
		productDao = new ProductDao();
		paymentMethodDao = new PaymentMethodDao();
		cashSaleDao = new CashSaleDao(itemProductDao, itemPaymentDao, productDao, paymentMethodDao);

	}

	public static long getUUIDCashSale(Date transactionDate, PosIdentification posIdentification,
			boolean quickGenerator) {
		return CashSaleDao.getUUID(transactionDate, posIdentification, quickGenerator);
	}

	public CashSaleModel createCashSale(CashSaleModel cashSale) throws DataLayerException {

		CashSaleModel cashSalePersisted = null;

		EntityManager em = DataLayerHelper.getNewEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {

			txn.begin();
			cashSalePersisted = createCashSale(cashSale, em);
			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			DataLayerHelper.closeEntityManager(em);
		}

		return cashSalePersisted;

	}

	public CashSaleModel createCashSale(CashSaleModel cashSale, EntityManager em) {

		CashSaleModel persistedCashSale = null;

		persistedCashSale = cashSaleDao.write(cashSale, em);

		return persistedCashSale;

	}

	public CashSaleModel getCashSale(Long id) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();
		CashSaleModel sale = null;
		try {
			sale = cashSaleDao.find(id, em);
		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return sale;

	}

	public CashSaleModel deleteCashSale(CashSaleModel cashSale) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {

			txn.begin();
			cashSaleDao.delete(cashSale.getId(), em);
			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			DataLayerHelper.closeEntityManager(em);
		}
		return cashSale;

	}

	public List<CashSaleModel> getAllCashSales(int maxResults) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		List<CashSaleModel> cashSales = null;
		try {

			if (maxResults > 0) {
				cashSales = cashSaleDao.findAll(maxResults, em);
			} else {
				cashSales = cashSaleDao.findAll(em);
			}

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return cashSales;

	}

	public List<CashSaleModel> getCashSalesBetween(Date startDate, Date endDate, int maxResults)
			throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		List<CashSaleModel> cashSales = null;
		try {

			cashSales = cashSaleDao.getBetweenDates(startDate, endDate, maxResults, em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return cashSales;

	}

	public long countCashSalesBetween(Date startDate, Date endDate, int maxResults) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		try {

			return cashSaleDao.countBetweenPeriod(startDate, endDate, em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

	}

	public long countProducts() throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		long count = 0;
		try {

			count = productDao.count(em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return count;

	}

	public long countCashSales() throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		long count = 0;
		try {

			count = cashSaleDao.count(em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return count;

	}

	public long countCashSales(Date startDate, Date endDate) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		long count = 0;
		try {

			count = cashSaleDao.countBetweenPeriod(startDate, endDate, em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return count;

	}

	public ProductModel createUpdateProduct(ProductModel product, EntityTransaction txn, EntityManager em)
			throws DataLayerException {

		boolean keepAliveEntityManager;
		boolean keepAliveTransaction;

		if (em == null) {
			em = DataLayerHelper.getNewEntityManager();
			keepAliveEntityManager = false;
		} else {
			keepAliveEntityManager = true;
		}

		if (txn == null) {
			txn = em.getTransaction();
			keepAliveTransaction = false;
		} else {
			keepAliveTransaction = true;
		}

		try {

			if (!keepAliveTransaction) {
				txn.begin();
				productDao.write(product, em);
				txn.commit();
			} else {
				productDao.write(product, em);
			}

		} finally {
			if (!keepAliveTransaction) {
				if (txn.isActive()) {
					txn.rollback();
				}
			}
			if (!keepAliveEntityManager) {
				DataLayerHelper.closeEntityManager(em);
			}
		}

		return product;

	}

	public List<ProductModel> getAllProducts() throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		List<ProductModel> productModels = null;
		try {

			productModels = productDao.findAll(em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return productModels;

	}

	public ProductModel getProductById(long id) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		ProductModel product = null;
		try {

			product = productDao.getById(id, em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return product;

	}

	public ProductModel getProductByCode(String code) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		ProductModel product = null;
		try {

			product = productDao.getByCode(code.toUpperCase(), em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return product;

	}

	public PaymentMethodModel getPaymentMethodById(long id) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		PaymentMethodModel paymentMethod = null;
		try {

			paymentMethod = paymentMethodDao.getById(id, em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return paymentMethod;

	}

	public List<PaymentMethodModel> getAllPaymentMethod() throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();

		List<PaymentMethodModel> paymentMethods = null;
		try {

			paymentMethods = paymentMethodDao.findAll(em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		return paymentMethods;

	}

	public List<VatRateModel> getAllVatRates(boolean refresh) throws DataLayerException {

		if (refresh || vatRates == null) {
			EntityManager em = DataLayerHelper.getNewEntityManager();
			try {
				vatRates = vatRateDao.findAll(em);
			} finally {
				DataLayerHelper.closeEntityManager(em);
			}
		}
		return vatRates;

	}

	public VatRateModel getVatRateByCode(String code) throws DataLayerException {

		getAllVatRates(false);

		for (VatRateModel vat : vatRates) {
			if (code.equals(vat.getCode())) {
				return vat;
			}
		}
		return null;

	}

	public void updateDbProducts(Collection<ProductModel> products) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {

			txn.begin();

			for (ProductModel product : products) {

				createUpdateProduct(product, txn, em);

			}

			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			DataLayerHelper.closeEntityManager(em);
		}
	}

	public void updateDbPaymentMethods(Collection<PaymentMethodModel> paymentMethods) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {

			txn.begin();

			for (PaymentMethodModel paymentMethod : paymentMethods) {

				paymentMethodDao.write(paymentMethod, em);

			}

			txn.commit();

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			DataLayerHelper.closeEntityManager(em);
		}
	}

	public void updateDbVatRates(Collection<VatRateModel> vatRates) throws DataLayerException {

		EntityManager em = DataLayerHelper.getNewEntityManager();
		EntityTransaction txn = em.getTransaction();

		try {

			txn.begin();
			for (VatRateModel vatRate : vatRates) {
				vatRateDao.write(vatRate, em);
			}
			txn.commit();
			getAllVatRates(true);

		} finally {
			if (txn.isActive()) {
				txn.rollback();
			}
			DataLayerHelper.closeEntityManager(em);
		}
	}

	public void exportCashSaleAsXml(Date startDate, Date endDate) throws DataLayerException, JAXBException {

		int maxResults = 1000000;

		String startDateAsString = SimpleDateFormat.getDateInstance().format(startDate);
		String endDateAsString = SimpleDateFormat.getDateInstance().format(endDate);

		EntityManager em = DataLayerHelper.getNewEntityManager();

		List<CashSaleModel> cashSales = null;

		try {

			LOG.info("Read data from : " + startDateAsString + ", to : " + endDateAsString + " - number of sales : "
					+ countCashSales(startDate, endDate));

			cashSales = cashSaleDao.getBetweenDates(startDate, endDate, maxResults, em);

		} finally {
			DataLayerHelper.closeEntityManager(em);
		}

		CashSaleModelXmlList cashSaleModelXmlList = new CashSaleModelXmlList();
		cashSaleModelXmlList.setDocument(cashSales);

		String fileName = "Export_" + startDateAsString + "_" + endDateAsString;

		LOG.info("Export cash sales : " + fileName + " - number of sales : " + cashSales.size());

		File file = new File(fileName);

		MarshalHelper.marchalToXml(cashSaleModelXmlList, file);

	}

	public void detach(ItemProductModel itemProduct) throws DataLayerException {
		EntityManager em = DataLayerHelper.getNewEntityManager();
		itemProductDao.detach(itemProduct, em);
	}

	public void detach(ItemPaymentModel itemPayment) throws DataLayerException {
		EntityManager em = DataLayerHelper.getNewEntityManager();
		itemPaymentDao.detach(itemPayment, em);
	}

}
