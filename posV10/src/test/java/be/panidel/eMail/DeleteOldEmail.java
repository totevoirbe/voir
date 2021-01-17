package be.panidel.eMail;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.Flags.Flag;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.SearchTerm;

import org.apache.log4j.Logger;

import be.panidel.common.POSParameters;
import be.panidel.pos.exception.ParameterException;

public class DeleteOldEmail {

	private static final Logger log = Logger.getLogger("DeleteOldEmail");

	private Session sendSession;
	private Session receiveSession;
	private InternetAddress addressFrom;

	Store store;
	Folder folder;

	public static void main(String[] args) {

		DeleteOldEmail mh = null;

		try {

			// String syncSmtpHost = "smtpmail.active24.com";
			String smtp = POSParameters.instance().getSyncSmtpHost();

			// String syncMsgFrom = "philfran@voir.be";
			String from = POSParameters.instance().getSyncMsgFrom();

			GregorianCalendar gc = new GregorianCalendar();
			gc.add(GregorianCalendar.MONTH, -1);

			Date deleteTill = gc.getTime();
			// String syncPopHost = "pop01.mbn1.net";
			// String syncEMailUser = "link@dagoarlon.be";
			// String syncEMailPwd = "7H7sBGjUF8yeGUwtvC20";

			String pop = POSParameters.instance().getSyncPopHost();
			String user = POSParameters.instance().getSyncEMailUser();
			String pwd = POSParameters.instance().getSyncEMailPwd();

			mh = new DeleteOldEmail(from, smtp, pop, user, pwd);
			mh.deleteBySearch(deleteTill);

		} catch (MessagingException e) {
			log.error("", e);
		} catch (ParameterException e) {
			log.error("", e);
		} finally {
			if (mh != null) {
				try {
					mh.close();
				} catch (MessagingException e) {
					log.error("", e);
				}
			}
		}

	}

	public DeleteOldEmail(String from, String smtp, String pop, String user,
			String pwd) throws MessagingException, ParameterException {

		Properties props = new Properties();
		props.put("mail.smtp.host", smtp);
		sendSession = Session.getInstance(props);
		sendSession.setDebug(log.isDebugEnabled());
		log.debug("init message");
		addressFrom = new InternetAddress(from);
		log.debug("from : " + addressFrom.getAddress());

		log.debug("open provider connection");
		receiveSession = Session.getInstance(System.getProperties());
		receiveSession.setDebug(log.isDebugEnabled());

		init(pop, user, pwd);

	}

	private void init(String pop, String user, String pwd)
			throws MessagingException {

		store = receiveSession.getStore("imap");
		log.info("pop=" + pop + "/user=" + user + "/pwd=" + pwd);
		store.connect(pop, user, pwd);
		folder = store.getDefaultFolder();
		folder = folder.getFolder("inbox");
		folder.open(Folder.READ_ONLY);
		log.debug("read messages");

	}

	public void close() throws MessagingException {

		folder.close(true);
		store.close();

	}

	public void deleteBySearch(Date deleteTill) throws MessagingException {

		Message message = null;
		Date firstEMailDate = null;
		Date lastEMailDate = null;

		ReceivedLowerThenDate rgtdt = new ReceivedLowerThenDate(deleteTill);
		Message messages[] = folder.search(rgtdt);

		int countDeleted = 0;

		for (int messageNumber = 0; messageNumber < messages.length; messageNumber++) {
			message = messages[messageNumber];
			Date receivedDate = message.getReceivedDate();

			if (firstEMailDate == null || firstEMailDate.after(receivedDate)) {
				firstEMailDate = receivedDate;
			}
			if (lastEMailDate == null || lastEMailDate.before(receivedDate)) {
				lastEMailDate = receivedDate;
			}

			message.setFlag(Flag.DELETED, true);
			countDeleted++;
			log.debug("TO BE DELETED:" + messageNumber + ":" + receivedDate
					+ "[" + firstEMailDate + "," + lastEMailDate + "]"
					+ countDeleted + "/" + messages.length);
		}

		log.info("TOTAL[" + firstEMailDate + "," + lastEMailDate + "]"
				+ countDeleted + "/" + messages.length);
	}

}

class ReceivedLowerThenDate extends SearchTerm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("ReceivedLowerThenDate");

	private Date date;

	public ReceivedLowerThenDate(Date date) {
		super();
		this.date = date;
	}

	@Override
	public boolean match(Message arg0) {
		boolean retVal = false;
		try {

			retVal = arg0.getReceivedDate().before(date)
					&& arg0.isSet(Flag.SEEN);

		} catch (MessagingException e) {
			log.error("", e);
		}
		return retVal;
	}
}