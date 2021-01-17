package be.panidel.management;

import java.math.BigDecimal;

import be.panidel.common.Identification;
import be.panidel.dao.ItemDAO;

public interface Item {

	String getDescription();

	Identification getItem();

	BigDecimal getQuantity();

	BigDecimal getUnitPrice();

	BigDecimal getTotalTVAC();

	void setDescription(String description);

	void setItem(Identification item);

	void setQuantity(BigDecimal quantity);

	void setUnitPrice(BigDecimal unitPrice);

	void addQuantity(BigDecimal quantity);

	boolean isSameUnitpriceAndDescription(Item item);

	ItemDAO getDAO();

	public BigDecimal getTvaTakeAway();

	public BigDecimal getTvaTakeOnPlace();

	public void setTvaTakeAway(BigDecimal tvaTakeAway);

	public void setTvaTakeOnPlace(BigDecimal tvaTakeOnPlace);

	public Item duplicate();
}