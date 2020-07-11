package be.panidel.dataLayer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import be.panidel.businessLayer.helper.EnumHelper.PaymentMethodComputation;
import be.panidel.dataLayer.helper.ModelHelper;

@Entity
@Table(name = "paymentmethod")
@NamedQueries({
		@NamedQuery(name = DataModelInterface.POS_PAYMENTMETHOD_ALL, query = "SELECT e FROM PaymentMethodModel e"),
		@NamedQuery(name = DataModelInterface.POS_PAYMENTMETHOD_BYID, query = "SELECT e FROM PaymentMethodModel e where e.id = :id") })
public class PaymentMethodModel implements DataModelInterface<PaymentMethodModel> {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "paymentmethod_id")
	private Long id;
	private String code;
	private String label;
	private String ticketLabel;
	private String htmlKeyLabel;
	private Boolean needSomeValue;
	private BigDecimal maxQuantity;
	private BigDecimal maxTotalAmount;
	private Boolean beAlone;
	private Boolean canMerge;
	private PaymentMethodComputation paymentMethodComputation;

	public PaymentMethodModel() {
	}

	@Override
	public String toString() {
		return "PaymentMethodModel [id=" + id + ", code=" + code + ", label=" + label + ", ticketLabel=" + ticketLabel
				+ ", htmlKeyLabel=" + htmlKeyLabel + ", needSomeValue=" + needSomeValue + ", maxQuantity=" + maxQuantity
				+ ", maxTotalAmount=" + maxTotalAmount + ", beAlone=" + beAlone + ", canMerge=" + canMerge + "]"
				+ ", paymentMethodComputation=" + paymentMethodComputation + "]";
	}

	@Override
	public boolean equals(Object paymentMethodAsObject) {

		if (paymentMethodAsObject instanceof PaymentMethodModel) {

			PaymentMethodModel paymentMathodEval = (PaymentMethodModel) paymentMethodAsObject;

			if (!ModelHelper.checkEquals(this.id, paymentMathodEval.id)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.code, paymentMathodEval.code)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.label, paymentMathodEval.label)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.ticketLabel, paymentMathodEval.ticketLabel)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.htmlKeyLabel, paymentMathodEval.htmlKeyLabel)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.needSomeValue, paymentMathodEval.needSomeValue)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.maxQuantity, paymentMathodEval.maxQuantity)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.maxTotalAmount, paymentMathodEval.maxTotalAmount)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.beAlone, paymentMathodEval.beAlone)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.canMerge, paymentMathodEval.canMerge)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.paymentMethodComputation, paymentMathodEval.paymentMethodComputation)) {
				return false;
			}
		}
		return true;

	}

	public PaymentMethodModel(Long id, String code, String label, String ticketLabel, String htmlKeyLabel,
			Boolean needSomeValue, BigDecimal maxQuantity, BigDecimal maxTotalAmount, Boolean beAlone, Boolean canMerge,
			PaymentMethodComputation paymentMethodComputation) {
		super();
		this.id = id;
		this.code = code;
		this.label = label;
		this.ticketLabel = ticketLabel;
		this.htmlKeyLabel = htmlKeyLabel;
		this.needSomeValue = needSomeValue;
		this.maxQuantity = maxQuantity;
		this.maxTotalAmount = maxTotalAmount;
		this.beAlone = beAlone;
		this.canMerge = canMerge;
		this.paymentMethodComputation = paymentMethodComputation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getLabel() {
		return label;
	}

	public String getTicketLabel() {
		return ticketLabel;
	}

	public String getHtmlKeyLabel() {
		return htmlKeyLabel;
	}

	public Boolean getNeedSomeValue() {
		return needSomeValue;
	}

	public BigDecimal getMaxQuantity() {
		return maxQuantity;
	}

	public BigDecimal getMaxTotalAmount() {
		return maxTotalAmount;
	}

	public Boolean getBeAlone() {
		return beAlone;
	}

	public Boolean getCanMerge() {
		return canMerge;
	}

	public PaymentMethodComputation getPaymentMethodComputation() {
		return paymentMethodComputation;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTicketLabel(String ticketLabel) {
		this.ticketLabel = ticketLabel;
	}

	public void setHtmlKeyLabel(String htmlKeyLabel) {
		this.htmlKeyLabel = htmlKeyLabel;
	}

	public void setNeedSomeValue(Boolean needSomeValue) {
		this.needSomeValue = needSomeValue;
	}

	public void setMaxQuantity(BigDecimal maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	public void setMaxTotalAmount(BigDecimal maxTotalAmount) {
		this.maxTotalAmount = maxTotalAmount;
	}

	public void setBeAlone(Boolean beAlone) {
		this.beAlone = beAlone;
	}

	public void setCanMerge(Boolean canMerge) {
		this.canMerge = canMerge;
	}

	public void setPaymentMethodComputation(PaymentMethodComputation paymentMethodComputation) {
		this.paymentMethodComputation = paymentMethodComputation;
	}

}
