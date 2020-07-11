package be.panidel.dataLayer.model;

import be.panidel.businessLayer.helper.EnumHelper.PriceCategory;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-10T07:36:05.255+0100")
@StaticMetamodel(ItemProductModel.class)
public class ItemProductModel_ {
	public static volatile SingularAttribute<ItemProductModel, Long> id;
	public static volatile SingularAttribute<ItemProductModel, ProductModel> product;
	public static volatile SingularAttribute<ItemProductModel, BigDecimal> quantity;
	public static volatile SingularAttribute<ItemProductModel, BigDecimal> unitPrice;
	public static volatile SingularAttribute<ItemProductModel, VatRateModel> vatRate;
	public static volatile SingularAttribute<ItemProductModel, Boolean> deleted;
	public static volatile SingularAttribute<ItemProductModel, PriceCategory> priceCategory;
}
