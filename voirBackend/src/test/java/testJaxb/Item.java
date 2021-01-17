package testJaxb;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "quantity", "product", "description", "unitPrice", "tvaTakeAway", "tvaTakeOnPlace" })

public class Item {

	private Long quantity;
	private Long product;
	private String description;
	private BigDecimal unitPrice;
	private Long tvaTakeAway;
	private Long tvaTakeOnPlace;

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getProduct() {
		return product;
	}

	public void setProduct(Long product) {
		this.product = product;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getTvaTakeAway() {
		return tvaTakeAway;
	}

	public void setTvaTakeAway(Long tvaTakeAway) {
		this.tvaTakeAway = tvaTakeAway;
	}

	public Long getTvaTakeOnPlace() {
		return tvaTakeOnPlace;
	}

	public void setTvaTakeOnPlace(Long tvaTakeOnPlace) {
		this.tvaTakeOnPlace = tvaTakeOnPlace;
	}

	@Override
	public String toString() {
		return "Item [quantity=" + quantity + ", product=" + product + ", description=" + description + ", unitPrice="
				+ unitPrice + ", tvaTakeAway=" + tvaTakeAway + ", tvaTakeOnPlace=" + tvaTakeOnPlace + "]";
	}

}
