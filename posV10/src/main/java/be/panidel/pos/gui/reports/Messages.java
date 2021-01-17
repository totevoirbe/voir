package be.panidel.pos.gui.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import be.panidel.common.HourAndMinute;
import be.panidel.common.Identification;
import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.common.PeriodBean;
import be.panidel.common.StringReverseOrder;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.exeption.DAOException;
import be.panidel.management.PeriodValue;
import be.panidel.management.impl.PeriodResult;
import be.panidel.pos.exception.ParameterException;

public class Messages {

	private static final Logger log = Logger.getLogger("Messages");

	public static StringBuffer MessageResultByDay(Date startDate, Date endDate) {

		if (log.isDebugEnabled()) {
			log.debug("Sales message from "
					+ POSConstants.SDF_DATE_AND_TIME.format(startDate) + " to "
					+ POSConstants.SDF_DATE_AND_TIME.format(endDate));
		}

		StringBuffer sb = new StringBuffer();

		Map<StringReverseOrder, PeriodResult> periodResultList;
		try {
			periodResultList = FacadeDAO
					.instance()
					.getOperationUnitDAO()
					.getDayResultList(
							new PeriodBean(startDate, endDate),
							POSParameters.instance()
									.getResultsStorageCaissesGroupbyday());

			if (periodResultList == null || periodResultList.size() <= 0) {
				return (new StringBuffer("Pas de ventes du "
						+ POSConstants.SDF_DAYOFWEEK.format(startDate) + " au "
						+ POSConstants.SDF_DAYOFWEEK.format(endDate)));
			}

			for (StringReverseOrder dateSelected : periodResultList.keySet()) {

				String dateAsString = dateSelected.toString();

				PeriodResult dr = periodResultList.get(dateSelected);

				sb.append(POSConstants.LINE_SEPARATOR);
				sb.append("Ventes du : ");
				sb.append(POSConstants.SDF_DAYOFWEEK.format(dr.getPeriod()
						.getStartDate()));
				sb.append(" ");
				sb.append(POSConstants.SDF_DAY_STRUCTURED.format(dr.getPeriod()
						.getStartDate()));
				sb.append(" / Situation au : ");
				sb.append(POSConstants.SDF_SHORTDATE_SHORTTIME
						.format(new Date()));
				sb.append(POSConstants.LINE_SEPARATOR);
				sb.append("----------------------------------------------------------------------");
				sb.append(POSConstants.LINE_SEPARATOR);

				Set<Identification> employeeOrderedList = new TreeSet<Identification>();
				for (Identification employee : FacadeDAO.instance()
						.getEmployeesDAO().getList()) {
					if (!BigDecimal.ZERO.equals(dr.getCaTVAC(employee))) {
						employeeOrderedList.add(employee);
					}
				}

				// first group of data
				globalData(sb, dr, null, "All");
				for (Identification employee : employeeOrderedList) {
					globalData(sb, dr, employee, dateAsString);
				}
				sb.append(POSConstants.LINE_SEPARATOR);

				// SalesByPeriod
				int firstPeriod = 0;
				int lastPeriod = 23;
				Map<String, PeriodValue> salesByPeriod = dr.getValuesByPeriod();
				for (int periodHour = 0; periodHour < 24; periodHour++) {
					String periodHourAsString = Integer.toString(periodHour);
					PeriodValue periodValue = salesByPeriod
							.get(periodHourAsString);
					if (periodValue != null
							&& periodValue.getSalesQuantity() > 0) {
						if (firstPeriod == 0) {
							firstPeriod = periodHour;
						}
						lastPeriod = periodHour;
					}
				}

				sb.append("Vtes");
				for (int periodHour = firstPeriod; periodHour <= lastPeriod; periodHour++) {
					sb.append(POSConstants.TAB);
					sb.append(Integer.toString(periodHour) + "/"
							+ Integer.toString(periodHour + 1));
				}
				sb.append(POSConstants.LINE_SEPARATOR);
				periodValues(sb, firstPeriod, lastPeriod,
						dr.getValuesByPeriod(), "All");
				for (Identification employee : employeeOrderedList) {
					periodValues(sb, firstPeriod, lastPeriod,
							dr.getValuesByPeriod(employee), employee.getCode());
				}
				sb.append(POSConstants.LINE_SEPARATOR);

				sb.append("Op");
				for (int periodHour = firstPeriod; periodHour <= lastPeriod; periodHour++) {
					sb.append(POSConstants.TAB);
					sb.append(Integer.toString(periodHour) + "/"
							+ Integer.toString(periodHour + 1));
				}
				sb.append(POSConstants.LINE_SEPARATOR);
				periodOp(sb, firstPeriod, lastPeriod, dr.getValuesByPeriod(),
						"All");
				for (Identification employee : employeeOrderedList) {
					periodOp(sb, firstPeriod, lastPeriod,
							dr.getValuesByPeriod(employee), employee.getCode());
				}
				sb.append(POSConstants.LINE_SEPARATOR);

				// PayList
				sb.append("Pay");

				List<Identification> payList = new ArrayList<Identification>();
				for (Identification payement : FacadeDAO.instance()
						.getPayementModesDAO().getList()) {
					BigDecimal payValue = dr.getPaymentsByType().get(
							payement.getId());
					if (payValue != null && !BigDecimal.ZERO.equals(payValue)) {
						payList.add(payement);
					}
				}

				for (Identification payement : payList) {
					sb.append(POSConstants.TAB);
					sb.append((payement.getCode().length() > 5 ? payement
							.getCode().substring(0, 5) : payement.getCode()));
				}
				sb.append(POSConstants.LINE_SEPARATOR);
				payList(sb, dr.getPaymentsByType(), "All", payList);
				for (Identification employee : employeeOrderedList) {
					payList(sb, dr.getPaymentsByType(employee),
							employee.getCode(), payList);
				}

				// Cancelled operations
				sb.append(POSConstants.LINE_SEPARATOR);
				sb.append("Annulation de vente");
				sb.append(POSConstants.LINE_SEPARATOR);
				sb.append("All");
				sb.append(POSConstants.TAB);
				sb.append(dr.getCanceled());
				sb.append(POSConstants.TAB);
				for (Identification employee : employeeOrderedList) {
					sb.append(POSConstants.LINE_SEPARATOR);
					sb.append(employee.getCode());
					sb.append(POSConstants.TAB);
					sb.append(dr.getCanceled(employee));
				}
				sb.append(POSConstants.LINE_SEPARATOR);
			}
		} catch (DAOException e) {
			log.error("", e);
		} catch (ParameterException e) {
			log.error("", e);
		}
		return sb;
	}

	private static void payList(StringBuffer sb,
			Map<String, BigDecimal> payByType, String employeeCode,
			List<Identification> payList) throws DAOException {
		BigDecimal payValue;
		sb.append(employeeCode);
		for (Identification payement : payList) {
			sb.append(POSConstants.TAB);
			payValue = payByType.get(payement.getId());
			if (payValue == null) {
				sb.append(".");
			} else {
				sb.append(Long.toString(payValue.longValue()));
			}
		}
		sb.append(POSConstants.LINE_SEPARATOR);
	}

	private static void periodValues(StringBuffer sb, int firstPeriod,
			int lastPeriod, Map<String, PeriodValue> salesByPeriod,
			String employeeCode) {
		sb.append(employeeCode);
		for (int periodHour = firstPeriod; periodHour <= lastPeriod; periodHour++) {
			sb.append(POSConstants.TAB);
			PeriodValue periodValue = salesByPeriod.get(Integer
					.toString(periodHour));
			if (periodValue != null) {
				BigDecimal periodSale = periodValue.getSalesByPeriod();
				if (!BigDecimal.ZERO.equals(periodSale)) {
					sb.append(Long.toString(periodSale.longValue()));
				}
			} else {
				sb.append(".");
			}
		}
		sb.append(POSConstants.LINE_SEPARATOR);
	}

	private static void periodOp(StringBuffer sb, int firstPeriod,
			int lastPeriod, Map<String, PeriodValue> salesByPeriod,
			String employeeCode) {
		sb.append(employeeCode);
		for (int periodHour = firstPeriod; periodHour <= lastPeriod; periodHour++) {
			sb.append(POSConstants.TAB);
			PeriodValue periodValue = salesByPeriod.get(Integer
					.toString(periodHour));
			if (periodValue != null) {
				int periodOp = periodValue.getSalesQuantity();
				if (periodOp > 0) {
					sb.append(Integer.toString(periodOp));
				}
			} else {
				sb.append(".");
			}
		}
		sb.append(POSConstants.LINE_SEPARATOR);
	}

	private static void globalData(StringBuffer sb, PeriodResult dr,
			Identification employee, String date) throws ParameterException {
		HourAndMinute hourAndMinute;
		hourAndMinute = dr.getFirstOperationInPeriod(employee);
		if (employee == null) {
			sb.append(date);
		} else {
			sb.append(employee.getCode());
		}
		sb.append(POSConstants.TAB);
		sb.append((hourAndMinute == null ? "-" : hourAndMinute.toString()));
		sb.append(POSConstants.TAB);
		hourAndMinute = dr.getLastOperationInPeriod(employee);
		sb.append((hourAndMinute == null ? "-" : hourAndMinute.toString()));
		sb.append(POSConstants.TAB);
		if (employee == null) {
			sb.append(dr.getCaTVAC().toPlainString());
		} else {
			sb.append(Long.toString(dr.getCaTVAC(employee).longValue()));
		}
		sb.append(POSConstants.LINE_SEPARATOR);
	}

	public static void main(String[] args) {

		Date date = new GregorianCalendar(2012, 0, 24).getTime();
		System.out.println(Messages.MessageResultByDay(date, date));

	}
}
