package be.panidel.tools;

import java.io.File;

public class TESTConstants {

	public static final File STORAGE_CAISSES_TEST = FileHelper
			.getOrCreateStorage("C:/CaisseTest");
	public static final File STORAGE_CAISSES_ARCHIVES = FileHelper
			.getOrCreateStorage("C:/CaisseTest/archives");
	public static final File STORAGE_CAISSES_GROUP = FileHelper
			.getOrCreateStorage("C:/CaisseTest/group");
	public static final File STORAGE_CAISSES_REJECTED = FileHelper
			.getOrCreateStorage("C:/CaisseTest/rejected");
	public static final String[] NAMES_OF_SALES_TEST = new String[] {
			"20101019201119609.xml", "20101020075507843.xml",
			"20101020075728875.xml", "20101020081520343.xml",
			"20101020081638156.xml", "20091127114852048.xml",
			"20091127115006595.xml", "20091127122623704.xml",
			"20091127123220501.xml", "20101019145717437.xml",
			"20101019200808568.xml" };

}
