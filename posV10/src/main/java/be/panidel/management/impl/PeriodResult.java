package be.panidel.management.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import be.panidel.common.CoupleMessages;
import be.panidel.common.HourAndMinute;
import be.panidel.common.Identification;
import be.panidel.common.PeriodBean;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.OperationUnitList;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.Item;
import be.panidel.management.OperationUnit;
import be.panidel.management.PeriodValue;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.ComputeHelper;

public class PeriodResult {

	private static final Logger log = Logger.getLogger("DayResult");

	public final static int LIMIT_FIRST = -1;
	public final static int LIMIT_LAST = 1;

	private PeriodBean period;
	private HourAndMinute firstOperation;
	private HourAndMinute lastOperation;
	private int canceled;
	private int salesQuantity;
	private Map<BigDecimal, BigDecimal> htva;
	private BigDecimal totalTVAC;
	private BigDecimal caTVAC;
	protected OperationUnitList operationUnitList;
	private Map<String, BigDecimal> paymentsByType;
	private Map<String, PeriodValue> valuesByPeriod;
	private Map<String, BigDecimal> globalFidelityAndRejectedRest;

	/**
	 * @param day
	 *            (format : yyyyMMdd)
	 * @param storage
	 * @param rejectedFiles
	 * @throws DAOException
	 * @throws ParseException
	 * @throws ParameterException
	 */
	public PeriodResult(PeriodBean period, File storage, boolean onlyForStat)
			throws DAOException, ParseException, ParameterException {

		totalTVAC = BigDecimal.ZERO;
		caTVAC = BigDecimal.ZERO;
		htva = new HashMap<BigDecimal, BigDecimal>();
		canceled = 0;
		this.period = period;
		operationUnitList = FacadeDAO.instance().getOperationUnitDAO()
				.extractSalesOfPeriod(storage, period);
		globalFidelityAndRejectedRest = compute(onlyForStat);
	}

	public String toString() {
		return prepareCoupleMessages().toString();
	}

	public String toXML() {
		return prepareCoupleMessages().toXML();
	}

	public CoupleMessages prepareCoupleMessages() {

		CoupleMessages cm = new CoupleMessages();

		cm.put("period", period);
		cm.put("firstOperation", firstOperation);
		cm.put("lastOperation", lastOperation);
		cm.put("canceled", canceled);
		cm.put("totalTVAC", totalTVAC);
		cm.put("caTVAC", caTVAC);

		CoupleMessages cmSalesByPeriod = new CoupleMessages();

		try {
			Map<String, PeriodValue> valuesByPeriod = getValuesByPeriod();

			for (Iterator<String> iterator = valuesByPeriod.keySet().iterator(); iterator
					.hasNext();) {
				String period = iterator.next();
				PeriodValue periodValue = valuesByPeriod.get(period);
				cmSalesByPeriod.put(period, periodValue.toString());
			}
		} catch (ParameterException e) {
			cm.put("Erreur", e.getMessage());
			log.error("Erreur grave", e);
		}
		cm.put("SalesByPeriod", cmSalesByPeriod.toString());

		Map<String, BigDecimal> paymentsByType = getPaymentsByType();

		CoupleMessages cmPayByType = new CoupleMessages();

		for (String payType : paymentsByType.keySet()) {

			BigDecimal value = paymentsByType.get(payType);
			cmPayByType.put(payType, value);
		}
		cm.put("PayByType", cmPayByType.toString());

		return cm;
	}

	private HourAndMinute getOperationLimitInPeriod(
			Identification identification, int limitType) {
		HourAndMinute opDate = null;
		if (identification != null) {
			for (OperationUnit operationUnit : operationUnitList.values()) {
				try {
					if (identification.getId().equals(
							operationUnit.getEmployee().getId())) {
						opDate = operationLimitInPeriod(opDate, operationUnit,
								limitType);
					}
				} catch (ParameterException e) {
					log.error(identification.toString(), e);
				}
			}
		}
		return opDate;
	}

	private HourAndMinute operationLimitInPeriod(HourAndMinute opHourAndMinute,
			OperationUnit operationUnit, int limitType)
			throws ParameterException {

		if (operationUnit != null && operationUnit.getBeginTime() != null) {

			HourAndMinute actualHourAndMinute = new HourAndMinute(
					operationUnit.getBeginTime());

			if (opHourAndMinute == null) {
				opHourAndMinute = actualHourAndMinute;
			} else if (actualHourAndMinute.compareTo(opHourAndMinute)
					* limitType < 0) {
				actualHourAndMinute = opHourAndMinute;
			}

			return actualHourAndMinute;

		} else {
			throw new ParameterException("Current opdate is null");
		}

	}

	public HourAndMinute getFirstOperation() {
		return firstOperation;
	}

	public HourAndMinute getLastOperation() {
		return lastOperation;
	}

	public HourAndMinute getFirstOperationInPeriod(Identification identification) {
		if (identification == null) {
			return firstOperation;
		} else {
			return getOperationLimitInPeriod(identification, LIMIT_FIRST);
		}
	}

	public HourAndMinute getLastOperationInPeriod(Identification identification) {
		if (identification == null) {
			return lastOperation;
		} else {
			return getOperationLimitInPeriod(identification, LIMIT_LAST);
		}
	}

	public int getCanceled(Identification identification) {
		int employeeCancelled = 0;
		if (identification != null) {
			for (OperationUnit operationUnit : operationUnitList.values()) {
				if (operationUnit.isCancelled()
						&& identification.getId().equals(
								operationUnit.getEmployee().getId())) {
					employeeCancelled++;
				}
			}
		}
		return employeeCancelled;
	}

	public BigDecimal getCaTVAC(Identification identification)
			throws ParameterException {
		BigDecimal employeeCaTvac = BigDecimal.ZERO;
		if (identification != null) {
			for (OperationUnit operationUnit : operationUnitList.values()) {
				if (!operationUnit.isCancelled()
						&& identification.getId().equals(
								operationUnit.getEmployee().getId())) {
					employeeCaTvac = employeeCaTvac.add(operationUnit
							.getCaTVAC());
				}
			}
		}
		return employeeCaTvac;
	}

	public BigDecimal getTotalTVAC() {
		return totalTVAC;
	}

	public BigDecimal getCaTVAC() {
		return caTVAC;
	}

	protected Map<String, BigDecimal> compute(boolean onlyForStat)
			throws ParameterException, DAOException {

		Map<String, BigDecimal> globalFidelityAndRejectedRest = new HashMap<String, BigDecimal>();

		for (OperationUnit operationUnit : operationUnitList.values()) {
			try {
				firstOperation = operationLimitInPeriod(firstOperation,
						operationUnit, LIMIT_FIRST);
				lastOperation = operationLimitInPeriod(lastOperation,
						operationUnit, LIMIT_LAST);
			} catch (ParameterException e) {
				log.error(operationUnit.toString(), e);
			}

			if (!operationUnit.isCancelled()) {
				if (onlyForStat) {

					Map<String, BigDecimal> fidelityAndRejectedRest = ComputeHelper
							.fidelityAndRejectedCorrection(operationUnit);
					addRest(globalFidelityAndRejectedRest,
							fidelityAndRejectedRest);

				}
				totalTVAC = totalTVAC.add(operationUnit.getTotalTVAC());
				caTVAC = caTVAC.add(operationUnit.getCaTVAC());
				Map<BigDecimal, BigDecimal> opUnitTvaList = operationUnit
						.getTvaList();
				for (BigDecimal tvaID : opUnitTvaList.keySet()) {
					BigDecimal currentTvaAmout = htva.get(tvaID);
					if (currentTvaAmout == null) {
						htva.put(tvaID, opUnitTvaList.get(tvaID));
					} else {
						htva.put(tvaID,
								currentTvaAmout.add(opUnitTvaList.get(tvaID)));
					}
				}
				salesQuantity++;
			} else {
				canceled++;
			}
		}
		return globalFidelityAndRejectedRest;
	}

	private static void addRest(
			Map<String, BigDecimal> globalFidelityAndRejectedRest,
			Map<String, BigDecimal> fidelityAndRejectedRest) {
		if (fidelityAndRejectedRest != null) {
			for (String key : fidelityAndRejectedRest.keySet()) {
				BigDecimal existingValue = globalFidelityAndRejectedRest
						.get(key);
				BigDecimal addedValue = fidelityAndRejectedRest.get(key);
				if (existingValue == null) {
					globalFidelityAndRejectedRest.put(key, addedValue);
				} else {
					globalFidelityAndRejectedRest.put(key,
							existingValue.add(addedValue));
				}
			}
		}
	}

	public PeriodBean getPeriod() {
		return period;
	}

	public Map<String, BigDecimal> getPaymentsByType() {

		if (paymentsByType == null) {
			paymentsByType = ComputeHelper.getPaymentsByType(operationUnitList);
		}

		return paymentsByType;
	}

	public Map<String, BigDecimal> getPaymentsByType(
			Identification identification) {

		return ComputeHelper.getPaymentsByType(operationUnitList,
				identification);
	}

	public Map<String, Item> getProductSales() {

		Map<String, Item> productSales = ComputeHelper
				.getProductSales(operationUnitList);

		return productSales;
	}

	public Map<String, Item> getRejectedProductList() {

		Map<String, Item> productSales = ComputeHelper
				.getRejectedProductList(operationUnitList);

		return productSales;
	}

	public Map<String, PeriodValue> getValuesByPeriod()
			throws ParameterException {

		if (valuesByPeriod == null) {
			valuesByPeriod = ComputeHelper.getSalesByPeriod(operationUnitList);
		}

		return valuesByPeriod;
	}

	public Map<String, PeriodValue> getValuesByPeriod(
			Identification identification) throws ParameterException {

		return ComputeHelper
				.getSalesByPeriod(operationUnitList, identification);
	}

	public int getCanceled() {
		return canceled;
	}

	public void addCanceled() {
		this.canceled++;
	}

	public Map<BigDecimal, BigDecimal> getHtva() {
		return htva;
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public OperationUnitList getOperationUnitList() {
		return operationUnitList;
	}

	public Map<String, BigDecimal> getGlobalFidelityAndRejectedRest() {
		return globalFidelityAndRejectedRest;
	}
}