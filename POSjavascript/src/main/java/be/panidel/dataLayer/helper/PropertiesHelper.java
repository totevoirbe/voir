package be.panidel.dataLayer.helper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesHelper {

	private final static Logger LOG = LoggerFactory.getLogger(PropertiesHelper.class);

	public static Properties loadProperties(String propertiesFileName) {

		FileInputStream fileInputStream = null;
		Properties properties = new Properties();

		try {
			fileInputStream = new FileInputStream(propertiesFileName);
			properties.load(fileInputStream);
		} catch (IOException e) {
			LOG.error("Properties file name : " + propertiesFileName, e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOG.error("Properties file name : " + propertiesFileName, e);
				}
			}
		}

		return properties;

	}

	public static Properties writeProperties(String propertiesFileName) {

		FileOutputStream fileOutputStream = null;
		Properties properties = new Properties();

		try {
			fileOutputStream = new FileOutputStream(propertiesFileName);
			properties.store(fileOutputStream, "---No Comment---");
		} catch (IOException e) {
			LOG.error("Properties file name : " + propertiesFileName, e);
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					LOG.error("Properties file name : " + propertiesFileName, e);
				}
			}
		}

		return properties;

	}

}
