package be.panidel.dao;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import be.panidel.common.CoupleMessages;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Company;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.Person;
import be.panidel.management.impl.PeriodResult;
import be.panidel.management.impl.OperationUnitImpl;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.DOMUtils;
import be.panidel.tools.FileIdentifier;
import be.panidel.tools.FileIdentifierList;
import be.panidel.tools.Tools;
import be.panidel.tools.XMLHelper;
import be.panidel.tools.salesFileFilter.SalesFileFilterByPeriodImpl;

public class OperationUnitDAO extends SalesDAO {

	private static final Logger log = Logger.getLogger("OperationUnitDAO");

	public final static String ATTRIBUTE_SOURCE = "source";
	public final static String ATTRIBUTE_CANCELLED = "cancelled";
	public final static String TAG_DOCUMENT = "document";
	public final static String TAG_DOCUMENT_LIST = "documentlist";
	public final static String TAG_PERSONNEL = "personnel";
	public final static String TAG_DATE = "date";
	public final static String TAG_COMPANY = "company";
	public final static String TAG_COMPUTERNAME = "computername";
	public final static String TAG_TAKEONPLACE = "takeonplace";

	public static final String TYPE_OF_UNIT_DAY = "date";
	public static final String TYPE_OF_UNIT_MONTH = "month";
	public static final String TYPE_OF_UNIT_TEST = "test";
	private static final String[] TYPE_OF_UNIT_LIST = { TYPE_OF_UNIT_DAY,
			TYPE_OF_UNIT_MONTH, TYPE_OF_UNIT_TEST };

	public OperationUnitList getById(String fileName, String repository)
			throws DAOException {
		return getById(fileName, new File(repository));
	}

	public OperationUnitList getById(String fileName, File repository)
			throws DAOException {

		OperationUnitList operationUnitList = new OperationUnitList();

		File file = new File(repository, fileName);
		extractSalesFromFile(file, operationUnitList);

		return operationUnitList;
	}

	public Map<StringReverseOrder, PeriodResult> getDayResultList(
			PeriodBean periodBean, File storage) throws DAOException {
		return getDayResultList(periodBean, storage, false);
	}

	public Map<StringReverseOrder, PeriodResult> getDayResultListForStat(
			PeriodBean periodBean, File storage) throws DAOException {
		return getDayResultList(periodBean, storage, true);
	}

	private Map<StringReverseOrder, PeriodResult> getDayResultList(
			PeriodBean periodBean, File storage, boolean onlyForStat)
			throws DAOException {

		Map<StringReverseOrder, PeriodResult> dayResultList = new TreeMap<StringReverseOrder, PeriodResult>();

		File[] fileList = storage.listFiles(new SalesFileFilterByPeriodImpl(
				periodBean));

		PeriodResult periodResult = null;

		for (int i = 0; i < fileList.length; i++) {
			File file = fileList[i];
			try {

				String fileName = file.getName();
				Date day = Tools.extractDayFromSalesFileNameAsDate(fileName);
				if (day == null) {
					throw new DAOException(file.getAbsolutePath());
				}
				periodResult = new PeriodResult(new PeriodBean(
						Tools.startOfCurentDay(day), Tools.endOfDay(day)),
						storage, onlyForStat);
				dayResultList.put(
						new StringReverseOrder(fileName.substring(0, 8)),
						periodResult);

			} catch (ParseException e) {
				throw new DAOException(file.getAbsolutePath(), e);
			} catch (ParameterException e) {
				throw new DAOException(file.getAbsolutePath(), e);
			}
		}
		return dayResultList;
	}

	public OperationUnitList extractSalesOfPeriod(File storage,
			PeriodBean period) {
		FileIdentifierList fileIdentifierList = new FileIdentifierList(storage,
				period.getStartDate(), period.getEndDate());
		OperationUnitList operationUnitList = FacadeDAO.instance()
				.getOperationUnitDAO().getOperationUnit(fileIdentifierList);

		return operationUnitList;
	}

	public OperationUnitList getOperationUnit(FileIdentifierList files) {

		OperationUnitList operationUnitList = new OperationUnitList();

		getOperationUnit(files, operationUnitList);

		return operationUnitList;

	}

	private void getOperationUnit(FileIdentifierList files,
			OperationUnitList operationUnitList) {

		log.debug("Start getOpationIdentier");

		for (FileIdentifier fileIdentifier : files) {
			if (fileIdentifier != null) {
				try {
					extractSalesFromFile(fileIdentifier.getFile(),
							operationUnitList);
				} catch (DAOException e) {
					log.error("", e);
					fileIdentifier.setRejected();
				}
			} else {
				log.error("file is null");
			}
		}

		log.debug("end");

	}

	public void writeOperationUnitList(OperationUnitList operationUnitList,
			String day, String typeOfUnit, File file) throws DAOException {

		if (Arrays.binarySearch(TYPE_OF_UNIT_LIST, typeOfUnit) < 0) {
			throw new DAOException(typeOfUnit + " illegal type");
		}

		StringBuffer xml = new StringBuffer();

		String[][] attributeList = null;
		attributeList = new String[1][];
		attributeList[0] = new String[] { typeOfUnit, day };

		XMLHelper.openTag(attributeList, TAG_DOCUMENT_LIST, xml);

		for (StringReverseOrder fileNameForList : operationUnitList.keySet()) {
			FacadeDAO.instance().getOperationUnitDAO()
					.appendXml(operationUnitList.get(fileNameForList), xml);
		}

		XMLHelper.closeTag(TAG_DOCUMENT_LIST, xml);

		writeSalesToFile(xml.toString(), file);

	}

	protected void extractSalesFromFile(File file,
			OperationUnitList operationUnitList) throws DAOException {

		OperationUnitList workList = new OperationUnitList();

		NodeList nodelist = DOMUtils.getDocumentsAsList(file, TAG_DOCUMENT);

		Date fileDate = Tools.extractDayFromSalesFileNameAsDate(file.getName());

		if (fileDate == null) {
			throw new DAOException(file.getAbsolutePath());
		}

		// Process the elements in the nodelist
		for (int i = 0; i < nodelist.getLength(); i++) {
			// Get element
			Element elem = (Element) nodelist.item(i);

			OperationUnit operationUnit = extractOperationUnit(elem);

			if (Tools.isNullOrEmpty(operationUnit.getFileName())) {

				String fileName = file.getName();
				operationUnit.setFileName(fileName.substring(0,
						fileName.length() - 4));
			}

			workList.put(new StringReverseOrder(operationUnit.getFileName()),
					operationUnit);
		}

		if (workList.size() > 0) {
			operationUnitList.putAll(workList);
		}

	}

	private OperationUnit extractOperationUnit(Element root)
			throws DAOException {

		Person employee = null;
		Date time = null;
		Company company = null;
		String computerName = null;
		boolean cancelled = false;
		boolean takeOnPlace = false;
		List<Item> productItemList = new ArrayList<Item>();
		List<Item> payementItemList = new ArrayList<Item>();

		String source = root.getAttribute(SALES_ATTRIBUTE_SOURCE);

		String cancelledAsString = root.getAttribute(DOCUMENT_CANCELLED);
		if (!Tools.isNullOrEmpty(cancelledAsString)) {
			cancelled = new Boolean(cancelledAsString).booleanValue();
		}

		String employeeId = DOMUtils.getElementValueByTagName(SALES_EMPLOYEE,
				root);
		employee = FacadeDAO.instance().getEmployeesDAO().getById(employeeId);

		String timeAsString = DOMUtils.getElementValueByTagName(SALES_TIME,
				root);
		try {
			Long timeAsLong = Tools.toLong(timeAsString);
			time = new Date(timeAsLong);
		} catch (ParameterException e) {
			throw new DAOException(timeAsString, e);
		}

		String companyId = DOMUtils.getElementValueByTagName(SALES_COMPANY,
				root);
		company = FacadeDAO.instance().getCompanyDAO().getById(companyId);

		computerName = DOMUtils.getElementValueByTagName(SALES_COMPUTERNAME,
				root);
		String takeOnPlaceAsString = DOMUtils.getElementValueByTagName(
				TAG_TAKEONPLACE, root);
		takeOnPlace = (!Tools.isNullOrEmpty(takeOnPlaceAsString)
				&& "true".equals(takeOnPlaceAsString) ? true : false);
		NodeList itemNodeList = DOMUtils.getElementByTagName(
				ItemDAO.TAG_SALES_ITEM, root);

		for (int i = 0; i < itemNodeList.getLength(); i++) {

			Element itemNode = (Element) itemNodeList.item(i);

			Item item =

			FacadeDAO.instance().getProductItemDAO().extract(itemNode);

			productItemList.add(item);

		}

		NodeList payNodeList = DOMUtils.getElementByTagName(
				PayementItemDAO.TAG_SALES_PAY, root);

		for (int i = 0; i < payNodeList.getLength(); i++) {

			Element itemNode = (Element) payNodeList.item(i);

			Item item = FacadeDAO.instance().getPayementItemDAO()
					.extract(itemNode);

			payementItemList.add(item);

		}

		OperationUnit operationUnit;
		try {
			operationUnit = new OperationUnitImpl(source, employee, time,
					company, computerName, takeOnPlace, productItemList,
					payementItemList, cancelled);
		} catch (ParameterException e) {
			throw new DAOException("Erreur grave", e);
		}

		return operationUnit;
	}

	public void appendXml(OperationUnit operationUnit, StringBuffer xml) {

		String[][] attributeList = null;

		if (operationUnit.isCancelled()) {
			attributeList = new String[2][];
			attributeList[0] = new String[] { ATTRIBUTE_SOURCE,
					operationUnit.getFileName() };
			attributeList[1] = new String[] { ATTRIBUTE_CANCELLED, "true" };
		} else {
			attributeList = new String[1][];
			attributeList[0] = new String[] { ATTRIBUTE_SOURCE,
					operationUnit.getFileName() };
		}

		XMLHelper.openTag(attributeList, TAG_DOCUMENT, xml);

		if (operationUnit.getEmployee() != null) {
			XMLHelper.addTag(operationUnit.getEmployee().getId(),
					TAG_PERSONNEL, xml);
		} else {
			CoupleMessages cm = new CoupleMessages();

			cm.put("Op file name", operationUnit.getFileName());
			cm.put("Employee undefined", "");

			log.error(cm.toString());
		}
		XMLHelper.addTag(Long.toString(operationUnit.getBeginTime().getTime()),
				TAG_DATE, xml);
		XMLHelper.addTag(operationUnit.getCompany().getId(), TAG_COMPANY, xml);
		XMLHelper
				.addTag(operationUnit.getComputerName(), TAG_COMPUTERNAME, xml);
		XMLHelper.addTag((operationUnit.getTakeOnPlace() != null
				&& operationUnit.getTakeOnPlace() == true ? "true" : null),
				TAG_TAKEONPLACE, xml);

		for (int i = 0; i < operationUnit.getItemList().size(); i++) {
			Item item = operationUnit.getItemList().get(i);
			try {
				FacadeDAO.instance().getProductItemDAO().append(item, xml);
			} catch (DAOException e) {
				log.error(operationUnit.getFileName() + "/" + item.toString());
			}
		}
		for (int i = 0; i < operationUnit.getPayList().size(); i++) {
			Item item = operationUnit.getPayList().get(i);
			try {
				FacadeDAO.instance().getPayementItemDAO().append(item, xml);
			} catch (DAOException e) {
				log.error(operationUnit.getFileName() + "/" + item.toString());
			}
		}

		XMLHelper.closeTag(TAG_DOCUMENT, xml);

	}

}