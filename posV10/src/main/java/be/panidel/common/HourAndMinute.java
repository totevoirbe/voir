package be.panidel.common;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class HourAndMinute implements Comparable<HourAndMinute> {

	private static final Logger log = Logger.getLogger("HourAndMinute");

	private int hour;
	private int minute;

	public HourAndMinute(int hour, int minute) {
		this.hour = hour;
		this.minute = minute;
	}

	public HourAndMinute(Date theDate) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(theDate);
		hour = gc.get(GregorianCalendar.HOUR_OF_DAY);
		minute = gc.get(GregorianCalendar.MINUTE);

	}

	@Override
	public int compareTo(HourAndMinute compareDate) {

		int thisHourAndMinute = hour * 60 + minute;
		int compareValue = compareDate.getHour() * 60 + compareDate.getMinute();

		return thisHourAndMinute - compareValue;

	}

	@Override
	public String toString() {
		try {
			return Tools.toStringTwoDigits(hour) + ":"
					+ Tools.toStringTwoDigits(minute);
		} catch (ParameterException e) {
			log.error("", e);
			return "??:??";
		}
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

}
