package be.panidel.pos10.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import be.panidel.dataLayer.model.VatRateModel;

@XmlRootElement(name = "vatrates")
public class VatRatesPos10 {

	private List<VatRateModel> vatrate;

	public List<VatRateModel> getVatrate() {
		return vatrate;
	}

	public void setVatrate(List<VatRateModel> vatrate) {
		this.vatrate = vatrate;
	}

	@Override
	public String toString() {
		return "VatRatesPos10 [vatrate=" + vatrate + "]";
	}

}
