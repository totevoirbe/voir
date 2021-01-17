package be.panidel.tools;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Date;

import be.panidel.common.PeriodBean;
import be.panidel.tools.salesFileFilter.SalesFileFilterByPeriodImpl;

public class FileIdentifierList extends ArrayList<FileIdentifier> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileIdentifierList(File source, Date startDay, Date endDay) {
		super();
		getList(source, new SalesFileFilterByPeriodImpl(new PeriodBean(
				startDay, endDay)));
	}

	private void getList(File source, FileFilter fileFilter) {
		File[] fileList = source.listFiles(fileFilter);
		if (fileList != null) {
			for (File file : fileList) {
				add(new FileIdentifier(file));
			}
		}
	}

	public void setAllUnMovable() {
		for (int i = 0; i < size(); i++) {
			FileIdentifier fileIdentifier = get(i);
			fileIdentifier.setUnMovable();
		}
	}
}