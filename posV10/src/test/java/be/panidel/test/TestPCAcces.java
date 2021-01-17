package be.panidel.test;

import java.io.File;

public class TestPCAcces {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String[] posList = { "\\\\DAGOSALLE\\CaisseGroup",
				"\\\\DAGORUE\\CaisseGroup", "\\\\FRANZENPH\\CaisseGroup" };

		for (int i = 0; i < posList.length; i++) {
			System.out.println("------------------>" + posList[i]);
			File file = new File(posList[i]);
			String[] fileList = file.list();
			if (fileList != null) {
				for (String string : fileList) {
					System.out.println(string);
				}
			}
		}
	}
}
