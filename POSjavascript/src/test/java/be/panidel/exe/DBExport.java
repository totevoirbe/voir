package be.panidel.exe;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.panidel.dataLayer.DataFacade;
import be.panidel.dataLayer.exception.DataLayerException;
import be.panidel.dataLayer.helper.DataLayerHelper;

public class DBExport {

	private final static Logger LOG = LoggerFactory.getLogger(DBExport.class);

	public static void main(String[] args) {

		Date startDate = new GregorianCalendar(2018, 1, 0).getTime();
		Date endDate = new GregorianCalendar(2018, 2, 0).getTime();

		try {

			LOG.info("DBExport - START");

			DataFacade.instance.exportCashSaleAsXml(startDate, endDate);

			LOG.info("DBExport - END");

		} catch (DataLayerException | JAXBException e) {
			LOG.error("", e);
		} finally {
			DataLayerHelper.closeEmf();
			LOG.info("DBExport - FINALLY END");
		}

	}

}
