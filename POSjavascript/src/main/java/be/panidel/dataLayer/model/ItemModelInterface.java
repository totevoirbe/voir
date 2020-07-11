package be.panidel.dataLayer.model;

import java.math.BigDecimal;

public interface ItemModelInterface<T> extends DataModelInterface<T> {

	BigDecimal getQuantity();

	BigDecimal getUnitPrice();

}
