package be.panidel.frontLayer.model;

import java.math.BigDecimal;

import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;

public class ItemProductJsonModel implements ItemModelJsonInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long productId;
	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private Boolean deleted;
	private PriceCategory priceCategory;

	@Override
	public String toString() {
		return "ItemProductJsonModel [productId=" + productId + ", quantity=" + quantity + ", unitPrice=" + unitPrice
				+ ", deleted=" + deleted + ", priceCategory=" + priceCategory + "]";
	}

	public ItemProductJsonModel() {
	}

	public ItemProductJsonModel(Long productId, BigDecimal quantity, BigDecimal unitPrice, Boolean deleted,
			PriceCategory priceCategory) {
		super();
		this.productId = productId;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.deleted = deleted;
		this.priceCategory = priceCategory;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Override
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public PriceCategory getPriceCategory() {
		return priceCategory;
	}

	public void setPriceCategory(PriceCategory priceCategory) {
		this.priceCategory = priceCategory;
	}

}