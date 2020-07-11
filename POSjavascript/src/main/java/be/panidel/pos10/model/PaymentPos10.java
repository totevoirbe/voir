package be.panidel.pos10.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import be.panidel.dataLayer.helper.DAOConfig;

@XmlRootElement(name = "documentList")
public class PaymentPos10 {

	private BigDecimal quantity;
	private String description;
	private String mode;
	private BigDecimal value;

	@Override
	public String toString() {
		return "Payment [quantity=" + quantity + ", description=" + description + ", mode=" + mode + ", value=" + value
				+ "]";
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity.divide(DAOConfig.POS_MULTIPLIER);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value.divide(DAOConfig.POS_MULTIPLIER);
	}

}
