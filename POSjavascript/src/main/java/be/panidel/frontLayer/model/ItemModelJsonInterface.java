package be.panidel.frontLayer.model;

import java.math.BigDecimal;

public interface ItemModelJsonInterface extends JsonModelInterface {

	BigDecimal getQuantity();

	BigDecimal getUnitPrice();

}
