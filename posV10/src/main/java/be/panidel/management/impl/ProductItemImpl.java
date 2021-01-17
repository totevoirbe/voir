package be.panidel.management.impl;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

import be.panidel.common.CoupleMessages;
import be.panidel.common.Identification;
import be.panidel.dao.FacadeDAO;
import be.panidel.dao.ItemDAO;
import be.panidel.management.Item;
import be.panidel.management.Product;

public class ProductItemImpl implements Item {

	private static final Logger log = Logger.getLogger("ProductItemImpl");

	private String description;
	private Product product;
	private BigDecimal quantity;
	private BigDecimal unitPrice;
	private BigDecimal tvaTakeAway;
	private BigDecimal tvaTakeOnPlace;

	public ProductItemImpl(Product product) {
		this(product, product.getDescription(), new BigDecimal(1), product
				.getPrix(), product.getTvaTakeAway(), product
				.getTvaTakeOnPlace());
	}

	public ProductItemImpl(Product product, String description,
			BigDecimal quantity, BigDecimal unitPrice, BigDecimal tvaTakeAway,
			BigDecimal tvaTakeOnPlace) {
		super();
		this.description = description;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.tvaTakeAway = tvaTakeAway;
		this.tvaTakeOnPlace = tvaTakeOnPlace;
	}

	@Override
	public String toString() {
		CoupleMessages cm = new CoupleMessages();
		cm.put("description", description);
		cm.put("quantity", quantity);
		cm.put("product", product);
		cm.put("unitPrice", unitPrice);
		cm.put("tvaTakeAway", tvaTakeAway);
		cm.put("tvaTakeOnPlace", tvaTakeOnPlace);
		return cm.toString();
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Identification getItem() {
		return product;
	}

	@Override
	public BigDecimal getQuantity() {
		return quantity;
	}

	@Override
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	@Override
	public BigDecimal getTotalTVAC() {
		BigDecimal totalTVAC = unitPrice.multiply(quantity);
		return totalTVAC;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setItem(Identification identification) {
		this.product = (Product) identification;
	}

	@Override
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@Override
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Override
	public void addQuantity(BigDecimal quantity) {
		this.quantity = this.quantity.add(quantity);
	}

	@Override
	public boolean isSameUnitpriceAndDescription(Item item) {
		ProductItemImpl productItemImpl = (ProductItemImpl) item;
		log.debug("isSameUnitpriceAndDescription [" + productItemImpl + "]["
				+ this + "]");
		return getUnitPrice() == productItemImpl.getUnitPrice()
				&& productItemImpl.getDescription().equals(getDescription());
	}

	@Override
	public ItemDAO getDAO() {
		return FacadeDAO.instance().getProductItemDAO();
	}

	@Override
	public BigDecimal getTvaTakeAway() {
		return tvaTakeAway;
	}

	@Override
	public BigDecimal getTvaTakeOnPlace() {
		return tvaTakeOnPlace;
	}

	@Override
	public void setTvaTakeAway(BigDecimal tvaTakeAway) {
		this.tvaTakeAway = tvaTakeAway;
	}

	@Override
	public void setTvaTakeOnPlace(BigDecimal tvaTakeOnPlace) {
		this.tvaTakeOnPlace = tvaTakeOnPlace;
	}

	@Override
	public Item duplicate() {
		Item productItem = new ProductItemImpl(product, description, quantity,
				unitPrice, tvaTakeAway, tvaTakeOnPlace);
		return productItem;
	}
}