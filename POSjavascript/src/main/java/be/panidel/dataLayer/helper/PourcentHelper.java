package be.panidel.dataLayer.helper;

import java.util.Date;

import org.slf4j.Logger;

public class PourcentHelper {

	private Logger log;
	private int lastDisplay = 0;
	private long lastDisplayTime = 0;
	private long waitTimeToDisplay = 1 * 1000;
	private long currentValue = 0;
	private long maxValue = 0;
	String message;

	public PourcentHelper(long maxValue, String message, Logger log) {

		this.log = log;
		this.maxValue = (maxValue > 0 ? maxValue : 1);
		this.message = message;

	}

	public int increment(long value) {

		currentValue += value;

		int pourcent = (int) (currentValue * 100 / maxValue);

		if (pourcent != lastDisplay) {
			Date date = new Date();
			long time = date.getTime();
			if (time > lastDisplayTime + waitTimeToDisplay) {
				lastDisplay = pourcent;
				lastDisplayTime = time;
				log.info("Done : " + pourcent + " % - " + message);
			}
		}
		return pourcent;
	}
}
