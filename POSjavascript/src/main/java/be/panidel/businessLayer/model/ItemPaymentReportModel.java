package be.panidel.businessLayer.model;

import java.math.BigDecimal;

import be.panidel.dataLayer.helper.ModelHelper;
import be.panidel.dataLayer.model.PaymentMethodModel;

public class ItemPaymentReportModel {

	private PaymentMethodModel paymentMethod;
	private BigDecimal operation;
	private BigDecimal total;

	@Override
	public boolean equals(Object ItemAsObject) {

		if (ItemAsObject instanceof ItemPaymentReportModel) {

			ItemPaymentReportModel itemEval = (ItemPaymentReportModel) ItemAsObject;

			if (!ModelHelper.checkEquals(this.paymentMethod, itemEval.paymentMethod)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.operation, itemEval.operation)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.total, itemEval.total)) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer();
		description.append("paymentMethod[" + paymentMethod + "]; operation[" + operation + "]; total[" + total + "]");

		return description.toString();
	}

	public PaymentMethodModel getPaymentMethod() {
		return paymentMethod;
	}

	public ItemPaymentReportModel(PaymentMethodModel paymentMethod, BigDecimal operation, BigDecimal total) {
		super();
		this.paymentMethod = paymentMethod;
		this.operation = operation;
		this.total = total;
	}

	public BigDecimal getOperation() {
		return operation;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setPaymentMethod(PaymentMethodModel paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setOperation(BigDecimal operation) {
		this.operation = operation;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}