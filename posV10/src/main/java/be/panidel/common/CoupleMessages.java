package be.panidel.common;

import java.math.BigDecimal;
import java.util.HashMap;

import be.panidel.tools.Tools;
import be.panidel.tools.XMLHelper;

public class CoupleMessages extends HashMap<String, String> {

	String prefixString = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String put(String name, Object value) {
		String stringValue = null;
		name = iterate(name);
		if (value != null) {
			stringValue = value.toString();
		} else {
			stringValue = "null";
		}
		super.put(name, stringValue);
		return stringValue;
	}

	public String put(String name, BigDecimal value) {
		String valueAsString = null;
		name = iterate(name);
		if (value != null) {
			valueAsString = value.toPlainString();
			put(name, value.toPlainString());
		} else {
			put(name, "null");
		}
		return valueAsString;
	}

	public String put(String name, boolean value) {
		String valueAsString = null;
		name = iterate(name);
		if (value) {
			valueAsString = "true";
		} else {
			valueAsString = "false";
		}
		put(name, valueAsString);
		return valueAsString;
	}

	private String iterate(String name) {
		int count = 1;
		String retName = name;
		do {
			retName = name + Integer.toString(count++);
		} while (get(retName) != null);
		return retName;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		boolean firstIteration = true;
		if (!Tools.isNullOrEmpty(prefixString)) {
			sb.append(prefixString);
			firstIteration = false;
		}
		for (String name : keySet()) {
			if (!firstIteration) {
				sb.append("/");
			}
			sb.append(name);
			sb.append("[");
			sb.append(get(name));
			sb.append("]");
		}
		return sb.toString();

	}

	public String toXML() {
		StringBuffer sb = new StringBuffer();
		XMLHelper.openTag("root", sb);
		for (String name : keySet()) {
			XMLHelper.addTag(get(name), name, sb);
		}
		XMLHelper.closeTag("root", sb);
		return sb.toString();
	}

	public void setPrefixString(String prefixString) {
		this.prefixString = prefixString;
	};
}
