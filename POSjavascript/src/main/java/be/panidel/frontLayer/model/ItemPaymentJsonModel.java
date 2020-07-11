package be.panidel.frontLayer.model;

import java.math.BigDecimal;

public class ItemPaymentJsonModel implements ItemModelJsonInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long paymentMethod;
	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private Boolean deleted;

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer();
		description.append("paymentMethodId[" + paymentMethod + "]; quantity[" + quantity + "]; unitPrice[" + unitPrice
				+ "]; deleted[" + deleted + "]");

		return description.toString();
	}

	public ItemPaymentJsonModel() {
	}

	public ItemPaymentJsonModel(Long paymentMethod, BigDecimal quantity, BigDecimal unitPrice, Boolean deleted) {
		super();
		this.paymentMethod = paymentMethod;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.deleted = deleted;
	}

	public Long getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Long paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

}