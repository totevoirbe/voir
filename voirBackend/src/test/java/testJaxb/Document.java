package testJaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "personnel", "date", "company", "computername", "takeonplace", "item", "payement" })

public class Document {

	private Long source;

	private Long personnel;
	private Long date;
	private Long company;
	private String computername;
	private String takeonplace;
	private List<Item> item;
	private List<Payement> payement;

	@XmlAttribute
	public void setSource(Long source) {
		this.source = source;
	}

	public Long getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Long personnel) {
		this.personnel = personnel;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getCompany() {
		return company;
	}

	public void setCompany(Long company) {
		this.company = company;
	}

	public String getComputername() {
		return computername;
	}

	public void setComputername(String computername) {
		this.computername = computername;
	}

	public String getTakeonplace() {
		return takeonplace;
	}

	public void setTakeonplace(String takeonplace) {
		this.takeonplace = takeonplace;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Long getSource() {
		return source;
	}

	public List<Payement> getPayement() {
		return payement;
	}

	public void setPayement(List<Payement> payement) {
		this.payement = payement;
	}

	@Override
	public String toString() {
		return "Document [source=" + source + ", personnel=" + personnel + ", date=" + date + ", company=" + company
				+ ", computername=" + computername + ", takeonplace=" + takeonplace + ", item=" + item + ", payement="
				+ payement + "]";
	}

}