package be.panidel.dataLayer.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.DataFacade.PosIdentification;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.DataModelInterface;
import be.panidel.dataLayer.model.ItemPaymentModel;
import be.panidel.dataLayer.model.ItemProductModel;

public class CashSaleDao extends GenericDaoImpl<CashSaleModel> {

	private final static Logger LOG = LoggerFactory.getLogger(CashSaleDao.class);

	public static long prefix_TIME = 0;
	public static String LAST_UUID_TIME = "";

	ItemProductDao itemProductDao;
	ItemPaymentDao itemPaymentDao;
	ProductDao productDao;
	PaymentMethodDao paymentMethodDao;

	public CashSaleDao(ItemProductDao itemProductDao, ItemPaymentDao itemPaymentDao, ProductDao productDao,
			PaymentMethodDao paymentMethodDao) {
		super();
		this.itemProductDao = itemProductDao;
		this.itemPaymentDao = itemPaymentDao;
		this.productDao = productDao;
		this.paymentMethodDao = paymentMethodDao;
	}

	/**
	 * @param date
	 * @param posIdentification
	 * @param quickGenerator
	 *            manage quick generated dates
	 * @return
	 */
	public static long getUUID(Date date, PosIdentification posIdentification, boolean quickGenerator) {

		LOG.debug("getUUID START");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String UUIDTime = sdf.format(date) + posIdentification.getIdentifier();
		Long UUID = null;

		if (quickGenerator && LAST_UUID_TIME.equals(UUIDTime)) {
			LAST_UUID_TIME = UUIDTime;
			prefix_TIME = prefix_TIME + 1;
			UUID = new Long(prefix_TIME + UUIDTime);
		} else {
			LAST_UUID_TIME = UUIDTime;
			prefix_TIME = 0;
			UUID = new Long(UUIDTime);
		}

		LOG.debug("getUUID END : " + UUID);

		return UUID;

	}

	/**
	 * Items are permant but list of items can be enlarged !
	 * 
	 * @param cashSaleModel
	 * @param itemProductDao
	 * @param itemPaymentDao
	 * @param productDao
	 * @param paymentMethodDao
	 * @param em
	 * @return
	 * @throws DataLayerException
	 */
	@Override
	public CashSaleModel write(CashSaleModel cashSale, EntityManager em) {

		CashSaleModel persistedCashSale = find(cashSale.getId(), em);

		if (persistedCashSale != null) {

			if ((persistedCashSale.getItemProducts() != null && cashSale.getItemProducts() == null)
					|| (cashSale.getItemProducts().size() < persistedCashSale.getItemProducts().size())) {
				LOG.error("item products list is reduced : [CashSale:" + cashSale + "],[persisted cash sale:"
						+ persistedCashSale + "]");
			}
			if ((persistedCashSale.getItemPayments() != null && cashSale.getItemPayments() == null)
					|| (cashSale.getItemPayments().size() < persistedCashSale.getItemPayments().size())) {
				LOG.error("item payments list is reduced : [CashSale:" + cashSale + "],[persisted cash sale:"
						+ persistedCashSale + "]");
			}

			populate(persistedCashSale, cashSale);

			LOG.debug("CashSale entity update : " + persistedCashSale);

		} else {

			persist(cashSale, em);

			persistedCashSale = cashSale;

			LOG.debug("CashSale entity created : " + persistedCashSale);

		}

		if (persistedCashSale.getItemProducts() != null) {

			Collection<ItemProductModel> persistedItemProducts = new ArrayList<ItemProductModel>();

			for (Iterator<ItemProductModel> itemProductIterator = persistedCashSale.getItemProducts()
					.iterator(); itemProductIterator.hasNext();) {
				ItemProductModel itemProduct = itemProductIterator.next();
				ItemProductModel persistedItemProduct = itemProductDao.write(itemProduct, em);
				persistedItemProducts.add(persistedItemProduct);
			}

			persistedCashSale.setItemProducts(persistedItemProducts);

		}

		if (persistedCashSale.getItemPayments() != null) {

			Collection<ItemPaymentModel> persistedItemPayments = new ArrayList<ItemPaymentModel>();

			for (Iterator<ItemPaymentModel> itemPaymentIterator = persistedCashSale.getItemPayments()
					.iterator(); itemPaymentIterator.hasNext();) {
				ItemPaymentModel itemPayment = itemPaymentIterator.next();
				ItemPaymentModel persistedItemPayment = itemPaymentDao.write(itemPayment, em);
				persistedItemPayments.add(persistedItemPayment);
			}

			persistedCashSale.setItemPayments(persistedItemPayments);

		}

		return persistedCashSale;

	}

	public long count(EntityManager em) {

		long count = (long) em.createNamedQuery(DataModelInterface.POS_CASHSALE_COUNT).getSingleResult();

		return count;

	}

	@Override
	public List<CashSaleModel> findAll(EntityManager em) {

		return em.createNamedQuery(DataModelInterface.POS_CASHSALE_ALL, CashSaleModel.class).getResultList();

	}

	public List<CashSaleModel> findAll(int maxResults, EntityManager em) {

		return em.createNamedQuery(DataModelInterface.POS_CASHSALE_ALL, CashSaleModel.class).setMaxResults(maxResults)
				.getResultList();

	}

	public List<CashSaleModel> getBetweenDates(Date startDate, Date endDate, int maxResults, EntityManager em) {

		List<CashSaleModel> cashSales = null;

		if (maxResults <= 0) {
			cashSales = em.createNamedQuery(DataModelInterface.POS_CASHSALE_BYPERIOD, CashSaleModel.class)
					.setParameter("startDate", startDate, TemporalType.DATE)
					.setParameter("endDate", endDate, TemporalType.DATE).getResultList();
		} else {
			cashSales = em.createNamedQuery(DataModelInterface.POS_CASHSALE_BYPERIOD, CashSaleModel.class)
					.setParameter("startDate", startDate, TemporalType.DATE)
					.setParameter("endDate", endDate, TemporalType.DATE).setMaxResults(maxResults).getResultList();
		}

		return cashSales;

	}

	public long countBetweenPeriod(Date startDate, Date endDate, EntityManager em) {

		long count = (long) em.createNamedQuery(DataModelInterface.POS_CASHSALE_BYPERIOD_COUNT)
				.setParameter("startDate", startDate, TemporalType.DATE)
				.setParameter("endDate", endDate, TemporalType.DATE).setMaxResults(50).getSingleResult();

		return count;

	}

	public void exportAsXml(Date startDate, Date endDate) {

	}

	/*
	 * Items has to be populated out of this process (non-Javadoc)
	 * 
	 * @see be.panidel.dataLayer.dao.GenericDao#populate(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void populate(CashSaleModel persistedCashSale, CashSaleModel cashSale) {

		persistedCashSale.setId(cashSale.getId());
		persistedCashSale.setCashSaleStatus(cashSale.getCashSaleStatus());
		persistedCashSale.setOpenDate(cashSale.getOpenDate());
		persistedCashSale.setEndDate(cashSale.getEndDate());
		persistedCashSale.setIdentifier(cashSale.getIdentifier());
		persistedCashSale.setSource(cashSale.getSource());
		persistedCashSale.setConsumptionPlace(cashSale.getConsumptionPlace());
		persistedCashSale.setCashSaleTotal(cashSale.getCashSaleTotal());
		persistedCashSale.setCashSaleExcludVAT(cashSale.getCashSaleExcludVAT());
		persistedCashSale.setCashSaleDeducedExcludVAT(cashSale.getCashSaleDeducedExcludVAT());
		persistedCashSale.setCashSaleFree(cashSale.getCashSaleFree());
		persistedCashSale.setCashSaleLost(cashSale.getCashSaleLost());
		persistedCashSale.setCashSaleTrash(cashSale.getCashSaleTrash());
		persistedCashSale.setCashSalePaymentTotal(cashSale.getCashSalePaymentTotal());
		persistedCashSale.setCashSaleNbArticles(cashSale.getCashSaleNbArticles());

	}

}