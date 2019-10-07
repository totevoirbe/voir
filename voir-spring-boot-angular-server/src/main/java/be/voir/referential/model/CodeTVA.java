package be.voir.referential.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CodeTVA {

	@Id
	private String code;
	private String label;
	private BigDecimal rate;

	public CodeTVA() {
		super();
	}

	public CodeTVA(String code, String label, BigDecimal rate) {
		super();
		this.code = code;
		this.label = label;
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "CodeTVA [code=" + code + ", label=" + label + ", rate=" + rate + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

}