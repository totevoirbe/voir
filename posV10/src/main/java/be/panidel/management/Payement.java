package be.panidel.management;

import java.math.BigDecimal;

import be.panidel.common.Identification;

public interface Payement extends Identification {

	boolean isNeedSomeValue();

	void setNeedSomeValue(boolean needSomeValue);

	BigDecimal getMaxTotalAmount();

	BigDecimal getMaxQuantity();

	void setMaxTotalAmount(BigDecimal maxTotalAmount);

	void setMaxQuantity(BigDecimal maxQuantity);

	String getHtmlKeyLabel();

	boolean isBeAlone();

	void setBeAlone(boolean beAlone);
}
