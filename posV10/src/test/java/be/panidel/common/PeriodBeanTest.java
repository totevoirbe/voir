package be.panidel.common;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import be.panidel.pos.exception.ParameterException;

public class PeriodBeanTest {

	@Test
	public void testPeriodBean() {
		assertNotNull(new PeriodBean(new Date(), new Date()));
	}

	@Test
	public void testIsInPeriodAsCompleteDay() {

		try {

			Date startOfperiod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101100");
			Date endOfperiod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101125");
			Date aDateBeforePeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003091023");
			Date aDateInPeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101123");
			Date aDateBeforeButInPeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003100900");
			Date aDateAfterButInPeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101223");
			Date aDateAfterPeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003111223");
			PeriodBean pb = new PeriodBean(startOfperiod, endOfperiod);
			assertEquals(-1, pb.isInPeriodAsCompleteDay(aDateBeforePeriod));
			assertEquals(0, pb.isInPeriodAsCompleteDay(aDateInPeriod));
			assertEquals(0, pb.isInPeriodAsCompleteDay(aDateBeforeButInPeriod));
			assertEquals(0, pb.isInPeriodAsCompleteDay(aDateAfterButInPeriod));
			assertEquals(1, pb.isInPeriodAsCompleteDay(aDateAfterPeriod));

		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (ParameterException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testIsInPeriod() {

		try {

			Date startOfperiod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101100");
			Date endOfperiod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101125");
			Date aDateBeforePeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101023");
			Date aDateInPeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101123");
			Date aDateAfterPeriod = new SimpleDateFormat("yyyyMMddHHmm")
					.parse("201003101223");
			PeriodBean pb = new PeriodBean(startOfperiod, endOfperiod);
			assertEquals(-1, pb.isInPeriod(aDateBeforePeriod));
			assertEquals(0, pb.isInPeriod(aDateInPeriod));
			assertEquals(1, pb.isInPeriod(aDateAfterPeriod));

		} catch (ParseException e) {
			fail(e.getMessage());
		} catch (ParameterException e) {
			fail(e.getMessage());
		}
	}

}