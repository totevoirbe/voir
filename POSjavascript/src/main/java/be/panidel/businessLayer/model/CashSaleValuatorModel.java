package be.panidel.businessLayer.model;

import java.math.BigDecimal;

import be.panidel.dataLayer.helper.ModelHelper;

public class CashSaleValuatorModel {

	long count;
	BigDecimal cashSaleTotal = new BigDecimal(0);
	BigDecimal paymentTotal = new BigDecimal(0);
	BigDecimal nbArticles = new BigDecimal(0);
	BigDecimal remainValue = new BigDecimal(0);

	@Override
	public boolean equals(Object cashSaleValuatorModelAsObject) {

		if (cashSaleValuatorModelAsObject instanceof CashSaleValuatorModel) {

			CashSaleValuatorModel cashSaleValuatorEval = (CashSaleValuatorModel) cashSaleValuatorModelAsObject;

			if (!ModelHelper.checkEquals(this.cashSaleTotal, cashSaleValuatorEval.cashSaleTotal)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.paymentTotal, cashSaleValuatorEval.paymentTotal)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.nbArticles, cashSaleValuatorEval.nbArticles)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.remainValue, cashSaleValuatorEval.remainValue)) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer();

		description.append("count[" + count + "]; ");
		description.append("cashSaleTotal[" + cashSaleTotal + "]; ");
		description.append("paymentTotal[" + paymentTotal + "]; ");
		description.append("nbArticles[" + nbArticles + "]; ");
		description.append("remainValue[" + remainValue + "]; ");

		return description.toString();
	}

	public void add(BigDecimal cashSaleTotal, BigDecimal paymentTotal, BigDecimal nbArticles, BigDecimal remainValue) {
		count++;
		this.cashSaleTotal = this.cashSaleTotal.add(cashSaleTotal);
		this.paymentTotal = this.paymentTotal.add(paymentTotal);
		this.nbArticles = this.nbArticles.add(nbArticles);
		this.remainValue = this.remainValue.add(remainValue);
	}

	public void reset() {
		count++;
		cashSaleTotal = BigDecimal.ZERO;
		paymentTotal = BigDecimal.ZERO;
		nbArticles = BigDecimal.ZERO;
		remainValue = BigDecimal.ZERO;
	}

	public CashSaleValuatorModel() {
	}

	public CashSaleValuatorModel(BigDecimal cashSaleTotal, BigDecimal paymentTotal, BigDecimal nbArticles,
			BigDecimal remainValue) {
		super();
		this.cashSaleTotal = cashSaleTotal;
		this.paymentTotal = paymentTotal;
		this.nbArticles = nbArticles;
		this.remainValue = remainValue;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getCashSaleTotal() {
		return cashSaleTotal;
	}

	public void setCashSaleTotal(BigDecimal cashSaleTotal) {
		this.cashSaleTotal = cashSaleTotal;
	}

	public BigDecimal getPaymentTotal() {
		return paymentTotal;
	}

	public void setPaymentTotal(BigDecimal paymentTotal) {
		this.paymentTotal = paymentTotal;
	}

	public BigDecimal getNbArticles() {
		return nbArticles;
	}

	public void setNbArticles(BigDecimal nbArticles) {
		this.nbArticles = nbArticles;
	}

	public BigDecimal getRemainValue() {
		return remainValue;
	}

	public void setRemainValue(BigDecimal remainValue) {
		this.remainValue = remainValue;
	}

}
