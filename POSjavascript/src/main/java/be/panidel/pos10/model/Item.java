package be.panidel.pos10.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlRootElement;

import be.panidel.dataLayer.helper.DAOConfig;

@XmlRootElement(name = "documentList")
public class Item {

	private BigDecimal quantity;
	private Integer product;
	private String description;
	private BigDecimal unitPrice;
	private BigDecimal tvaTakeAway;
	private BigDecimal tvaTakeOnPlace;

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity.divide(DAOConfig.POS_MULTIPLIER);
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice.divide(DAOConfig.POS_MULTIPLIER);
	}

	public BigDecimal getTvaTakeAway() {
		return tvaTakeAway;
	}

	public void setTvaTakeAway(BigDecimal tvaTakeAway) {
		this.tvaTakeAway = tvaTakeAway.divide(DAOConfig.POS_MULTIPLIER);
	}

	public BigDecimal getTvaTakeOnPlace() {
		return tvaTakeOnPlace;
	}

	public void setTvaTakeOnPlace(BigDecimal tvaTakeOnPlace) {
		this.tvaTakeOnPlace = tvaTakeOnPlace.divide(DAOConfig.POS_MULTIPLIER);
	}

	@Override
	public String toString() {
		return "Item [quantity=" + quantity + ", product=" + product + ", description=" + description + ", unitPrice="
				+ unitPrice + ", tvaTakeAway=" + tvaTakeAway + ", tvaTakeOnPlace=" + tvaTakeOnPlace + "]";
	}

	public Item() {
	}

	public Item(BigDecimal quantity, Integer product, String description, BigDecimal unitPrice, BigDecimal tvaTakeAway,
			BigDecimal tvaTakeOnPlace) {
		super();
		this.quantity = quantity;
		this.product = product;
		this.description = description;
		this.unitPrice = unitPrice;
		this.tvaTakeAway = tvaTakeAway;
		this.tvaTakeOnPlace = tvaTakeOnPlace;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
