package be.panidel.pos10.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author franzph
 * 
 *         For pos 1.0 co,patibility
 * 
 */

@XmlRootElement(name = "root")
public class RootVatRatesPos10 {

	VatRatesPos10 vatrates;

	public VatRatesPos10 getVatrates() {
		return vatrates;
	}

	public void setVatrates(VatRatesPos10 vatrates) {
		this.vatrates = vatrates;
	}

	@Override
	public String toString() {
		return "RootVatRatesPos10 [vatrates=" + vatrates + "]";
	}

}
