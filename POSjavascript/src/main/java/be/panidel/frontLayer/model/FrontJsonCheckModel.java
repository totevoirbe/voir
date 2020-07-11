package be.panidel.frontLayer.model;

import java.util.Collection;

import be.panidel.businessLayer.model.CashSaleValuatorModel;

public class FrontJsonCheckModel implements JsonModelInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Collection<CashSaleJsonModel> cashSaleJsons;
	private CashSaleValuatorModel cashSaleValuatorOPEN;
	private CashSaleValuatorModel cashSaleValuatorCANCEL;
	private CashSaleValuatorModel cashSaleValuatorDONE;

	public FrontJsonCheckModel() {
	}

	@Override
	public String toString() {
		return "FrontJsonCheckModel [cashSaleJsons=" + cashSaleJsons + ", cashSaleValuatorOPEN=" + cashSaleValuatorOPEN
				+ ", cashSaleValuatorCANCEL=" + cashSaleValuatorCANCEL + ", cashSaleValuatorDONE="
				+ cashSaleValuatorDONE + "]";
	}

	public Collection<CashSaleJsonModel> getCashSaleJsons() {
		return cashSaleJsons;
	}

	public void setCashSaleJsons(Collection<CashSaleJsonModel> cashSaleJsons) {
		this.cashSaleJsons = cashSaleJsons;
	}

	public CashSaleValuatorModel getCashSaleValuatorOPEN() {
		return cashSaleValuatorOPEN;
	}

	public void setCashSaleValuatorOPEN(CashSaleValuatorModel cashSaleValuatorOPEN) {
		this.cashSaleValuatorOPEN = cashSaleValuatorOPEN;
	}

	public CashSaleValuatorModel getCashSaleValuatorCANCEL() {
		return cashSaleValuatorCANCEL;
	}

	public void setCashSaleValuatorCANCEL(CashSaleValuatorModel cashSaleValuatorCANCEL) {
		this.cashSaleValuatorCANCEL = cashSaleValuatorCANCEL;
	}

	public CashSaleValuatorModel getCashSaleValuatorDONE() {
		return cashSaleValuatorDONE;
	}

	public void setCashSaleValuatorDONE(CashSaleValuatorModel cashSaleValuatorDONE) {
		this.cashSaleValuatorDONE = cashSaleValuatorDONE;
	}

}