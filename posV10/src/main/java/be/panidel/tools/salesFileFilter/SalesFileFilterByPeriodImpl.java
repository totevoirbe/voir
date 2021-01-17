package be.panidel.tools.salesFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import org.apache.log4j.Logger;

import be.panidel.common.PeriodBean;
import be.panidel.pos.exception.ParameterException;
import be.panidel.tools.Tools;

public class SalesFileFilterByPeriodImpl implements FileFilter {

	private static final Logger log = Logger
			.getLogger("SalesFileFilterByPeriodImpl");

	private PeriodBean periodBean;

	public SalesFileFilterByPeriodImpl(PeriodBean periodBean) {
		this.periodBean = periodBean;
	}

	@Override
	public boolean accept(File file) {

		Date dateOfFile = Tools.extractDayFromSalesFileNameAsDate(file
				.getName());

		try {
			return periodBean.isInPeriod(dateOfFile) == 0;
		} catch (ParameterException e) {
			log.error(file.getAbsolutePath() + "/" + dateOfFile, e);
			return false;
		}
	}
}