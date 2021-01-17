package be.panidel.management.impl;

import java.math.BigDecimal;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.ItemDAO;
import be.panidel.management.Item;
import be.panidel.management.Payement;

public class PayementItemImpl implements Item {

	private BigDecimal quantity;
	private Payement payement;
	private BigDecimal tvaTakeOnPlace;
	private BigDecimal tvaTakeAway;
	private BigDecimal achatHtva;
	private BigDecimal unitPrice;
	private String description;

	public PayementItemImpl(Payement payement, BigDecimal quantity,
			BigDecimal tvaTakeAway, BigDecimal tvaTakeOnPlace,
			BigDecimal unitPrice, String description) {
		super();
		this.quantity = quantity;
		this.payement = payement;
		this.tvaTakeAway = tvaTakeAway;
		this.tvaTakeOnPlace = tvaTakeOnPlace;
		this.unitPrice = unitPrice;
		this.description = description;
	}

	public String toString() {
		CoupleMessages cm = new CoupleMessages();
		cm.put("mode", payement.getId());
		cm.put("quantity", quantity);
		cm.put("unitPrice", unitPrice);
		return cm.toString();
	}

	public String getDescription() {
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see be.panidel.management.Item#getItem()
	 */
	public Identification getItem() {
		return payement;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public BigDecimal getTotalTVAC() {
		return unitPrice.multiply(quantity);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setItem(Identification identification) {
		this.payement = (Payement) identification;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void addQuantity(BigDecimal quantity) {
		this.quantity = this.quantity.add(quantity);
	}

	public boolean isSameUnitpriceAndDescription(Item item) {
		return getUnitPrice().equals(item.getUnitPrice())
				&& getDescription().equals(item.getDescription());
	}

	@Override
	public ItemDAO getDAO() {
		return FacadeDAO.instance().getPayementItemDAO();
	}

	public BigDecimal getAchatHtva() {
		return achatHtva;
	}

	public void setAchatHtva(BigDecimal achatHtva) {
		this.achatHtva = achatHtva;
	}

	public BigDecimal getTvaTakeOnPlace() {
		return tvaTakeOnPlace;
	}

	public BigDecimal getTvaTakeAway() {
		return tvaTakeAway;
	}

	public void setTvaTakeOnPlace(BigDecimal tvaTakeOnPlace) {
		this.tvaTakeOnPlace = tvaTakeOnPlace;
	}

	public void setTvaTakeAway(BigDecimal tvaTakeAway) {
		this.tvaTakeAway = tvaTakeAway;
	}

	@Override
	public Item duplicate() {
		Item payementItem = new PayementItemImpl(payement, quantity,
				tvaTakeAway, tvaTakeOnPlace, unitPrice, description);
		return payementItem;
	}
}