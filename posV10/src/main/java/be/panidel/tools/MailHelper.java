package be.panidel.tools;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.SearchTerm;

import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;
import be.panidel.common.POSParameters;
import be.panidel.pos.exception.ParameterException;

import java.io.*;
import java.util.*;

public class MailHelper {

	private static final Logger log = Logger.getLogger("MailHelper");

	private static MailHelper instance;
	private static Object lock = new Object();

	public static MailHelper instance() throws ParameterException,
			MessagingException {
		if (instance == null) {
			synchronized (lock) {
				instance = new MailHelper(POSParameters.instance()
						.getSyncMsgFrom(), POSParameters.instance()
						.getSyncSmtpHost());
			}
		}
		return instance;
	}

	private Session sendSession;
	private Session receiveSession;
	private InternetAddress addressFrom;

	private MailHelper(String from, String smtp) throws MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		sendSession = Session.getInstance(props);
		sendSession.setDebug(log.isDebugEnabled());
		log.debug("init message");
		addressFrom = new InternetAddress(from);

		log.debug("open provider connection");
		receiveSession = Session.getInstance(System.getProperties());
		receiveSession.setDebug(log.isDebugEnabled());

	}

	public void postEmail(String recipients[], String subject, String message)
			throws MessagingException, IOException {

		Message sendMsg = initMsg(recipients, subject);
		sendMsg.setText(message);
		sendMsg(sendMsg);

		log.debug("postByMail done");
	}

	public void postByMail(String recipients[], String subject,
			DataSource source, String fileName) throws MessagingException,
			IOException {

		log.debug("add attachment");
		BodyPart messageBodyPart = new MimeBodyPart();
		Multipart multipart = new MimeMultipart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(fileName);
		multipart.addBodyPart(messageBodyPart);

		Message sendMsg = initMsg(recipients, subject);
		sendMsg.setContent(multipart);
		sendMsg(sendMsg);

		log.debug("postByMail done");
	}

	public void processMail(String subjectKey, File fileOutputFolder,
			Date readFrom, String pop, String user, String pwd)
			throws MessagingException, IOException {

		log.debug("processMail starting : "
				+ POSConstants.SDF_DATE_AND_TIME.format(readFrom));

		Message message = null;
		Object messagecontentObject = null;
		String sender = null;
		String subject = null;
		Multipart multipart = null;
		Part part = null;
		String contentType = null;
		Date StartReading = new Date();

		Store store = receiveSession.getStore("imap");
		log.info("pop=" + pop + "/user=" + user + "/pwd=" + pwd);
		store.connect(pop, user, pwd);
		Folder folder = store.getDefaultFolder();
		folder = folder.getFolder("inbox");
		folder.open(Folder.READ_WRITE);
		log.debug("read messages");

		// FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
		ReceivedGreaterThenDateTerm rgtdt = new ReceivedGreaterThenDateTerm(
				readFrom);
		Message messages[] = folder.search(rgtdt);

		// Message messages[] = folder.getMessages();

		for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
			message = messages[messageNumber];
			messagecontentObject = message.getContent();
			if (messagecontentObject instanceof Multipart) {
				log.debug("message with attachment");
				sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
				if (sender == null) {
					sender = ((InternetAddress) message.getFrom()[0])
							.getAddress();
				}
				subject = message.getSubject();
				log.debug("subject is : " + subject);
				if (subject != null && subject.startsWith(subjectKey)) {
					multipart = (Multipart) message.getContent();
					for (int i = 0; i < multipart.getCount(); i++) {
						part = multipart.getBodyPart(i);
						contentType = part.getContentType();
						if (contentType.startsWith("text/plain")) {
							log.debug("text/plain");
						} else {
							String fileName = part.getFileName();
							if ("sales.zip".equals(fileName)) {
								log.debug("sales.zip");
								DataHandler dh = part.getDataHandler();
								InputStream is = dh.getInputStream();
								ZipHelper.unZipFiles(is, fileOutputFolder);
								message.setFlag(Flags.Flag.SEEN, true);
							} else {
								log.debug("other");
							}
						}
					}
				}
			} else {
				log.debug("simple message");
				sender = ((InternetAddress) message.getFrom()[0]).getPersonal();
				if (sender == null) {
					sender = ((InternetAddress) message.getFrom()[0])
							.getAddress();
				}
				subject = message.getSubject();
			}
		}
		folder.close(true);
		store.close();
		TimerProp.instance().setLastAccess(TimerProp.LAST_PROCESS_SYNC_EMAIL,
				StartReading);
		log.debug("processMail done : "
				+ POSConstants.SDF_DATE_AND_TIME.format(TimerProp.instance()
						.getLastAccess(TimerProp.LAST_PROCESS_SYNC_EMAIL)));
	}

	private Message initMsg(String[] recipients, String subject)
			throws AddressException, MessagingException {
		InternetAddress[] addressTo = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		Message senMsg = new MimeMessage(sendSession);
		senMsg.setFrom(addressFrom);
		senMsg.setRecipients(Message.RecipientType.TO, addressTo);
		senMsg.setSubject(subject);
		return senMsg;
	}

	private void sendMsg(Message senMsg) throws NoSuchProviderException,
			MessagingException {
		Transport tr = sendSession.getTransport("smtp");
		tr.connect();
		senMsg.saveChanges(); // don't forget this
		tr.sendMessage(senMsg, senMsg.getAllRecipients());
		tr.close();
	}

}

class FileXMLFilterByDate implements FileFilter {

	private Date lastPostMail;

	public FileXMLFilterByDate(Date lastPostMail) {
		this.lastPostMail = lastPostMail;
	}

	@Override
	public boolean accept(File file) {
		return file.isFile() && file.getName().endsWith(".xml")
				&& file.lastModified() > lastPostMail.getTime();
	}

}

class ReceivedGreaterThenDateTerm extends SearchTerm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger
			.getLogger("ReceivedGreaterThenDateTerm");

	private Date date;

	public ReceivedGreaterThenDateTerm(Date date) {
		super();
		this.date = date;
	}

	@Override
	public boolean match(Message arg0) {
		boolean retVal = false;
		try {
			retVal = arg0.getReceivedDate().after(date);
		} catch (MessagingException e) {
			log.error("", e);
		}
		return retVal;
	}
}
