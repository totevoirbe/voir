package be.panidel.pos10.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAttribute;

import be.panidel.businessLayer.helper.EnumHelper.PaymentMethodComputation;

public class PaymentMethodPos10 {

	@XmlAttribute
	private Long id;

	private String code;
	private String description;
	private String htmlKeyLabel;
	private Boolean needSomeValue;
	private BigDecimal maxQuantity;
	private BigDecimal maxTotalAmount;
	private Boolean beAlone;
	private PaymentMethodComputation paymentMethodComputation;

	@Override
	public String toString() {
		return "PaymentMethodPos10 [id=" + id + ", code=" + code + ", description=" + description + ", htmlKeyLabel="
				+ htmlKeyLabel + ", needSomeValue=" + needSomeValue + ", maxQuantity=" + maxQuantity
				+ ", maxTotalAmount=" + maxTotalAmount + ", beAlone=" + beAlone + ", paymentMethodComputation="
				+ paymentMethodComputation + "]";
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public Boolean getNeedSomeValue() {
		return needSomeValue;
	}

	public void setNeedSomeValue(Boolean needSomeValue) {
		this.needSomeValue = needSomeValue;
	}

	public BigDecimal getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(BigDecimal maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	public Boolean getBeAlone() {
		return beAlone;
	}

	public void setBeAlone(Boolean beAlone) {
		this.beAlone = beAlone;
	}

	public PaymentMethodComputation getPaymentMethodComputation() {
		return paymentMethodComputation;
	}

	public void setPaymentMethodComputation(PaymentMethodComputation paymentMethodComputation) {
		this.paymentMethodComputation = paymentMethodComputation;
	}

}