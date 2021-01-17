package be.panidel.tools;

import static org.junit.Assert.*;

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
import org.junit.Test;

import be.panidel.common.POSParameters;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.reports.Messages;

public class TestMailHelper {

	private static final Logger log = Logger.getLogger("TestMailHelper");

	private static final String SOURCE_FOLDER = "C:/testzip";
	private static final String OUTPUT_FOLDER = "C:/testzipout";

	private static final String SALES_FILE_NAME = "sales.zip";

	// @Test
	public void testPostByMail() {
		try {
			File sourceDir = new File(SOURCE_FOLDER);
			File[] fileList = sourceDir.listFiles();
			if (fileList.length > 0) {
				ByteArrayOutputStream baos = ZipHelper.zipFiles(fileList);
				DataSource source = new ByteArrayDataSource(baos.toByteArray(),
						"application/zip");
				String recipients[] = POSParameters.instance()
						.getSyncEMailBox();
				MailHelper.instance().postByMail(
						recipients,
						POSParameters.instance().getSyncMsgSubjectPrefix()
								+ CashRegister.COMPUTERNAME, source,
						SALES_FILE_NAME);

			}
		} catch (MessagingException e) {
			log.debug("", e);
			fail("MessagingException");
		} catch (IOException e) {
			log.debug("", e);
			fail("IOException");
		} catch (ParameterException e) {
			log.debug("", e);
			fail("ParameterException");
		}
	}

	// @Test
	public void testMessageByMail() {
		try {
			String recipients[] = POSParameters.instance().getSyncEMailBox();
			for (int i = 1; i <= 31; i++) {
				Date date = new GregorianCalendar(2012, 0, i).getTime();
				MailHelper.instance().postEmail(
						recipients,
						POSParameters.instance().getSyncMsgSubjectPrefix()
								+ CashRegister.COMPUTERNAME,
						Messages.MessageResultByDay(date, date).toString());
			}
		} catch (MessagingException e) {
			log.debug("", e);
			fail("MessagingException");
		} catch (IOException e) {
			log.debug("", e);
			fail("IOException");
		} catch (ParameterException e) {
			log.debug("", e);
			fail("ParameterException");
		}
	}

	// @Test
	public void testSimpleMessageByMail() {
		try {
			String recipients[] = POSParameters.instance().getSyncEMailBox();
			MailHelper.instance().postEmail(
					recipients,
					POSParameters.instance().getSyncMsgSubjectPrefix()
							+ CashRegister.COMPUTERNAME, "Test simple message");
		} catch (MessagingException e) {
			log.debug("", e);
			fail("MessagingException");
		} catch (IOException e) {
			log.debug("", e);
			fail("IOException");
		} catch (ParameterException e) {
			log.debug("", e);
			fail("ParameterException");
		}
	}

	@Test
	public void testProcessMail() {
		try {
			File file = new File(OUTPUT_FOLDER);
			if (!file.isDirectory()) {
				file.mkdirs();
			}
			MailHelper.instance().processMail(
					POSParameters.instance().getSyncMsgSubjectPrefix(),
					file,
					TimerProp.instance().getLastAccess(
							TimerProp.LAST_SYNC_BY_EMAIL),
					POSParameters.instance().getSyncPopHost(),
					POSParameters.instance().getSyncEMailUser(),
					POSParameters.instance().getSyncEMailPwd());
		} catch (MessagingException e) {
			log.debug("", e);
			fail("MessagingException");
		} catch (IOException e) {
			log.debug("", e);
			fail("IOException");
		} catch (ParameterException e) {
			log.debug("", e);
			fail("ParameterException");
		}
	}

	// @Test
	public void testPostAndReadMail() {
		try {
			while (true) {
				Date lastPostMail = TimerProp.instance().getLastAccess(
						TimerProp.LAST_SYNC_BY_EMAIL);
				File sourceDir = new File(SOURCE_FOLDER);
				FileFilter ff = new FileXMLFilterByDate(lastPostMail);
				File[] fileList = sourceDir.listFiles(ff);
				if (fileList.length > 0) {
					lastPostMail = new Date();
					ByteArrayOutputStream baos = ZipHelper.zipFiles(fileList);
					DataSource source = new ByteArrayDataSource(
							baos.toByteArray(), "application/zip");
					String recipients[] = POSParameters.instance()
							.getSyncEMailBox();
					MailHelper.instance().postByMail(
							recipients,
							POSParameters.instance().getSyncMsgSubjectPrefix()
									+ CashRegister.COMPUTERNAME, source,
							SALES_FILE_NAME);
					TimerProp.instance().setLastAccess(
							TimerProp.LAST_SYNC_BY_EMAIL, lastPostMail);
					log.debug("SendEMail");

					Thread.sleep(10000);
					File file = new File(OUTPUT_FOLDER);
					if (!file.isDirectory()) {
						file.mkdirs();
					}
					MailHelper.instance().processMail(
							POSParameters.instance().getSyncMsgSubjectPrefix(),
							file,
							TimerProp.instance().getLastAccess(
									TimerProp.LAST_PROCESS_SYNC_EMAIL),
							POSParameters.instance().getSyncPopHost(),
							POSParameters.instance().getSyncEMailUser(),
							POSParameters.instance().getSyncEMailPwd());
				}
			}
		} catch (MessagingException e) {
			log.debug("", e);
			fail("MessagingException");
		} catch (IOException e) {
			log.debug("", e);
			fail("IOException");
		} catch (ParameterException e) {
			log.debug("", e);
			fail("ParameterException");
		} catch (InterruptedException e) {
			log.debug("", e);
			fail("InterruptedException");
		}
	}
}
