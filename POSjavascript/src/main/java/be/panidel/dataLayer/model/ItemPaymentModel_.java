package be.panidel.dataLayer.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-10T07:18:25.807+0100")
@StaticMetamodel(ItemPaymentModel.class)
public class ItemPaymentModel_ {
	public static volatile SingularAttribute<ItemPaymentModel, Long> id;
	public static volatile SingularAttribute<ItemPaymentModel, PaymentMethodModel> paymentMethod;
	public static volatile SingularAttribute<ItemPaymentModel, BigDecimal> quantity;
	public static volatile SingularAttribute<ItemPaymentModel, BigDecimal> unitPrice;
	public static volatile SingularAttribute<ItemPaymentModel, Boolean> deleted;
}
