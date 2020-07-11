package be.panidel.businessLayer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mysql.cj.core.exceptions.WrongArgumentException;

import be.panidel.businessLayer.helper.EnumHelper.PeriodType;

public class ReportPeriod {

	private Date beginTime;
	private Date endTime;
	private PeriodType periodType;

	public ReportPeriod(Date beginTime, Date endTime, PeriodType periodType) {
		super();
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.periodType = periodType;
	}

	public ReportPeriod(Date from, PeriodType periodType) {

		this.periodType = periodType;

		beginTime = getKeyDate(from, periodType);
		endTime = new Date(getContigousBeginTime(this).getTime() - 1);

	}

	public static Date getKeyDate(Date from, PeriodType periodType) {
		Calendar calendarFrom = Calendar.getInstance();
		calendarFrom.setTime(from);

		switch (periodType) {
		case YEAR:
			return new GregorianCalendar(calendarFrom.get(Calendar.YEAR), 0, 1).getTime();
		case MONTH:
			return new GregorianCalendar(calendarFrom.get(Calendar.YEAR), calendarFrom.get(Calendar.MONTH), 1)
					.getTime();
		case DAY:
			return new GregorianCalendar(calendarFrom.get(Calendar.YEAR), calendarFrom.get(Calendar.MONTH),
					calendarFrom.get(Calendar.DAY_OF_MONTH)).getTime();
		case HOUR:
			return new GregorianCalendar(calendarFrom.get(Calendar.YEAR), calendarFrom.get(Calendar.MONTH),
					calendarFrom.get(Calendar.DAY_OF_MONTH), calendarFrom.get(Calendar.HOUR_OF_DAY), 0).getTime();
		default:
			throw new WrongArgumentException("Cash sale unit is not a period");
		}
	}

	public static long getUnitsInRange(ReportPeriod reportPeriod) {

		long millisecondsBetweenDates = reportPeriod.getEndTime().getTime() - reportPeriod.getBeginTime().getTime();
		return millisecondsBetweenDates / reportPeriod.getPeriodType().getMilliseconds();

	}

	@Override
	public String toString() {
		DateFormat df = SimpleDateFormat.getDateTimeInstance();
		return "ReportPeriod [beginTime=" + df.format(beginTime) + ", endTime=" + df.format(endTime) + ", periodType="
				+ periodType + "]";
	}

	public boolean isSubPeriodInPerdiodRange(ReportPeriod subReportPeriod) {
		return !subReportPeriod.getBeginTime().after(endTime);
	}

	public static Date getContigousBeginTime(ReportPeriod reportPeriod) {

		Date contigousBeginTime = null;

		Calendar calendarTo = Calendar.getInstance();
		calendarTo.setTime(reportPeriod.getBeginTime());
		switch (reportPeriod.getPeriodType()) {
		case YEAR:
			contigousBeginTime = new GregorianCalendar(calendarTo.get(Calendar.YEAR) + 1, 0, 1).getTime();
			break;
		case MONTH:
			contigousBeginTime = new GregorianCalendar(calendarTo.get(Calendar.YEAR),
					calendarTo.get(Calendar.MONTH) + 1, 1).getTime();
			break;
		case DAY:
			contigousBeginTime = new GregorianCalendar(calendarTo.get(Calendar.YEAR), calendarTo.get(Calendar.MONTH),
					calendarTo.get(Calendar.DAY_OF_MONTH) + 1).getTime();
			break;
		case HOUR:
			contigousBeginTime = new GregorianCalendar(calendarTo.get(Calendar.YEAR), calendarTo.get(Calendar.MONTH),
					calendarTo.get(Calendar.DAY_OF_MONTH), calendarTo.get(Calendar.HOUR_OF_DAY) + 1, 0).getTime();
			break;
		case CASH_SALE_UNIT:
			// can not be a period
			throw new WrongArgumentException("Cash sale unit is not a period");
		}

		return contigousBeginTime;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public PeriodType getPeriodType() {
		return periodType;
	}

}
