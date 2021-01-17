package be.panidel.tools;

import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.management.Item;
import be.panidel.pos.exception.ParameterException;

public class Tools {

	private static final Logger log = Logger.getLogger("Tools");

	public static boolean isNullOrEmpty(String value) {
		return (value == null || value.trim().length() <= 0);
	}

	public static boolean isNullOrEmpty(Object[] theList) {
		return (theList == null || theList.length <= 0);
	}

	public static boolean isNullOrEmpty(List<?> theList) {
		return (theList == null || theList.size() <= 0);
	}

	public static boolean isNullOrEmpty(Map<?, ?> theList) {
		return (theList == null || theList.size() <= 0);
	}

	public static Font getDefaultFont(int size) {
		Font font = POSConstants.DEFAULT_FONT;
		return new Font(font.getName(), font.getStyle(), size);
	}

	public static String toString(boolean value) {
		if (value) {
			return "true";
		} else {
			return "false";
		}
	}

	public static boolean toBoolean(String data) {
		if (data == null) {
			return false;
		}
		data = data.trim().toUpperCase();
		if ("TRUE".equals(data) || "VRAI".equals(data)) {
			return true;
		}
		return false;
	}

	public static String toStringNotNull(Integer number) {

		if (number == null) {
			return "0";
		}

		return Integer.toString(number);

	}

	public static Integer toIntegerNotNull(String data)
			throws ParameterException {

		Integer value = toInteger(data);

		if (value == null) {
			return new Integer(0);
		}

		return value;

	}

	public static Integer toInteger(String data) throws ParameterException {
		if (data == null) {
			throw new ParameterException("Null value");
		}
		try {
			return Integer.parseInt(data);
		} catch (NumberFormatException e) {
			throw new ParameterException("Bad input value [" + data + "]", e);
		}
	}

	public static Long toLong(String data) throws ParameterException {
		if (data == null) {
			throw new ParameterException("Null value");
		}
		try {
			return Long.parseLong(data);
		} catch (NumberFormatException e) {
			throw new ParameterException("Bad input value [" + data + "]", e);
		}
	}

	public static String toStringMultiplyNotNull(BigDecimal value) {
		if (value == null) {
			return "0";
		}
		return value.multiply(new BigDecimal(100)).toPlainString();
	}

	public static String toStringMultiply(BigDecimal value)
			throws ParameterException {
		if (value == null) {
			throw new ParameterException("Null value");
		}
		return toStringMultiplyNotNull(value);
	}

	public static String toString(BigDecimal value) throws ParameterException {
		if (value == null) {
			throw new ParameterException("Null value");
		}
		return value.toPlainString();
	}

	/**
	 * Value 1.23 is 123 !
	 * 
	 * @param value
	 * @return
	 */
	public static String toStringCurrencyFormated(int value,
			boolean keepNonSignifiantDecimals) {

		log.debug("Value[" + value + "] knsd[" + keepNonSignifiantDecimals
				+ "]");

		String stringValue = Integer.toString(value);
		int decimal = value % 100;
		int entier = Math.abs((value - decimal) / 100);

		log.debug("stringValue[" + stringValue + "]decimal[" + decimal
				+ "]entier[" + entier + "]");

		StringBuffer sbValue = new StringBuffer();
		if (value < 0) {
			sbValue.append("-");
		}
		sbValue.append(Integer.toString(entier));
		if (keepNonSignifiantDecimals || decimal != 0) {
			sbValue.append(".");
			if (decimal != 0) {
				sbValue.append(stringValue.substring(stringValue.length() - 2));
			} else {
				sbValue.append("00");
			}
		}
		log.debug("stringValue[" + sbValue + "]");
		return sbValue.toString();
	}

	public static String toStringTwoDigits(int value) throws ParameterException {
		if (value < 0 || value > 99) {
			throw new ParameterException(value + " is upper than 99");
		}
		if (value > 9) {
			return Integer.toString(value);
		} else {
			return "0" + Integer.toString(value);
		}
	}

	/**
	 * Value 1.23 is 123 !
	 * 
	 * @param value
	 * @return
	 * @throws ParameterException
	 * @throws InputException
	 */
	public static Integer toIntegerCurrencyFormated(String data)
			throws ParameterException {
		if (data == null || data.length() <= 0) {
			throw new ParameterException("Null value");
		}
		try {
			new Double(data);
		} catch (NumberFormatException e) {
			throw new ParameterException("Bad input value (level 1)", e);
		}
		StringBuffer sb = null;
		try {
			int decimalPosition = data.indexOf(".");
			sb = new StringBuffer();
			if (decimalPosition < 0) {
				sb.append(data);
				sb.append("00");
			} else {
				if (decimalPosition > 0) {
					sb.append(data.substring(0, decimalPosition));
				}
				if (data.length() > decimalPosition + 2) {
					sb.append(data.substring(decimalPosition + 1,
							decimalPosition + 3));
				} else if (data.length() > decimalPosition + 1) {
					sb.append(data.substring(decimalPosition + 1,
							decimalPosition + 2));
					sb.append("0");
				} else if (data.length() > decimalPosition) {
					sb.append("00");
				}
			}
			return new Integer(sb.toString());
		} catch (NumberFormatException e) {
			throw new ParameterException("Bad input value [" + data + "]", e);
		}
	}

	public static BigDecimal toBigDecimalNotNullDivide(String data)
			throws ParameterException {
		if (Tools.isNullOrEmpty(data)) {
			return BigDecimal.ZERO;
		}
		return toBigDecimalNotNull(data).divide(new BigDecimal(100));
	}

	public static BigDecimal toBigDecimalNotNull(String data)
			throws ParameterException {
		if (Tools.isNullOrEmpty(data)) {
			return BigDecimal.ZERO;
		}
		return toBigDecimal(data);
	}

	public static BigDecimal toBigDecimal(String data)
			throws ParameterException {
		if (Tools.isNullOrEmpty(data)) {
			return null;
		}
		try {
			return new BigDecimal(data);
		} catch (NumberFormatException e) {
			throw new ParameterException("Bad input value [" + data + "]", e);
		}
	}

	public static int Compare(String id1, String id2) {
		return id1.compareTo(id2);
	}

	public static String SeekAndextractIdFromText(String theText) {
		if (theText.indexOf("(id=") >= 0) {

		}
		return null;
	}

	public static String addIdToText(String theText, String theId) {
		return theText + " (id=" + theId + ")";

	}

	public static Date startOfCurentDay(Date theDate) throws ParameterException {

		return startOfDay(theDate, 0);

	}

	public static Date endOfDay(Date theDate) throws ParameterException {

		return new Date(startOfDay(theDate, 1).getTime() - 1);

	}

	public static Date startOfDay(Date theDate, int offsetInDay)
			throws ParameterException {

		if (theDate != null) {

			GregorianCalendar gDate = new GregorianCalendar();
			gDate.setTime(theDate);
			gDate = new GregorianCalendar(gDate.get(GregorianCalendar.YEAR),
					gDate.get(GregorianCalendar.MONTH),
					gDate.get(GregorianCalendar.DAY_OF_MONTH) + offsetInDay);
			return gDate.getTime();

		} else {

			throw new ParameterException("The date is null.");

		}
	}

	public static Date startOfMonth(Date theDate) throws ParameterException {

		if (theDate != null) {

			GregorianCalendar gDate = new GregorianCalendar();
			gDate.setTime(theDate);
			gDate = new GregorianCalendar(gDate.get(GregorianCalendar.YEAR),
					gDate.get(GregorianCalendar.MONTH), 1);
			return gDate.getTime();

		} else {

			throw new ParameterException("The date is null.");

		}
	}

	public static Date endOfMonth(Date theDate) throws ParameterException {

		if (theDate != null) {

			GregorianCalendar gDate = new GregorianCalendar();
			gDate.setTime(theDate);
			gDate = new GregorianCalendar(gDate.get(GregorianCalendar.YEAR),
					gDate.get(GregorianCalendar.MONTH) + 1, 1);
			gDate.add(GregorianCalendar.MINUTE, -1);
			return gDate.getTime();

		} else {

			throw new ParameterException("The date is null.");

		}
	}

	public static Date startOfYear(Date theDate) throws ParameterException {

		if (theDate != null) {

			GregorianCalendar gDate = new GregorianCalendar();
			gDate.setTime(theDate);
			gDate = new GregorianCalendar(gDate.get(GregorianCalendar.YEAR), 0,
					1);
			return gDate.getTime();

		} else {

			throw new ParameterException("The date is null.");

		}
	}

	public static Date endOfYear(Date theDate) throws ParameterException {

		if (theDate != null) {

			GregorianCalendar gDate = new GregorianCalendar();
			gDate.setTime(theDate);
			gDate = new GregorianCalendar(
					gDate.get(GregorianCalendar.YEAR) + 1, 0, 1);
			gDate.add(GregorianCalendar.MINUTE, -1);
			return gDate.getTime();

		} else {

			throw new ParameterException("The date is null.");

		}
	}

	public static TreeMap<String, Item> orderingItemsMap(Map<String, Item> items) {

		TreeMap<String, Item> itemsOrderByCode = new TreeMap<String, Item>();

		log.debug("START ITERATE byDaySales");

		for (Iterator<String> iterator = items.keySet().iterator(); iterator
				.hasNext();) {
			String itemId = iterator.next();
			Item item = items.get(itemId);
			String indexValue = item.getItem().getCode();
			Item orderedItem = items.get(indexValue);
			if (orderedItem != null) {
				indexValue = indexValue + "-" + itemId;
			}
			itemsOrderByCode.put(indexValue, item);
		}

		return itemsOrderByCode;

	}

	/**
	 * Extract day from file name (yyyyMMdd*.xml)
	 * 
	 * @param file
	 * @return date as string in format : yyyyMMdd
	 * @throws ParseException
	 */
	public static String extractDayFromSalesFileNameAsString(String fileName) {

		// chech filename & date. if error, throw exception
		extractDayFromSalesFileNameAsDate(fileName);

		return fileName.substring(0, 8);
	}

	public static Date extractDayFromSalesFileNameAsDate(String fileName) {

		Date dateAsDate = null;
		if (fileName != null && fileName.length() >= 12
				&& fileName.endsWith(".xml")
				&& !"posparams.xml".equals(fileName)) {
			dateAsDate = extractDayFromString(fileName);
		}
		return dateAsDate;

	}

	public static Date extractDayFromString(String fileName) {

		Date dateAsDate;
		try {
			DateFormat df = POSConstants.SDF_DAY;
			// check if file name is a valid date.
			boolean lenient = df.isLenient();
			df.setLenient(false);
			dateAsDate = df.parse(fileName.substring(0, 8));
			df.setLenient(lenient);
		} catch (ParseException e) {
			return null;
		}
		return dateAsDate;

	}

	public static BigDecimal getWitoutTax(BigDecimal withTVA, BigDecimal tva) {

		BigDecimal tvaToApply = tva.divide(new BigDecimal(100)).add(
				BigDecimal.ONE);

		return withTVA.divide(tvaToApply, RoundingMode.HALF_DOWN);
	}

	public static long convertMinutesInDateUnit(int minutes) {
		return 60000l * minutes;
	}
}