package be.panidel.tools.salesFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.util.Date;

import be.panidel.tools.Tools;

public class SalesFileFilterImpl implements FileFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * be.panidel.tools.salesFileFilter.SalesFileFilterI#accept(java.io.File)
	 */
	@Override
	public boolean accept(File file) {

		Date dateOfFile = Tools.extractDayFromSalesFileNameAsDate(file
				.getName());

		return dateOfFile != null;
	}
}
