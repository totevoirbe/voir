package be.panidel.tools;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Date;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.junit.Test;

import be.panidel.common.POSParameters;
import be.panidel.dao.exeption.DAOException;
import be.panidel.pos.exception.ParameterException;
import be.panidel.pos.gui.CashRegister;
import be.panidel.pos.gui.reports.Messages;

public class TestMailMessage {

	private static final Logger log = Logger.getLogger("TestMailMessage");
	private static final String SALES_FILE_NAME = "sales.zip";

	@Test
	public void testSimpleMessageByMail() {
		for (int i = 0; i < 5; i++) {
			try {
				log.debug("Group sales");

				// Group sales
				SalesFilesGroupingTool.doIt(POSParameters.instance()
						.getResultsStorageCaissesSales(), POSParameters
						.instance().getResultsStorageCaissesGroupbyday(),
						POSParameters.instance()
								.getResultsStorageCaissesArchives(),
						POSParameters.instance()
								.getResultsStorageCaissesRejected());
				// post
				File sourceDir = POSParameters.instance()
						.getCashregisterStorageCaissesGroupbyday();
				FileFilter ff = new FileXMLFilterByDate(new Date(0));
				File[] fileList = sourceDir.listFiles(ff);
				log.info("Files quantity to sync : " + fileList.length);
				if (fileList.length > 0) {
					log.info("Post sales by eMail");
					ByteArrayOutputStream baos = ZipHelper.zipFiles(fileList);
					DataSource source = new ByteArrayDataSource(
							baos.toByteArray(), "application/zip");
					// eMail sales
					log.debug("Send EMail Sales");
					MailHelper.instance().postByMail(
							POSParameters.instance().getSyncEMailBox(),
							POSParameters.instance().getSyncMsgSubjectPrefix()
									+ CashRegister.COMPUTERNAME, source,
							SALES_FILE_NAME);
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
				// eMail result message
				log.info("Send EMail result message");
				MailHelper.instance().postEmail(
						POSParameters.instance().getMgmtEMailBox(),
						POSParameters.instance().getSyncMsgSubjectPrefix()
								+ CashRegister.COMPUTERNAME,
						Messages.MessageResultByDay(
								Tools.startOfCurentDay(new Date()),
								Tools.endOfDay(new Date())).toString());
			} catch (MessagingException e1) {
				log.error("", e1);
				fail();
			} catch (IOException e1) {
				log.error("", e1);
				fail();
			} catch (ParameterException e1) {
				log.error("", e1);
				fail();
			} catch (DAOException e1) {
				log.error("", e1);
				fail();
			}
		}
	}
}
