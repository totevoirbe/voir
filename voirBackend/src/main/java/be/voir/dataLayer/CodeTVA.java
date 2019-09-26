package be.voir.dataLayer;

import java.math.BigDecimal;

public class CodeTVA {

	private String code;
	private String label;
	private BigDecimal rate;

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

	public String getLabel() {
		return label;
	}

	public BigDecimal getTax() {
		return rate;
	}

}
