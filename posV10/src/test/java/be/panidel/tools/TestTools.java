package be.panidel.tools;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import be.panidel.common.POSConstants;
import be.panidel.pos.exception.ParameterException;

public class TestTools {

	@Test
	public void testIsNullOrEmptyString() {

		assertFalse(Tools.isNullOrEmpty("Not yet implemented"));
		assertTrue(Tools.isNullOrEmpty(" "));
		assertTrue(Tools.isNullOrEmpty(""));

	}

	@Test
	public void testIsNullOrEmptyListOfQ() {

		List<String> aList = null;
		assertTrue(Tools.isNullOrEmpty(aList));
		aList = new ArrayList<String>();
		assertTrue(Tools.isNullOrEmpty(aList));
		aList.add(new String());
		assertFalse(Tools.isNullOrEmpty(aList));

	}

	@Test
	public void testIsNullOrEmptyMapOfQQ() {

		Map<String, String> aMap = null;
		assertTrue(Tools.isNullOrEmpty(aMap));
		aMap = new HashMap<String, String>();
		assertTrue(Tools.isNullOrEmpty(aMap));
		aMap.put(new String(), new String());
		assertFalse(Tools.isNullOrEmpty(aMap));

	}

	@Test
	public void testGetDefaultFont() {

		assertNotNull(Tools.getDefaultFont(10));

	}

	@Test
	public void testToStringBoolean() {

		assertSame("true", Tools.toString(true));
		assertSame("false", Tools.toString(false));

	}

	@Test
	public void testToBoolean() {

		assertTrue(Tools.toBoolean("true"));
		assertTrue(Tools.toBoolean("TRue"));
		assertTrue(Tools.toBoolean("VRAI"));
		assertTrue(Tools.toBoolean("vrai"));
		assertFalse(Tools.toBoolean("..."));
		assertFalse(Tools.toBoolean("null"));

	}

	@Test
	public void testToStringNotNull() {

		assertNotNull(Tools.toStringNotNull(null));
		assertNotNull(Tools.toStringNotNull(new Integer(0)));
		assertEquals(Tools.toStringNotNull(new Integer(4)), "4");

	}

	@Test
	public void testToIntegerNotNull() {

		boolean exceptionThrowed = false;
		try {
			assertNotNull(Tools.toIntegerNotNull(null));
		} catch (ParameterException e) {
			exceptionThrowed = true;
		}
		assertTrue(exceptionThrowed);

	}

	@Test
	public void testToInteger() {

		try {
			assertNotNull(Tools.toInteger("3"));
		} catch (ParameterException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testToLong() {

		try {
			assertNotNull(Tools.toLong("3"));
		} catch (ParameterException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testToStringMultiplyNotNull() {

		assertEquals("100", Tools.toStringMultiplyNotNull(BigDecimal.ONE));
		assertEquals("0", Tools.toStringMultiplyNotNull(null));

	}

	@Test
	public void testToStringMultiply() {

		try {
			assertEquals("100", Tools.toStringMultiply(BigDecimal.ONE));
		} catch (ParameterException e) {
			fail(e.getMessage());
		}

		boolean throwException = false;
		try {
			assertNull(Tools.toStringMultiply(null));
		} catch (ParameterException e) {
			throwException = true;
		}
		assertTrue(throwException);
	}

	@Test
	public void testToStringBigDecimal() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testToStringCurrencyFormated() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testToIntegerCurrencyFormated() {
		try {
			assert (new Integer(0).equals(Tools.toIntegerCurrencyFormated("0")));
			assert (new Integer(2500).equals(Tools
					.toIntegerCurrencyFormated("25")));
			assert (new Integer(250).equals(Tools
					.toIntegerCurrencyFormated("2.5")));
			assert (new Integer(250).equals(Tools
					.toIntegerCurrencyFormated("2.50")));
			assert (new Integer(250).equals(Tools
					.toIntegerCurrencyFormated("02.50")));
			assert (new Integer(250).equals(Tools
					.toIntegerCurrencyFormated("02.501234")));
			assert (new Integer(50).equals(Tools
					.toIntegerCurrencyFormated(".501234")));
			assert (new Integer(50).equals(Tools
					.toIntegerCurrencyFormated(".50")));
			assert (new Integer(50).equals(Tools
					.toIntegerCurrencyFormated(".5")));
			assert (new Integer(50).equals(Tools
					.toIntegerCurrencyFormated("0.5")));
		} catch (ParameterException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testToBigDecimalNotNullDivide() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testToBigDecimalNotNull() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testToBigDecimal() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testCompare() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testSeekAndextractIdFromText() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testAddIdToText() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testStartOfCurentDay() {

		Date aDate = null;
		Date testDate = null;
		Date newDate = null;
		try {

			aDate = new SimpleDateFormat("yyyyMMddHHmm").parse("201003101100");
			testDate = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003100000");
			newDate = Tools.startOfCurentDay(aDate);
			assert (newDate.equals(testDate));

			aDate = new SimpleDateFormat("yyyyMMddHHmm").parse("201001010000");
			testDate = new SimpleDateFormat("yyyyMMdd").parse("20100101");
			newDate = Tools.startOfCurentDay(aDate);
			assertEquals(newDate, testDate);

		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (ParameterException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testEndOfDay() {
		Date aDate = null;
		Date testDate = null;
		Date newDate = null;
		try {

			aDate = new SimpleDateFormat("yyyyMMddHHmm").parse("201003101100");
			testDate = new SimpleDateFormat("yyyyMMddHHmmssSSS")
					.parse("20100310235959999");
			newDate = Tools.endOfDay(aDate);
			assertEquals(newDate, testDate);

		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (ParameterException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testStartOfDay() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testOrderingItemsMap() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testObject() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testEquals() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testClone() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testToString() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testNotify() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testWait() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		System.err.println("Not yet implemented");
	}

	@Test
	public void testExtractDayFromSalesFileNameAsString() {

		assertEquals("20101219", Tools
				.extractDayFromSalesFileNameAsString("20101219.xml"));
		assertEquals("20101219", Tools
				.extractDayFromSalesFileNameAsString("20101219xxxx.xml"));
		assertEquals("20101912", Tools
				.extractDayFromSalesFileNameAsString("20101912.xml"));
		assertEquals("20101912", Tools
				.extractDayFromSalesFileNameAsString("20101912"));
		assertEquals("20101912", Tools
				.extractDayFromSalesFileNameAsString("20101912.xml"));

	}

	@Test
	public void testExtractDayFromSalesFileNameAsDate() {

		try {
			assertEquals(POSConstants.SDF_DAY.parse("20101219"), Tools
					.extractDayFromSalesFileNameAsDate("20101219.xml"));
			assertEquals(POSConstants.SDF_DAY.parse("20101219"), Tools
					.extractDayFromSalesFileNameAsDate("20101219xxxx.xml"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}

	}
}
