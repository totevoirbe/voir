package be.panidel.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.apache.log4j.Logger;

import be.panidel.common.POSConstants;

public class TimerProp extends Properties {

	public static final String LAST_SYNC_BY_EMAIL = "lastSyncByEmail";
	public static final String LAST_PROCESS_SYNC_EMAIL = "lastProcessSyncEmail";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger("TimerProp");

	private File file;

	private static TimerProp instance;
	private static Object lock = new Object();

	public static TimerProp instance() {
		if (instance == null) {
			synchronized (lock) {
				instance = new TimerProp();
			}
		}
		return instance;
	}

	private TimerProp() {

		String userDir = System.getProperty("user.dir");
		log.debug("Parameters directory:" + userDir);
		File dirParam = FileHelper.getOrCreateStorage(userDir);
		file = new File(dirParam, "sync.xml");
		if (file.isFile()) {
			InputStream is = null;
			try {
				is = new FileInputStream(file);
				loadFromXML(is);
			} catch (FileNotFoundException e) {
				log.error(file, e);
			} catch (InvalidPropertiesFormatException e) {
				log.error(file, e);
			} catch (IOException e) {
				log.error(file, e);
			} finally {
				try {
					is.close();
				} catch (IOException e) {
					log.error(file, e);
				}
			}
		}

	}

	private void save() {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			storeToXML(os, null);
		} catch (FileNotFoundException e) {
			log.error(file, e);
		} catch (IOException e) {
			log.error(file, e);
		} finally {
			try {
				os.flush();
				os.close();
			} catch (IOException e) {
				log.error(file, e);
			}
		}

	}

	public Date getLastAccess(String propertyName) {
		String propertyValue = getProperty(propertyName);
		Date date = new Date(0);
		if (propertyValue == null) {
			setLastAccess(propertyName, date);
		} else {
			try {
				date = POSConstants.SDF_FOR_FILE_SALES.parse(propertyValue);
			} catch (ParseException e) {
				log.error("", e);
			}
		}
		return date;
	}

	public void setLastAccess(String propertyName, Date date) {
		setProperty(propertyName, POSConstants.SDF_FOR_FILE_SALES.format(date));
		save();
	}

}