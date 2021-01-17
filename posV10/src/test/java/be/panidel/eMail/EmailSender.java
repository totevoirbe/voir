package be.panidel.eMail;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class EmailSender {

	private static String smtpServer = "smtp01.mbn1.net";

	// private static String smtpServer = "smtp.skynet.be";
	private static String port = "25";
	private static String user = "philfran@voir.be";
	private static String password = "pf8210";
	private static String auth = "false";
	private static String from = "philfran@voir.be";

	public static void main(String[] args) {

		try {
			EmailSender emailer = new EmailSender();
			emailer.sendEmail("subject", "message",
					new String[] { "philippe@voir.be" });
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Properties prepareProperties() {
		Properties props = new Properties();
		props.setProperty("mail.smtp.host", smtpServer);
		props.setProperty("mail.smtp.port", port);
		props.setProperty("mail.smtp.user", user);
		props.setProperty("mail.smtp.password", password);
		props.setProperty("mail.smtp.auth", auth);
		return props;
	}

	private MimeMessage prepareMessage(Session mailSession, String charset,
			String from, String subject, String HtmlMessage, String[] recipient) {
		// Multipurpose Internet Mail Extensions
		MimeMessage message = null;
		try {
			message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			for (int i = 0; i < recipient.length; i++)
				message.addRecipient(Message.RecipientType.TO,
						new InternetAddress(recipient[i]));
			message.setContent(HtmlMessage, "text/html; charset=\"" + charset
					+ "\"");
		} catch (Exception ex) {
			Logger.getLogger(EmailSender.class.getName()).log(Level.ERROR,
					null, ex);
		}
		return message;
	}

	public void sendEmail(String subject, String HtmlMessage, String[] to) {
		Transport transport = null;
		try {
			Properties props = prepareProperties();
			Session mailSession = Session.getDefaultInstance(props,
					new SMTPAuthenticator(from, password, true));
			transport = mailSession.getTransport("smtp");
			MimeMessage message = prepareMessage(mailSession, "ISO-8859-2",
					from, subject, HtmlMessage, to);
			transport.connect();
			Transport.send(message);
		} catch (Exception ex) {
			Logger.getLogger(EmailSender.class.getName()).log(Level.ERROR,
					null, ex);
		} finally {
			try {
				transport.close();
			} catch (MessagingException ex) {
				Logger.getLogger(EmailSender.class.getName()).log(Level.ERROR,
						null, ex);
			}
		}
	}

}
