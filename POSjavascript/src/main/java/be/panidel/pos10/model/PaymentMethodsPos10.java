package be.panidel.pos10.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "payments")
public class PaymentMethodsPos10 {

	private List<PaymentMethodPos10> payment;

	@Override
	public String toString() {
		return "PaymentMethodsPos10 [productPos10s=" + payment + "]";
	}

	public List<PaymentMethodPos10> getPayment() {
		return payment;
	}

	public void setPayment(List<PaymentMethodPos10> payment) {
		this.payment = payment;
	}

}
