package be.panidel.common.impl;

import org.apache.log4j.Logger;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.tools.Tools;

public abstract class IdentificationImpl implements Identification {

	protected static final Logger log = Logger.getLogger("IdentificationImpl");

	private String id;
	private String code;
	private String name;
	private String description;
	private String htmlKeyLabel;

	public IdentificationImpl() {
		super();
		id = "0";
		code = new String();
		name = new String();
		description = new String();
		htmlKeyLabel = new String();
	}

	public IdentificationImpl(String id, String code, String name,
			String description, String htmlKeyLabel) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public String toString() {

		CoupleMessages cm = new CoupleMessages();

		cm.put("id", id);
		cm.put("code", code);
		cm.put("name", name);
		cm.put("description", description);
		cm.put("htmlKeyLabel", htmlKeyLabel);

		return cm.toString();

	}

	public int compareTo(Identification id1) {
		return Tools.Compare(description, id1.getDescription());
	}

	/* getters */
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	/* setters */
	public void setCode(String code) {
		this.code = code;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}
}