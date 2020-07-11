package be.panidel.dataLayer.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cashSaleLits")
public class CashSaleModelXmlList {

	private Collection<CashSaleModel> cashSales;

	@Override
	public String toString() {
		return "DocumentList [cashSales=" + cashSales + "]";
	}

	public Collection<CashSaleModel> getDocument() {
		return cashSales;
	}

	public void setDocument(Collection<CashSaleModel> cashSales) {
		this.cashSales = cashSales;
	}

}
