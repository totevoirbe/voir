package testJaxb;

import java.math.BigDecimal;

class DayResult {
	BigDecimal caisse;
	BigDecimal carte;

	public BigDecimal getCaisse() {
		return caisse;
	}

	public void setCaisse(BigDecimal caisse) {
		this.caisse = caisse;
	}

	public BigDecimal getCarte() {
		return carte;
	}

	public void setCarte(BigDecimal carte) {
		this.carte = carte;
	}

	@Override
	public String toString() {
		return "DayResult [caisse=" + caisse + ", carte=" + carte + "]";
	}

}