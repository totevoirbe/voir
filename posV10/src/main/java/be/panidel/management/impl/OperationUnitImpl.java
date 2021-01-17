package be.panidel.management.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import be.panidel.common.CoupleMessages;
import be.panidel.common.POSConstants;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Company;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.Person;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.ComputeHelper;

public class OperationUnitImpl implements OperationUnit {

	Logger log = Logger.getLogger("OperationUnitImpl");

	private String fileName;
	private Person employee;
	private Date time;
	private Company company;
	private String computerName;
	private boolean cancelled;
	private List<Item> itemList;
	private List<Item> payList;
	private Boolean takeOnPlace;

	public OperationUnitImpl(String fileName, Person employee, Date time,
			Company company, String computerName, Boolean takeOnpBoolean,
			List<Item> itemList, List<Item> payList, boolean cancelled)
			throws ParameterException {
		super();
		this.fileName = fileName;
		this.employee = employee;
		this.time = time;
		this.company = company;
		this.computerName = computerName;
		this.itemList = itemList;
		this.payList = payList;
		this.cancelled = cancelled;
		this.takeOnPlace = takeOnpBoolean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.impl.OperationUnit#getId()
	 */
	public String getFileName() {
		return fileName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.impl.OperationUnit#getEmployee()
	 */
	public Person getEmployee() {
		return employee;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.impl.OperationUnit#getCompany()
	 */
	public Company getCompany() {
		return company;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.impl.OperationUnit#getComputerName()
	 */
	public String getComputerName() {
		return computerName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.impl.OperationUnit#getItem()
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.impl.OperationUnit#getPay()
	 */
	public List<Item> getPayList() {
		return payList;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public String toString() {
		CoupleMessages cm = new CoupleMessages();

		cm.put("fileName", fileName);
		cm.put("cancelled", cancelled);
		cm.put("employee", employee);
		cm.put("time", POSConstants.SDF_DATE_AND_TIME.format(time));
		cm.put("company", company);
		cm.put("computerName", computerName);

		CoupleMessages cmItems = new CoupleMessages();
		for (Item oneItem : itemList) {
			cmItems.put("Item", oneItem);
		}
		cm.put("ItemList", cmItems);

		CoupleMessages cmPay = new CoupleMessages();
		for (Item oneItem : payList) {
			cmPay.put("Item", oneItem);
		}
		cm.put("PayList", cmPay);

		return cm.toXML();
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public BigDecimal getTotalTVAC() {
		BigDecimal totalTVAC = BigDecimal.ZERO;
		for (Item payment : payList) {
			totalTVAC = totalTVAC.add(payment.getTotalTVAC());
		}
		return totalTVAC;
	}

	public BigDecimal getCaTVAC() throws ParameterException {
		BigDecimal caTVAC = BigDecimal.ZERO;
		for (Item payment : payList) {
			if (payment.getItem() != null) {
				if (ComputeHelper.isCA(payment.getItem().getId())) {
					BigDecimal operationCaTVAC = payment.getTotalTVAC();
					caTVAC = caTVAC.add(operationCaTVAC);
				}
			} else {
				log.debug(payment.toString());
			}
		}
		return caTVAC;
	}

	public BigDecimal getItemQuantity() {
		BigDecimal itemQuantity = BigDecimal.ZERO;
		for (Item oneItem : itemList) {
			itemQuantity = itemQuantity.add(oneItem.getQuantity());
		}
		return itemQuantity;
	}

	public void setBeginTime(Date time) {
		this.time = time;
	}

	@Override
	public Date getBeginTime() {
		return time;
	}

	@Override
	public Date getEndTime() {
		Date endTime = null;
		try {
			endTime = POSConstants.SDF_FOR_FILE_SALES.parse(fileName);
		} catch (ParseException e) {
			log.error(fileName, e);
		}
		return endTime;
	}

	public Boolean getTakeOnPlace() {
		return takeOnPlace;
	}

	public void setTakeOnPlace(Boolean takeOnPlace) {
		this.takeOnPlace = takeOnPlace;
	}

	public Map<BigDecimal, BigDecimal> getTvaList() throws ParameterException {

		SplitTva splitTva = new SplitTva(takeOnPlace, getCaTVAC());
		for (Item oneItem : itemList) {
			splitTva.addItem(oneItem);
		}

		return splitTva.getTvaList();
	}
}

class SplitTva {

	Logger log = Logger.getLogger("splitTva");

	private Boolean takeOnPlace;
	private BigDecimal caTVAC;
	private Map<BigDecimal, List<Item>> tvaVentilation;

	public SplitTva(Boolean takeOnPlace, BigDecimal caTVAC) {
		super();
		this.takeOnPlace = takeOnPlace;
		this.caTVAC = caTVAC;
		tvaVentilation = new TreeMap<BigDecimal, List<Item>>();
	}

	public void addItem(Item item) {
		List<Item> itemList = null;
		BigDecimal onTVA = BigDecimal.ZERO;
		try {
			if (takeOnPlace) {
				onTVA = item.getTvaTakeOnPlace();
				if (BigDecimal.ZERO.compareTo(onTVA) == 0) {
					onTVA = FacadeDAO.instance().getProductsDAO()
							.getById(item.getItem().getId())
							.getTvaTakeOnPlace();
				}
			} else {
				onTVA = item.getTvaTakeAway();
				if (onTVA.equals(BigDecimal.ZERO)) {
					onTVA = FacadeDAO.instance().getProductsDAO()
							.getById(item.getItem().getId()).getTvaTakeAway();
				}
			}
		} catch (DAOException e) {
			log.error(item, e);
		}
		onTVA = onTVA.negate();
		itemList = tvaVentilation.get(onTVA);
		if (itemList == null) {
			itemList = new ArrayList<Item>();
			tvaVentilation.put(onTVA, itemList);
		}
		itemList.add(item);
	}

	public Map<BigDecimal, BigDecimal> getTvaList() {
		Map<BigDecimal, BigDecimal> tvalist = new TreeMap<BigDecimal, BigDecimal>();
		BigDecimal CATVACByItems = BigDecimal.ZERO;
		for (BigDecimal tva : tvaVentilation.keySet()) {
			BigDecimal htvaByTVAValues = BigDecimal.ZERO;
			for (Item item : tvaVentilation.get(tva)) {
				BigDecimal addValue = BigDecimal.ZERO;
				if (CATVACByItems.add(item.getTotalTVAC()).compareTo(caTVAC) > 0) {
					addValue = caTVAC.subtract(CATVACByItems);
				} else {
					addValue = item.getTotalTVAC();
				}
				htvaByTVAValues = htvaByTVAValues.add(addValue);
				CATVACByItems = CATVACByItems.add(addValue);
			}
			tvalist.put(tva.negate(), htvaByTVAValues);
		}
		return tvalist;
	}
}