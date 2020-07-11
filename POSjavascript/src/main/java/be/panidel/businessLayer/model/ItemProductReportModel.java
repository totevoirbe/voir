package be.panidel.businessLayer.model;

import java.math.BigDecimal;

import be.panidel.dataLayer.helper.ModelHelper;
import be.panidel.dataLayer.model.ProductModel;

public class ItemProductReportModel {

	private ProductModel product;
	private BigDecimal nbreProduits;
	private BigDecimal total;

	@Override
	public boolean equals(Object ItemAsObject) {

		if (ItemAsObject instanceof ItemProductReportModel) {

			ItemProductReportModel itemEval = (ItemProductReportModel) ItemAsObject;

			if (!ModelHelper.checkEquals(this.product, itemEval.product)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.nbreProduits, itemEval.nbreProduits)) {
				return false;
			}
			if (!ModelHelper.checkEquals(this.total, itemEval.total)) {
				return false;
			}
		}
		return true;

	}

	@Override
	public String toString() {

		StringBuffer description = new StringBuffer();
		description.append("product[" + product + "]; nbreProduits[" + nbreProduits + "]; total[" + total + "]");

		return description.toString();
	}

	public ItemProductReportModel(ProductModel product, BigDecimal nbreProduits, BigDecimal total) {
		super();
		this.product = product;
		this.nbreProduits = nbreProduits;
		this.total = total;
	}

	public ProductModel getProduct() {
		return product;
	}

	public BigDecimal getNbreProduits() {
		return nbreProduits;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setProduct(ProductModel product) {
		this.product = product;
	}

	public void setNbreProduits(BigDecimal nbreProduits) {
		this.nbreProduits = nbreProduits;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}