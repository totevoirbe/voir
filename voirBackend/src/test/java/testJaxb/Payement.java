package testJaxb;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "quantity", "description", "mode", "value" })

public class Payement {

	private Long quantity;
	private String description;
	private Long mode;
	private BigDecimal value;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getMode() {
		return mode;
	}

	public void setMode(Long mode) {
		this.mode = mode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Payement [quantity=" + quantity + ", mode=" + mode + ", description=" + description + ", value=" + value
				+ "]";
	}

}
