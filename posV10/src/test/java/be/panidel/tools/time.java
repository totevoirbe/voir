package be.panidel.tools;

import java.util.Date;

import be.panidel.common.POSConstants;

public class time {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date();
		date.setTime(Long.parseLong("1265968558093"));
		
		System.out.println(POSConstants.SDF_DATE_AND_TIME.format(date));

	}

}
