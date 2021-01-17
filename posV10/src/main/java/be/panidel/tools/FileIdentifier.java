package be.panidel.tools;

import java.io.File;
import java.util.Date;

public class FileIdentifier {

	private File file;
	private Date dateOfFileAsDate;
	private String dateOfFileAsString;
	private boolean rejected = false;
	private boolean unMovable = false;

	public FileIdentifier(File file) {
		super();
		this.file = file;
		dateOfFileAsDate = Tools.extractDayFromSalesFileNameAsDate(file
				.getName());
		dateOfFileAsString = Tools.extractDayFromSalesFileNameAsString(file
				.getName());
	}

	public String getFileName() {
		return file.getName();
	}

	public Date getDateOfFileAsDate() {
		return dateOfFileAsDate;
	}

	public String getDateOfFileAsString() {
		return dateOfFileAsString;
	}

	public void setRejected() {
		rejected = true;
	}

	public boolean isRejected() {
		return rejected;
	}

	public File getFile() {
		return file;
	}

	public boolean isUnMovable() {
		return unMovable;
	}

	public void setUnMovable() {
		this.unMovable = true;
	}

}