package be.panidel.pos10.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "documentList")
public class Document {

	@XmlAttribute
	private String source;
	private String personnel;
	private String date;
	private String company;
	private String computername;
	private Boolean takeonplace;
	@XmlAttribute
	private Boolean cancelled;
	private Collection<Item> item;
	private Collection<PaymentPos10> payement;

	@Override
	public String toString() {
		return "Document [source=" + source + ", personnel=" + personnel + ", date=" + date + ", company=" + company
				+ ", computername=" + computername + ", takeonplace=" + takeonplace + ", cancelled=" + cancelled
				+ ", itemList=" + item + ", paymentList=" + payement + "]";
	}

	public String getSource() {
		return source;
	}

	public String getPersonnel() {
		return personnel;
	}

	public void setPersonnel(String personnel) {
		this.personnel = personnel;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getComputername() {
		return computername;
	}

	public void setComputername(String computername) {
		this.computername = computername;
	}

	public Boolean getTakeonplace() {
		return takeonplace;
	}

	public void setTakeonplace(Boolean takeonplace) {
		this.takeonplace = takeonplace;
	}

	public Boolean getCancelled() {
		return cancelled;
	}

	public Collection<Item> getItem() {
		return item;
	}

	public void setItem(Collection<Item> item) {
		this.item = item;
	}

	public Collection<PaymentPos10> getPayement() {
		return payement;
	}

	public void setPayement(Collection<PaymentPos10> payement) {
		this.payement = payement;
	}

}
