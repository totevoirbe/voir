package be.panidel.businessLayer.model;

import java.math.BigDecimal;

import be.panidel.dataLayer.helper.ModelHelper;

public class ItemValuatorModel {

	long count;
	BigDecimal itemTotal = new BigDecimal(0);
	BigDecimal itemsQuantity = new BigDecimal(0);

	@Override
	public boolean equals(Object itemValuatorModelAsObject) {

		if (itemValuatorModelAsObject instanceof ItemValuatorModel) {

			ItemValuatorModel cashSaleValuatorEval = (ItemValuatorModel) itemValuatorModelAsObject;

			if (!ModelHelper.checkEquals(this.itemTotal, cashSaleValuatorEval.itemTotal)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.itemsQuantity, cashSaleValuatorEval.itemsQuantity)) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer();

		description.append("count[" + count + "]; ");
		description.append("itemTotal[" + itemTotal + "]; ");

		return description.toString();
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public BigDecimal getItemTotal() {
		return itemTotal;
	}

	public void setItemTotal(BigDecimal itemTotal) {
		this.itemTotal = itemTotal;
	}

	public BigDecimal getItemsQuantity() {
		return itemsQuantity;
	}

	public void setItemsQuantity(BigDecimal itemsQuantity) {
		this.itemsQuantity = itemsQuantity;
	}

}
