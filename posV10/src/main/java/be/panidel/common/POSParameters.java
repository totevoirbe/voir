package be.panidel.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.log4j.Logger;

import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.FileHelper;

public class POSParameters extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("AbstractPOSParameters");

	boolean hasBeenUpdated = false;
	Properties sourceProperties;
	File datatables;

	private static final int TYPE_STRING = 0;
	private static final int TYPE_BIGDECIMAL = 1;
	private static final int TYPE_REFFILE = 2;
	private static final int TYPE_SOURCE = 3;
	private static final int TYPE_INTEGER = 4;
	private static final int TYPE_BOOLEAN = 5;
	private static final int TYPE_STRING_ARRAY = 6;

	private static final String POS_LIST = "posList";

	private static final String CASHREGISTER_STORAGE_CAISSES_SALES = "posStorCseSales";
	private static final String CASHREGISTER_STORAGE_CAISSES_GROUPBYDAY = "posStorCseGrp";
	private static final String CASHREGISTER_STORAGE_CAISSES_ARCHIVES = "posStorCseArc";
	private static final String CASHREGISTER_STORAGE_CAISSES_REJECTED = "posStorCseRej";

	private static final String RESULTS_STORAGE_CAISSES_SALES = "resStorCseSales";
	private static final String RESULTS_STORAGE_CAISSES_GROUPBYDAY = "resStorCseGrp";
	private static final String RESULTS_STORAGE_CAISSES_ARCHIVES = "resStorCseArc";
	private static final String RESULTS_STORAGE_CAISSES_REJECTED = "resStorCseRej";

	private static final String XML_DATATABLES_FILE = "datablesDir";

	private static final String XML_COMPANY_FILE = "companyFile";
	private static final String XML_EMPLOYEES_FILE = "employeesFile";
	private static final String XML_KBLAYOUT_FILE = "kbLayoutFile";
	private static final String XML_PAYEMENTMODE_FILE = "payementmodeFile";
	private static final String XML_PRODUCTS_FILE = "productsFile";
	private static final String XML_RAWPRODUCTS_FILE = "rawProductsFile";
	private static final String XML_GROUPS_FILE = "groupsFile";

	private static final String TVA_TAKEONPLACE = "tvaTakeOnPlace";
	private static final String TVA_TAKEAWAY = "tvaTakeAway";
	private static final String TVA_LIST = "tvaList";

	private static final String PAYID_AS_SALE = "payIdAsSale";
	private static final String PAYID_AS_CA = "payIdAsCA";
	private static final String PAYID_CASH = "payIdCash";

	private static final String ITEM_SALE_AMOUNT_MAX = "itemSaleAmountMax";
	private static final String ITEM_SALE_TOTAL_MAX = "itemSaleTotalMax";
	private static final String ITEM_PAY_TOTAL_MAX = "itemPayTotalMax";
	private static final String ITEM_PAY_MAX = "itemPayMax";

	private static final String PERIODIC_EVENT_SCAN = "periodicEventScan";
	private static final String EMAIL_SYNC_PERIODICITY = "eMailSyncPeriodicity";
	private static final String POS_ISIDLE_IN_MINUTES = "posIsIdleInMinutes";
	private static final String SALE_ISIDLE_IN_MINUTES = "saleIsIdleInMinutes";
	private static final String SYNC_SEND_ENABLED = "syncSendEnabled";
	private static final String SYNC_RECEIVE_ENABLED = "syncReceiveEnabled";
	private static final String SYNC_SMTP_HOST = "syncSmtpHost";
	private static final String SYNC_POP_HOST = "syncPopHost";

	/**
	 * Sales exchenage eMailBox
	 */
	private static final String SYNC_EMAIL_BOX = "syncEMailBox";
	private static final String SYNC_EMAIL_USER = "syncEMailUser";
	private static final String SYNC_EMAIL_PWD = "syncEMailPwd";
	/**
	 * List of manager eMailBox for reporting
	 */
	private static final String MGMT_EMAIL_BOX = "mgmtEMailBox";

	private static final String SYNC_MSG_FROM = "syncMsgFrom";
	private static final String SYNC_MSG_RECIPIENT_LIST = "syncMsgRecipientList";
	private static final String SYNC_MSG_SUBJECT_PREFIX = "syncMsgSubjectPrefix";

	private static POSParameters instance;
	private static Object lock = new Object();

	public static POSParameters instance() throws ParameterException {
		if (instance == null) {
			synchronized (lock) {
				instance = new POSParameters();
			}
		}
		return instance;
	}

	public synchronized String toString() {

		CoupleMessages cm = new CoupleMessages();
		for (Object key : this.keySet()) {
			cm.put(key.toString(), this.get(key));
		}
		return cm.toString();
	};

	private POSParameters() throws ParameterException {

		String userDir = System.getProperty("user.dir");
		log.debug("Parameters directory:" + userDir);
		File dirParam = FileHelper.getOrCreateStorage(userDir);
		File file = new File(dirParam, "posparams.xml");
		if (!file.isFile()) {
			hasBeenUpdated = true;
		} else {
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				loadFromXML(is);
			} catch (FileNotFoundException e) {
				log.error(file, e);
			} catch (InvalidPropertiesFormatException e) {
				log.error(file, e);
			} catch (IOException e) {
				log.error(file, e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					log.error(file, e);
				}
			}
		}

		sourceProperties = new Properties();

		for (Object key : keySet()) {
			sourceProperties.put(key, get(key));
		}

		try {
			put(XML_DATATABLES_FILE, TYPE_STRING, "C:/Caisses/dataTables");

			put(POS_LIST, TYPE_STRING_ARRAY, "DAGOSALLE,DAGORUE");

			put(CASHREGISTER_STORAGE_CAISSES_SALES, TYPE_SOURCE,
					"C:/TestCaisses");
			put(CASHREGISTER_STORAGE_CAISSES_GROUPBYDAY, TYPE_SOURCE,
					"C:/TestCaisseGroup");
			put(CASHREGISTER_STORAGE_CAISSES_ARCHIVES, TYPE_SOURCE,
					"C:/TestCaisseArchive");
			put(CASHREGISTER_STORAGE_CAISSES_REJECTED, TYPE_SOURCE,
					"C:/TestCaisseRejected");
			put(RESULTS_STORAGE_CAISSES_SALES, TYPE_SOURCE, "C:/Caisses");
			put(RESULTS_STORAGE_CAISSES_GROUPBYDAY, TYPE_SOURCE,
					"C:/CaisseGroup");
			put(RESULTS_STORAGE_CAISSES_ARCHIVES, TYPE_SOURCE,
					"C:/CaisseArchive");
			put(RESULTS_STORAGE_CAISSES_REJECTED, TYPE_SOURCE,
					"C:/CaisseRejected");

			put(XML_COMPANY_FILE, TYPE_REFFILE, "company.xml");
			put(XML_EMPLOYEES_FILE, TYPE_REFFILE, "employees.xml");
			put(XML_KBLAYOUT_FILE, TYPE_REFFILE, "kbLayout.xml");
			put(XML_PAYEMENTMODE_FILE, TYPE_REFFILE, "payementmode.xml");
			put(XML_PRODUCTS_FILE, TYPE_REFFILE, "products.xml");
			put(XML_RAWPRODUCTS_FILE, TYPE_REFFILE, "rawProducts.xml");
			put(XML_GROUPS_FILE, TYPE_REFFILE, "groups.xml");

			put(TVA_TAKEONPLACE, TYPE_BIGDECIMAL, "21");
			put(TVA_TAKEAWAY, TYPE_BIGDECIMAL, "6");
			put(TVA_LIST, TYPE_STRING, "6,12,21");

			put(PAYID_AS_SALE, TYPE_STRING, "40,50,60,70");
			put(PAYID_AS_CA, TYPE_STRING, "50,60");
			put(PAYID_CASH, TYPE_STRING, "60");

			put(ITEM_SALE_AMOUNT_MAX, TYPE_BIGDECIMAL, "20");
			put(ITEM_SALE_TOTAL_MAX, TYPE_BIGDECIMAL, "100");

			put(ITEM_PAY_TOTAL_MAX, TYPE_BIGDECIMAL, "300");
			put(ITEM_PAY_MAX, TYPE_BIGDECIMAL, "501");

			put(PERIODIC_EVENT_SCAN, TYPE_INTEGER, "10");
			put(EMAIL_SYNC_PERIODICITY, TYPE_INTEGER, "60");
			put(POS_ISIDLE_IN_MINUTES, TYPE_INTEGER, "3");
			put(SALE_ISIDLE_IN_MINUTES, TYPE_INTEGER, "5");
			put(SYNC_SEND_ENABLED, TYPE_BOOLEAN, "false");
			put(SYNC_RECEIVE_ENABLED, TYPE_BOOLEAN, "false");

			put(SYNC_SMTP_HOST, TYPE_STRING, "relay.skynet.be");
			// SYNC_SMTP_HOST = "relay.skynet.be"; // OK dago
			// SYNC_SMTP_HOST = "smtp.tvcablenet.be";
			// SYNC_SMTP_HOST = "smtpmail.active24.com"; // OK aubay
			// SYNC_SMTP_HOST = "smtp01.mbn1.net";
			put(SYNC_POP_HOST, TYPE_STRING, "pop01.mbn1.net");
			// SYNC_POP_HOST = "pop01.mbn1.net";
			// SYNC_POP_HOST = "imap.gmail.com";

			put(SYNC_EMAIL_BOX, TYPE_STRING_ARRAY, "link@dagoarlon.be");
			put(SYNC_EMAIL_USER, TYPE_STRING, "link@dagoarlon.be");
			put(SYNC_EMAIL_PWD, TYPE_STRING, "7H7sBGjUF8yeGUwtvC20");
			put(MGMT_EMAIL_BOX, TYPE_STRING_ARRAY,
					"christine@voir.be,philippe@voir.be");

			put(SYNC_MSG_FROM, TYPE_STRING, "philfran@voir.be");
			put(SYNC_MSG_RECIPIENT_LIST, TYPE_STRING_ARRAY,
					"solution.architect.solution.it@gmail.com, philippe@voir.be");
			put(SYNC_MSG_SUBJECT_PREFIX, TYPE_STRING, "msg_from_");

			if (hasBeenUpdated) {
				OutputStream os = null;
				try {
					os = new FileOutputStream(file);
					sourceProperties.storeToXML(os, null);
				} catch (FileNotFoundException e) {
					log.error(file, e);
				} catch (IOException e) {
					log.error(file, e);
				} finally {
					try {
						os.flush();
						os.close();
					} catch (IOException e) {
						log.error(file, e);
					}
				}
			}
		} catch (ParameterException e) {
			throw new ParameterException(toString(), e);
		}
	}

	public String[] getPosList() {
		return (String[]) get(POS_LIST);
	}

	public File getCashregisterStorageCaissesSales() {
		return (File) get(CASHREGISTER_STORAGE_CAISSES_SALES);
	}

	public File getCashregisterStorageCaissesGroupbyday() {
		return (File) get(CASHREGISTER_STORAGE_CAISSES_GROUPBYDAY);
	}

	public File getCashregisterStorageCaissesArchives() {
		return (File) get(CASHREGISTER_STORAGE_CAISSES_ARCHIVES);
	}

	public File getCashregisterStorageCaissesRejected() {
		return (File) get(CASHREGISTER_STORAGE_CAISSES_REJECTED);
	}

	public File getResultsStorageCaissesSales() {
		return (File) get(RESULTS_STORAGE_CAISSES_SALES);
	}

	public File getResultsStorageCaissesGroupbyday() {
		return (File) get(RESULTS_STORAGE_CAISSES_GROUPBYDAY);
	}

	public File getResultsStorageCaissesArchives() {
		return (File) get(RESULTS_STORAGE_CAISSES_ARCHIVES);
	}

	public File getResultsStorageCaissesRejected() {
		return (File) get(RESULTS_STORAGE_CAISSES_REJECTED);
	}

	public File getXmlCompanyFile() {
		return (File) get(XML_COMPANY_FILE);
	}

	public File getXmlEmployeesFile() {
		return (File) get(XML_EMPLOYEES_FILE);
	}

	public File getXmlKblayoutFile() {
		return (File) get(XML_KBLAYOUT_FILE);
	}

	public File getXmlPayementmodeFile() {
		return (File) get(XML_PAYEMENTMODE_FILE);
	}

	public File getXmlProductsFile() {
		return (File) get(XML_PRODUCTS_FILE);
	}

	public File getXmlRawProductsFile() {
		return (File) get(XML_RAWPRODUCTS_FILE);
	}

	public File getXmlGroupsFile() {
		return (File) get(XML_GROUPS_FILE);
	}

	public BigDecimal getTVATakeOnPlace() {
		return (BigDecimal) get(TVA_TAKEONPLACE);
	}

	public BigDecimal getTVATakeAway() {
		return (BigDecimal) get(TVA_TAKEAWAY);
	}

	public String getTVAList() {
		return (String) get(TVA_LIST);
	}

	public String getPayidAsSale() {
		return (String) get(PAYID_AS_SALE);
	}

	public String getPayidAsCa() {
		return (String) get(PAYID_AS_CA);
	}

	public String getPayidAsCash() {
		return (String) get(PAYID_CASH);
	}

	public BigDecimal getItemSaleAmountMax() {
		return (BigDecimal) get(ITEM_SALE_AMOUNT_MAX);
	}

	public BigDecimal getItemSaleTotalMax() {
		return (BigDecimal) get(ITEM_SALE_TOTAL_MAX);
	}

	public BigDecimal getItemPayTotalMax() {
		return (BigDecimal) get(ITEM_PAY_TOTAL_MAX);
	}

	public BigDecimal getItemPayMax() {
		return (BigDecimal) get(ITEM_PAY_MAX);
	}

	public int getPeriodicEventScan() {
		return ((Integer) get(PERIODIC_EVENT_SCAN)).intValue();
	}

	public int getEMailSyncPeriodicity() {
		return ((Integer) get(EMAIL_SYNC_PERIODICITY)).intValue();
	}

	public int getPosIsIdleInMinutes() {
		return ((Integer) get(POS_ISIDLE_IN_MINUTES)).intValue();
	}

	public int getSaleIsIdleInMinutes() {
		return ((Integer) get(SALE_ISIDLE_IN_MINUTES)).intValue();
	}

	public boolean getSyncSendEnabled() {
		return ((Boolean) get(SYNC_SEND_ENABLED)).booleanValue();
	}

	public boolean getSyncReceiveEnabled() {
		return ((Boolean) get(SYNC_RECEIVE_ENABLED)).booleanValue();
	}

	public String getSyncSmtpHost() {
		return (String) get(SYNC_SMTP_HOST);
	}

	public String getSyncPopHost() {
		return (String) get(SYNC_POP_HOST);
	}

	/**
	 * Sales exchange eMail box
	 * 
	 * @return
	 */
	public String[] getSyncEMailBox() {
		return (String[]) get(SYNC_EMAIL_BOX);
	}

	/**
	 * List of manager for reporting
	 * 
	 * @return
	 */
	public String[] getMgmtEMailBox() {
		return (String[]) get(MGMT_EMAIL_BOX);
	}

	public String getSyncEMailUser() {
		return (String) get(SYNC_EMAIL_USER);
	}

	public String getSyncEMailPwd() {
		return (String) get(SYNC_EMAIL_PWD);
	}

	public String getSyncMsgFrom() {
		return (String) get(SYNC_MSG_FROM);
	}

	public String[] getSyncMsgRecipientList() {
		return (String[]) get(SYNC_MSG_RECIPIENT_LIST);
	}

	public String getSyncMsgSubjectPrefix() {
		return (String) get(SYNC_MSG_SUBJECT_PREFIX);
	}

	private void put(String key, int type, String defaultValue)
			throws ParameterException {
		Object value = get(key);
		Object putValue = null;
		if (value == null) {
			hasBeenUpdated = true;
			sourceProperties.put(key, defaultValue);
			value = defaultValue;
			log.error("Value is empty for key : " + key
					+ " Set default value : " + defaultValue);
		}
		String valueAsTring = (String) value;
		switch (type) {
		case TYPE_STRING:
			putValue = (String) value;
			break;
		case TYPE_REFFILE:
			if (datatables == null) {
				Object xmlDatatablesFile = get(XML_DATATABLES_FILE);
				if (xmlDatatablesFile != null) {
					datatables = FileHelper
							.getOrCreateStorage((String) xmlDatatablesFile);
				} else {
					throw new ParameterException(
							"XML_DATATABLES_FILE parameter is null");
				}
			}
			putValue = new File(datatables, valueAsTring);
			break;
		case TYPE_SOURCE:
			putValue = FileHelper.getOrCreateStorage(valueAsTring);
			break;
		case TYPE_BIGDECIMAL:
			putValue = new BigDecimal(valueAsTring);
			break;
		case TYPE_INTEGER:
			putValue = new Integer(valueAsTring);
			break;
		case TYPE_BOOLEAN:
			putValue = new Boolean(valueAsTring);
			break;
		case TYPE_STRING_ARRAY:
			putValue = ((String) value).split(",");
			break;

		default:
			log.error("Illegal type : " + type);
			break;
		}

		super.put(key, putValue);
	}
}