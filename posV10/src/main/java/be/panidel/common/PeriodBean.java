package be.panidel.common;

import java.util.Date;

import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

/**
 * @author franzph
 *
 */
/**
 * @author franzph
 * 
 */
public class PeriodBean {

	Date startDate;
	Date endDate;

	public PeriodBean() {
	}

	public PeriodBean(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String toString() {
		CoupleMessages cm = new CoupleMessages();
		cm.put("StartDate", startDate);
		cm.put("EndDate", endDate);
		return cm.toString();
	}

	/**
	 * @param date
	 * @param periodStartDate
	 * @param periodEndDate
	 * @return -1 = is Before, 0 = is in period (tags included), 1 = is after
	 * @throws ParameterException
	 */
	public int isInPeriodAsCompleteDay(Date date) throws ParameterException {

		return this.isInPeriod(date, Tools.startOfCurentDay(startDate),
				Tools.endOfDay(endDate));

	}

	/**
	 * @param date
	 * @param periodStartDate
	 * @param periodEndDate
	 * @return -1 = is Before, 0 = is in period (tags included), 1 = is after
	 * @throws ParameterException
	 */
	public int isInPeriod(Date date) throws ParameterException {

		return this.isInPeriod(date, this.startDate, this.endDate);

	}

	/**
	 * @param date
	 * @param periodStartDate
	 * @param periodEndDate
	 * @return -1 = is Before, 0 = is in period (tags included), 1 = is after
	 * @throws ParameterException
	 */
	private Integer isInPeriod(Date date, Date periodStartDate,
			Date periodEndDate) throws ParameterException {

		if (date == null) {
			return null;
		}

		if (periodStartDate != null && date.before(periodStartDate)) {
			return -1;
		}

		if (periodEndDate != null && date.after(periodEndDate)) {
			return 1;
		}

		return 0;

	}

	public boolean isBorned() {
		return startDate != null && endDate != null;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
}