package be.panidel.pos10.tool;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CashSaleFileFilter implements FileFilter {

	private final static Logger LOG = LoggerFactory.getLogger(CashSaleFileFilter.class);

	private Date fromDay;
	private Date toDay;

	public CashSaleFileFilter(Date fromDay, Date toDay) {
		super();
		this.fromDay = fromDay;
		this.toDay = toDay;
	}

	@Override
	public boolean accept(File file) {

		if (file.getName().length() < 9) {
			LOG.debug("File name to short : " + file.getName());
			return false;
		}

		if (!file.getName().endsWith(".xml")) {
			LOG.debug("File is not XML file : " + file.getName());
			return false;
		}

		Date fileDay = ProcessAsCashSalesPos10.fromTextYYYYMMDDToDate(file.getName()).getTime();

		if (fileDay == null) {
			return false;
		}

		if (fileDay.before(fromDay) || fileDay.after(toDay)) {
			LOG.debug("File is out of range date : " + file.getName());
			return false;
		}

		LOG.debug("File is accepted : " + file.getName());
		return true;

	}

}
