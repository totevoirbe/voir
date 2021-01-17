package be.panidel.management;

import java.math.BigDecimal;

import be.panidel.common.CoupleMessages;

public class PeriodValue {

	private String period;
	private int salesQuantity;
	private BigDecimal salesByPeriod;

	public PeriodValue(String period) {
		super();
		this.period = period;
		salesQuantity = 0;
		salesByPeriod = BigDecimal.ZERO;
	}

	@Override
	public String toString() {
		CoupleMessages cm = new CoupleMessages();

		cm.put("period", period);
		cm.put("salesQuantity", Integer.toString(salesQuantity));
		cm.put("salesByPeriod", salesByPeriod.toPlainString());

		return cm.toString();
	}

	public int getSalesQuantity() {
		return salesQuantity;
	}

	public BigDecimal getSalesByPeriod() {
		return salesByPeriod;
	}

	public void incrementSalesQuantity() {
		this.salesQuantity++;
	}

	public void addSale(BigDecimal salesByPeriod) {
		this.salesByPeriod = this.salesByPeriod.add(salesByPeriod);
	}

	public void addPeriodValue(PeriodValue periodValue) {
		salesByPeriod = salesByPeriod.add(periodValue.getSalesByPeriod());
		this.salesQuantity += periodValue.getSalesQuantity();
	}

	public String getPeriod() {
		return period;
	}
}
