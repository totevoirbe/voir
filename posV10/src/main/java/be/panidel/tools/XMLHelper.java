package be.panidel.tools;

public class XMLHelper {

	public static void addTag(String value, String tagName, StringBuffer xml) {
		if (Tools.isNullOrEmpty(value)) {
			xml.append("<");
			xml.append(tagName);
			xml.append("/>");
		} else {
			xml.append("<");
			xml.append(tagName);
			xml.append(">");
			xml.append(value);
			xml.append("</");
			xml.append(tagName);
			xml.append(">");
		}
	}

	public static void addTag(String value, String[][] attributeList,
			String tagName, StringBuffer xml) {
		xml.append("<");
		xml.append(tagName);

		for (int i = 0; i < attributeList.length; i++) {
			String attributeName = attributeList[i][0];
			String attributeValue = attributeList[i][1];
			xml.append(" ");
			xml.append(attributeName);
			xml.append("=\"");
			xml.append(attributeValue);
			xml.append("\"");
		}

		if (Tools.isNullOrEmpty(value)) {
			xml.append("/>");
		} else {
			xml.append(">");
			xml.append(value);
			xml.append("</");
			xml.append(tagName);
			xml.append(">");
		}
	}

	public static void openTag(String tagName, StringBuffer xml) {
		xml.append("<");
		xml.append(tagName);
		xml.append(">");
	}

	public static void openTag(String[][] attributeList, String tagName,
			StringBuffer xml) {
		xml.append("<");
		xml.append(tagName);

		for (int i = 0; i < attributeList.length; i++) {
			String attributeName = attributeList[i][0];
			String attributeValue = attributeList[i][1];
			xml.append(" ");
			xml.append(attributeName);
			xml.append("=\"");
			xml.append(attributeValue);
			xml.append("\"");
		}

		xml.append(">");
	}

	public static void closeTag(String tagName, StringBuffer xml) {
		xml.append("</");
		xml.append(tagName);
		xml.append(">");
	}
}