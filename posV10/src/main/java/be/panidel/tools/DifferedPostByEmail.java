package be.panidel.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;

import be.panidel.common.POSParameters;
import be.panidel.common.POSException.POSException;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.AbstractCashRegister;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.reports.Messages;
import be.panidel.pos.model.CashRegisterModel;

public class DifferedPostByEmail extends Thread {

	private static final Logger log = Logger.getLogger("DifferedPostByEmail");
	private static final String SALES_FILE_NAME = "sales.zip";

	private AbstractCashRegister cashRegister;

	private static boolean isRunning;

	public static void initAndRunOnlyOnce(AbstractCashRegister cashRegister)
			throws POSException {
		if (isRunning) {
			throw new POSException("Always running");
		}
		new DifferedPostByEmail(cashRegister).start();
		isRunning = true;
	}

	public DifferedPostByEmail(AbstractCashRegister cashRegister) {
		super();
		this.cashRegister = cashRegister;
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
	}

	@Override
	public void run() {
		try {
			while (true) {

				/*
				 * Init present and elapsed time since lat eMail sendings
				 */
				log.debug("PeriodicEvent");
				GregorianCalendar nowAsGC = new GregorianCalendar();
				long elapsedTimeSinceLastPost = nowAsGC.getTimeInMillis()
						- TimerProp.instance()
								.getLastAccess(TimerProp.LAST_SYNC_BY_EMAIL)
								.getTime();
				log.info("Elapsed time since last post : "
						+ elapsedTimeSinceLastPost + " mSec. (Setup:"
						+ POSParameters.instance().getEMailSyncPeriodicity()
						+ " minutes)");

				/*
				 * if time between two eMail sending is elapsed
				 */
				if (elapsedTimeSinceLastPost > Tools
						.convertMinutesInDateUnit(POSParameters.instance()
								.getEMailSyncPeriodicity())) {

					log.info("Up to syncPeriod");

					/*
					 * if pos is idle
					 */
					if (isPosIdle(nowAsGC.getTimeInMillis())) {
						log.info("Sync by eMail");

						/*
						 * sync by eMail
						 */
						try {
							log.debug("Group sales");

							/*
							 * select files by delta since last eMail sending
							 */
							File sourceDir = POSParameters.instance()
									.getCashregisterStorageCaissesGroupbyday();
							FileFilter ff = new FileXMLFilterByDate(TimerProp
									.instance().getLastAccess(
											TimerProp.LAST_SYNC_BY_EMAIL));
							File[] fileList = sourceDir.listFiles(ff);
							log.info("Files quantity to sync : "
									+ fileList.length);

							/*
							 * If some files have changed
							 */
							if (fileList.length > 0) {

								/*
								 * compress and group files in memory
								 */
								log.info("Post sales by eMail");
								ByteArrayOutputStream baos = ZipHelper
										.zipFiles(fileList);
								DataSource source = new ByteArrayDataSource(
										baos.toByteArray(), "application/zip");

								/*
								 * post message by eMail
								 */
								// eMail sales
								log.debug("Send EMail Sales");
								MailHelper.instance().postByMail(
										POSParameters.instance()
												.getSyncEMailBox(),
										POSParameters.instance()
												.getSyncMsgSubjectPrefix()
												+ CashRegister.COMPUTERNAME,
										source, SALES_FILE_NAME);

								/*
								 * save time of last change in file
								 */
								long lastAccessTime = 0;
								for (File file : fileList) {
									if (file.lastModified() > lastAccessTime) {
										lastAccessTime = file.lastModified();
									}
								}
								TimerProp.instance().setLastAccess(
										TimerProp.LAST_SYNC_BY_EMAIL,
										new Date(lastAccessTime));
							}

							TimerProp.instance().setLastAccess(
									TimerProp.LAST_SYNC_BY_EMAIL,
									new Date(nowAsGC.getTimeInMillis()));
						} catch (MessagingException e1) {
							log.error("", e1);
						}

						/*
						 * Send eMail result by period
						 */
						if (nowAsGC.get(GregorianCalendar.DAY_OF_WEEK) != GregorianCalendar.SUNDAY
								&& nowAsGC.get(GregorianCalendar.HOUR_OF_DAY) >= 8
								&& nowAsGC.get(GregorianCalendar.HOUR_OF_DAY) <= 18) {
							try {
								// eMail result message
								log.info("Send EMail result message");
								MailHelper
										.instance()
										.postEmail(
												POSParameters.instance()
														.getMgmtEMailBox(),
												POSParameters
														.instance()
														.getSyncMsgSubjectPrefix()
														+ CashRegister.COMPUTERNAME,
												Messages.MessageResultByDay(
														Tools.startOfCurentDay(new Date()),
														Tools.endOfDay(new Date()))
														.toString());
							} catch (MessagingException e1) {
								log.error("", e1);
							}
						}
					} else {
						log.debug("POS is NOT idle, wait for eMail");
					}
				} else {
					log.info("Low then syncPeriod");
				}

				/*
				 * scan delay
				 */
				log.info("Sleep for : "
						+ POSParameters.instance().getPeriodicEventScan()
						+ " minute(s).");
				Thread.sleep(Tools.convertMinutesInDateUnit(POSParameters
						.instance().getPeriodicEventScan()));

			}
		} catch (IOException e) {
			log.error("", e);
		} catch (ParameterException e) {
			log.debug("", e);
		} catch (InterruptedException e) {
			log.debug("", e);
		}
	}

	public boolean isPosIdle(long now) throws ParameterException {

		CashRegisterModel cashregisterModel = cashRegister
				.getCashregisterModel();

		// if sales ongoing since less then x minutes = not idle
		if (cashregisterModel.isSaleOpenned()) {
			long saleDuration = now
					- cashregisterModel.getStartOfSale().getTime();
			log.info("Sale open since : " + saleDuration + " mSec. (Setup:"
					+ POSParameters.instance().getSaleIsIdleInMinutes()
					+ " minutes)");
			if (saleDuration > Tools.convertMinutesInDateUnit(POSParameters
					.instance().getSaleIsIdleInMinutes())) {
				log.info("OK for sync.");
				return true;
			} else {
				log.info("NOK for sync.");
				return false;
			}
		} else {
			long idleTime = now - cashRegister.getLastSale();
			log.info("POS is idle since : " + idleTime + " mSec. (Setup:"
					+ POSParameters.instance().getPosIsIdleInMinutes()
					+ " minutes)");
			if (idleTime > Tools.convertMinutesInDateUnit(POSParameters
					.instance().getPosIsIdleInMinutes())) {
				log.info("OK for sync.");
				return true;
			} else {
				log.info("NOK for sync.");
				return false;
			}
		}
	}

}
