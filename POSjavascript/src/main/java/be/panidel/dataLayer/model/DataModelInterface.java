package be.panidel.dataLayer.model;

import java.io.Serializable;

public interface DataModelInterface<T> extends Serializable {

	public enum ObjectType {
		PRODUCT_TYPE, SALE_TYPE, PERSON_TYPE
	};

	public static final String POS_PRODUCT_ALL = "pos.product.all";
	public static final String POS_PRODUCT_BYCODE = "pos.product.bycode";
	public static final String POS_PRODUCT_BYID = "pos.product.byid";
	public static final String POS_PRODUCT_DELETE_ALL = "pos.product.deleteAll";
	public static final String POS_PRODUCT_COUNT = "pos.product.count";

	public static final String POS_PAYMENTMETHOD_ALL = "pos.paymentmethod.all";
	public static final String POS_PAYMENTMETHOD_BYID = "pos.paymentmethod.byid";

	public static final String POS_ITEMPRODUCT_ALL = "pos.itemproduct.all";
	public static final String POS_ITEMPAYMENT_ALL = "pos.itempay.all";

	public static final String POS_CASHSALE_COUNT = "pos.sale.count";
	public static final String POS_CASHSALE_ALL = "pos.sale.all";
	public static final String POS_CASHSALE_BYPERIOD = "pos.sale.byperiod";
	public static final String POS_CASHSALE_BYPERIOD_COUNT = "pos.sale.byperiod.count";

	public static final String POS_VAT_ALL = "pos.vat.all";
	public static final String POS_VAT_BYCODE = "pos.vat.code";

}