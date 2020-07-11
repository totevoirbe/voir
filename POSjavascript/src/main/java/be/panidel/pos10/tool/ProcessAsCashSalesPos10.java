package be.panidel.pos10.tool;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.businessLayer.CashSaleComputation;
import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;
import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.DataFacade.ConsumptionPlace;
import be.panidel.dataLayer.DataFacade.PosIdentification;
import be.panidel.dataLayer.dao.MarshalHelper;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DAOConfig;
import be.panidel.dataLayer.helper.DataLayerHelper;
import be.panidel.dataLayer.helper.SysHelper;
import be.panidel.dataLayer.model.CashSaleModel;
import be.panidel.dataLayer.model.ItemProductModel;
import be.panidel.dataLayer.model.PaymentMethodModel;
import be.panidel.frontLayer.model.CashSaleJsonModel;
import be.panidel.frontLayer.model.ItemPaymentJsonModel;
import be.panidel.frontLayer.model.ItemProductJsonModel;
import be.panidel.pos10.dao.ProductPos10Dao;
import be.panidel.pos10.model.Document;
import be.panidel.pos10.model.DocumentList;
import be.panidel.pos10.model.Item;
import be.panidel.pos10.model.PaymentPos10;
import be.panidel.pos10.model.ProductPos10;

public class ProcessAsCashSalesPos10 implements Runnable {

	private final static Logger LOG = LoggerFactory.getLogger(ProcessAsCashSalesPos10.class);

	public static enum CountDirection {
		UP, DOWN
	};

	public static int instanceCounter = 0;

	public int identifiant;
	private DocumentList documentListPos10;
	private String processName;
	private CashSaleComputation cashSaleComputation = new CashSaleComputation();

	synchronized private void updateCounter(CountDirection countDirection) {
		switch (countDirection) {
		case UP:
			instanceCounter++;
			break;
		case DOWN:
			instanceCounter--;
			break;
		}
		;
	};

	public static void pos10Migration(Date fromDay, Date toDay) {
		try {

			int identifiant = 0;

			File fileRepository = new File(DAOConfig.POS10_CASHSALES_REPOSITORY);

			LOG.debug("Reporistory : " + fileRepository.getAbsolutePath());

			File files[] = fileRepository.listFiles(new CashSaleFileFilter(fromDay, toDay));

			if (files == null) {
				LOG.warn("Repository is empty : " + fileRepository.getAbsolutePath());
				return;
			}

			LOG.info("Number of files : " + files.length);

			long lastDisplay = 0;

			for (int i = 0; i < files.length; i++) {

				if (i % 100 == 0) {
					long pourcent = i * 100 / files.length;
					if (lastDisplay != pourcent) {
						lastDisplay = pourcent;
						LOG.info("Done : " + pourcent + " %");
						SysHelper.displayMemoryUtilization(
								"Pos10Migration : " + SimpleDateFormat.getDateTimeInstance().format(fromDay)
										+ SimpleDateFormat.getDateTimeInstance().format(toDay));
					}
				}

				File fileSource = files[i];

				LOG.debug("read file : " + i + ". " + fileSource.getName());

				DocumentList documentListPos10 = (DocumentList) MarshalHelper.unmarchalXml(DocumentList.class,
						fileSource);

				String processName = i + ". " + fileSource.getName() + " / " + files.length;
				LOG.debug(processName + " file extracted.");
				LOG.debug("" + documentListPos10);

				try {

					while (ProcessAsCashSalesPos10.instanceCounter > 3) {
						try {
							LOG.debug("Pool instances full : " + ProcessAsCashSalesPos10.instanceCounter);
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							LOG.info("SLEEP process [" + processName + "]");
						}
					}

					ProcessAsCashSalesPos10 processAsCashSales = new ProcessAsCashSalesPos10(documentListPos10,
							processName, identifiant++);
					Thread thread = new Thread(processAsCashSales);
					thread.start();

				} catch (NumberFormatException e) {
					LOG.error("" + documentListPos10, e);
				}

			}

			LOG.debug("WAIT FOR CLOSE");

			while (ProcessAsCashSalesPos10.instanceCounter > 0) {
				try {
					LOG.debug("Wait all instances cleaned : " + ProcessAsCashSalesPos10.instanceCounter);
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					LOG.info("Wait NO process [" + ProcessAsCashSalesPos10.instanceCounter + "]");
				}
			}

			LOG.debug("DONE");

		} catch (JAXBException e) {
			LOG.error("", e);
		}
	}

	public static GregorianCalendar fromTextYYYYMMDDToDate(String inputTxt) {

		if (inputTxt.length() < 8) {
			return null;
		}

		String yearAsTxt = inputTxt.substring(0, 4);
		String monthAsTxt = inputTxt.substring(4, 6);
		String dayAsTxt = inputTxt.substring(6, 8);

		int year = new Integer(yearAsTxt);
		int month = new Integer(monthAsTxt) - 1;
		int day = new Integer(dayAsTxt);

		GregorianCalendar date = new GregorianCalendar(year, month, day);

		return date;

	}

	public ProcessAsCashSalesPos10(DocumentList documentListPos10, String processName, int identifiant) {

		updateCounter(CountDirection.UP);

		this.identifiant = identifiant;
		this.documentListPos10 = documentListPos10;
		this.processName = processName;
		if (documentListPos10 == null) {
			LOG.info("[" + identifiant + "] New thread from " + instanceCounter + " document list is null");
		} else {
			LOG.debug("[" + identifiant + "] New thread from " + instanceCounter + ", date : "
					+ documentListPos10.getDate() + ", number : " + documentListPos10.getDocument().size());
		}

	}

	@Override
	public void run() {

		EntityManager em = null;
		EntityTransaction txn = null;

		Date startDate = new Date();

		try {

			em = DataLayerHelper.getNewEntityManager();
			txn = em.getTransaction();

			Document[] documents = new Document[documentListPos10.getDocument().size()];
			documentListPos10.getDocument().toArray(documents);

			for (int i = 0; i < documents.length; i++) {

				txn.begin();

				long warnLevel = 95;
				long errorLevel = 98;
				String message = "ProcessAsCashSalesPos10";

				SysHelper.checkMemoryLevelAndWarn(warnLevel, errorLevel, message);

				Document documentPos10 = documents[i];

				LOG.debug("[" + identifiant + "] Create : " + instanceCounter + ", date : "
						+ documentListPos10.getDate() + ", number : " + documentListPos10.getDocument().size());

				PosIdentification posIdentification = null;

				switch (documentPos10.getComputername()) {

				case "DAGOSALLE":
					posIdentification = PosIdentification.DAGOSALLE;
					break;
				case "DAGORUE":
					posIdentification = PosIdentification.DAGORUE;
					break;
				case "SERVER":
					posIdentification = PosIdentification.SERVER;
					break;
				default:
					posIdentification = PosIdentification.UNDEFINED;
					break;
				}

				Date transactionDate = new Date(new Long(documentPos10.getDate()));

				Long id = DataFacade.getUUIDCashSale(transactionDate, posIdentification, false);

				if (DataFacade.instance.getCashSale(id) == null) {
					LOG.debug("[" + identifiant + "] Cash sale exist : " + instanceCounter + ", date : "
							+ documentListPos10.getDate() + ", number : " + documentListPos10.getDocument().size());

					CashSaleStatus cashSaleStatus = (documentPos10.getCancelled() != null
							&& documentPos10.getCancelled() ? CashSaleStatus.CANCEL : CashSaleStatus.DONE);

					Date openDate = transactionDate;
					Date endDate = transactionDate;
					String identifier = documentListPos10.getDate();
					String source = documentPos10.getSource();

					ConsumptionPlace consumptionPlace = null;
					if (documentPos10.getTakeonplace() != null && documentPos10.getTakeonplace()) {
						consumptionPlace = ConsumptionPlace.ON_PLACE;
					} else {
						consumptionPlace = ConsumptionPlace.TAKE_AWAY;
					}

					Collection<ItemPaymentJsonModel> itemPaymentJsons = new ArrayList<>();
					Collection<ItemProductJsonModel> itemProductJsons = new ArrayList<>();

					if (documentPos10.getItem() != null) {

						Item[] itempos10 = new Item[documentPos10.getItem().size()];
						documentPos10.getItem().toArray(itempos10);

						for (int j = 0; j < itempos10.length; j++) {

							Item itemPos10 = itempos10[j];

							ProductPos10 productPos10 = ProductPos10Dao.instance.getById(itemPos10.getProduct());

							ItemProductJsonModel itemProductJson = null;

							if (productPos10 == null) {

								LOG.warn("[" + identifiant + "] Product pos 10 not found : " + itemPos10.getProduct()
										+ "/" + itemPos10.getDescription());

							} else {

								String productPos10Code = productPos10.getCode();

								itemProductJson = mapProductPos10toProduct(itemPos10, productPos10Code);

								itemProductJsons.add(itemProductJson);

							}

						}
					} else {
						LOG.debug(
								"[" + identifiant + "] CshSale without productsitems : " + documentListPos10.getDate());
					}

					if (documentPos10.getPayement() != null) {

						PaymentPos10[] paymentpos10List = new PaymentPos10[documentPos10.getPayement().size()];
						documentPos10.getPayement().toArray(paymentpos10List);
						for (int j = 0; j < paymentpos10List.length; j++) {

							PaymentPos10 paymentPos10 = paymentpos10List[j];

							PaymentMethodModel paymentMethod = DataFacade.instance
									.getPaymentMethodById(new Long(paymentPos10.getMode()));

							if (paymentMethod == null) {

								LOG.warn("[" + identifiant + "] Payment method pos 10 not found : "
										+ paymentPos10.getMode() + "/" + paymentPos10.getDescription());

							} else {

								BigDecimal quantity = paymentPos10.getQuantity();
								BigDecimal unitPrice = paymentPos10.getValue();
								Boolean deleted = false;

								ItemPaymentJsonModel itemPaymentJson = new ItemPaymentJsonModel(paymentMethod.getId(),
										quantity, unitPrice, deleted);

								itemPaymentJsons.add(itemPaymentJson);

							}

						}
					} else {
						LOG.debug("[" + identifiant + "] CashSale without payment items : "
								+ documentListPos10.getDate());
					}

					BigDecimal cashSaleTotal = null;
					BigDecimal cashSaleExcludVAT = null;
					BigDecimal cashSaleDeducedExcludVAT = null;
					BigDecimal cashSaleFree = null;
					BigDecimal cashSaleLost = null;
					BigDecimal cashSaleTrash = null;
					BigDecimal cashSalePaymentTotal = null;
					BigDecimal cashSaleNbArticles = null;

					CashSaleJsonModel cashSaleJson = new CashSaleJsonModel(id, cashSaleStatus, openDate, endDate,
							identifier, source, consumptionPlace, cashSaleTotal, cashSaleExcludVAT,
							cashSaleDeducedExcludVAT, cashSaleFree, cashSaleLost, cashSaleTrash, cashSalePaymentTotal,
							cashSaleNbArticles, itemPaymentJsons, itemProductJsons);

					LOG.debug("[" + identifiant + "] write : " + cashSaleJson.getId() + " persisted");

					CashSaleModel cashSale = cashSaleComputation.writeCashSale(cashSaleJson, em);

					LOG.debug("[" + identifiant + "] " + cashSale.getId() + " persisted");

				}
				txn.commit();
			}
		} catch (DataLayerException e) {
			LOG.error("[" + identifiant + "] ", e);
		} finally {

			long time = (new Date().getTime() - startDate.getTime()) / 1000 / 60;
			LOG.debug("[" + identifiant + "] END process [" + processName + "] in " + time + "sec.");
			updateCounter(CountDirection.DOWN);

			if (txn.isActive()) {
				txn.rollback();
			}

			DataLayerHelper.closeEntityManager(em);

		}

	}

	protected ItemProductJsonModel mapProductPos10toProduct(Item itemPos10, String productCodePos10)
			throws DataLayerException {

		ItemProductModel itemProduct = ProductPos10Dao.instance.getProduct(productCodePos10);

		if (itemProduct != null && itemProduct.getProduct() != null) {

			LOG.debug("[" + identifiant + "] Product found : " + productCodePos10 + "-"
					+ itemProduct.getProduct().getCode());

			Long productId = itemProduct.getProduct().getId();
			BigDecimal quantity = itemPos10.getQuantity();
			BigDecimal unitPrice = itemPos10.getUnitPrice();
			Boolean deleted = null;
			PriceCategory priceCategory = itemProduct.getPriceCategory();

			ItemProductJsonModel itemProductJson = new ItemProductJsonModel(productId, quantity, unitPrice, deleted,
					priceCategory);

			return itemProductJson;

		} else {

			LOG.warn("[" + identifiant + "] Product not found should be created : " + productCodePos10);

			return null;

		}
	}

}
