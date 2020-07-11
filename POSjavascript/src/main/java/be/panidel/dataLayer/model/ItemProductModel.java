package be.panidel.dataLayer.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;
import be.panidel.dataLayer.helper.ModelHelper;

@Entity
@Table(name = "itemproduct")
@NamedQueries({
		@NamedQuery(name = DataModelInterface.POS_ITEMPRODUCT_ALL, query = "SELECT e FROM ItemProductModel e") })
public class ItemProductModel implements ItemModelInterface<ItemProductModel> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemProduct_generator")
	@SequenceGenerator(name = "itemProduct_generator", sequenceName = "itemProduct_seq", allocationSize = 50)
	@Column(name = "itemproduct_id")
	private Long id;

	@ManyToOne
	private ProductModel product;
	private BigDecimal quantity;
	private BigDecimal unitPrice;

	@ManyToOne
	private VatRateModel vatRate;
	private Boolean deleted;
	private PriceCategory priceCategory;

	@Override
	public boolean equals(Object ItemAsObject) {

		if (ItemAsObject instanceof ItemProductModel) {

			ItemProductModel itemEval = (ItemProductModel) ItemAsObject;

			// if (!ModelUtil.checkEquals(this.id, itemEval.id)) {
			// return false;
			// }
			if (!ModelHelper.checkEquals(this.product, itemEval.product)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.quantity, itemEval.quantity)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.unitPrice, itemEval.unitPrice)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.vatRate, itemEval.vatRate)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.deleted, itemEval.deleted)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.priceCategory, itemEval.priceCategory)) {
				return false;
			}

		}
		return true;

	}

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer("id[" + id + "];");
		description.append("product[" + product + "]; quantity[" + quantity + "]; unitPrice[" + unitPrice
				+ "]; vatRate[" + vatRate + "]; deleted[" + deleted + "]; priceCategory[" + priceCategory + "]");

		return description.toString();
	}

	public ItemProductModel() {
	}

	public ItemProductModel(ProductModel product, BigDecimal quantity, BigDecimal unitPrice, VatRateModel vatRate,
			Boolean deleted, PriceCategory priceCategory) {
		super();
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.vatRate = vatRate;
		this.deleted = deleted;
		this.priceCategory = priceCategory;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductModel getProduct() {
		return product;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
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

	public VatRateModel getVatRate() {
		return vatRate;
	}

	public void setVatRate(VatRateModel vatRate) {
		this.vatRate = vatRate;
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