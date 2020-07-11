package be.panidel.pos10.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author franzph
 * 
 *         For pos 1.0 co,patibility
 * 
 */

@XmlRootElement(name = "root")
public class RootPaymentMethodsPos10 {

	PaymentMethodsPos10 payments;

	@Override
	public String toString() {
		return "RootPaymentMethodsPos10 [paymentMethodsPos10=" + payments + "]";
	}

	public PaymentMethodsPos10 getPayments() {
		return payments;
	}

	public void setPayments(PaymentMethodsPos10 payments) {
		this.payments = payments;
	}

}
