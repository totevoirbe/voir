package be.panidel.common;

import java.awt.Font;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class POSConstants {

	public enum DataSourceName {
		EMPLOYEE, COMPANY, PAYMENTMODE, ARTICLEGROUP, ARTICLE, ARTICLECOMPOSITION, ARTICLEPRICETABLE, SALETRANSACTION
	};

	public final static String LINE_SEPARATOR = System
			.getProperty("line.separator");
	public final static String TAB = "\t";

	public static final Font DEFAULT_FONT = new Font(Font.SERIF, Font.BOLD, 14);

	public final static String CENTURY = "20";

	public final static String ADD_PRODUCT = "addProduct";
	public final static String ADD_PAYE = "addPaye";

	/**
	 * yyyyMMdd
	 */
	public final static SimpleDateFormat SDF_DAY = new SimpleDateFormat(
			"yyyyMMdd");
	/**
	 * yyyyMM
	 */
	public final static SimpleDateFormat SDF_MONTH = new SimpleDateFormat(
			"yyyyMM");
	/**
	 * yyyy
	 */
	public final static SimpleDateFormat SDF_DAY_STRUCTURED = new SimpleDateFormat(
			"dd/MM/yyyy");
	/**
	 * HH:mm
	 */
	public final static SimpleDateFormat SDF_TIME = new SimpleDateFormat(
			"HH:mm");
	/**
	 * dd/MM
	 */
	public final static SimpleDateFormat SDF_DATE = new SimpleDateFormat(
			"dd/MM");
	/**
	 * dd/MM HH:mm
	 */
	public final static SimpleDateFormat SDF_SHORTDATE_SHORTTIME = new SimpleDateFormat(
			"dd/MM HH:mm");
	/**
	 * EEE, d MMM yy
	 */
	public final static SimpleDateFormat SDF_FULLDAY = new SimpleDateFormat(
			"EEE, d MMM yy");
	/**
	 * EEE
	 */
	public final static SimpleDateFormat SDF_DAYOFWEEK = new SimpleDateFormat(
			"EEE");
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public final static DateFormat SDF_FOR_FILE_SALES = new SimpleDateFormat(
			"yyyyMMddHHmmssSSS");
	/**
	 * dd/MM/yyyy HH:mm:ssSSS
	 */
	public final static DateFormat SDF_DATE_AND_TIME = new SimpleDateFormat(
			"dd/MM/yyyy HH:mm:ssSSS");

	public final static MathContext MC = new MathContext(2,
			RoundingMode.HALF_DOWN);

	public final static String REJECTED_PAY_TYPE = "10";
	public final static String FIDELITY_PAY_TYPE = "40";
}