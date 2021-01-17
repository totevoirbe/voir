package be.panidel.tools;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.mail.MessagingException;
import org.apache.log4j.Logger;
import org.junit.Test;

import be.panidel.common.POSParameters;
import be.panidel.pos.exception.ParameterException;

public class TestProcesseMail {

	private static final Logger log = Logger.getLogger("7H7sBGjUF8yeGUwtvC2");

	private static final String OUTPUT_FOLDER = "C:/testzipout";

	@Test
	public void testProcessMail() {
		try {
			File file = new File(OUTPUT_FOLDER);
			if (!file.isDirectory()) {
				file.mkdirs();
			}
			MailHelper.instance().processMail(
					POSParameters.instance().getSyncMsgSubjectPrefix(), file,
					new Date(0), POSParameters.instance().getSyncPopHost(),
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
}
