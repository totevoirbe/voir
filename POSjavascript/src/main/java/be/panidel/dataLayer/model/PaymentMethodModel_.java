package be.panidel.dataLayer.model;

import be.panidel.businessLayer.helper.EnumHelper.PaymentMethodComputation;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-10T07:18:25.814+0100")
@StaticMetamodel(PaymentMethodModel.class)
public class PaymentMethodModel_ {
	public static volatile SingularAttribute<PaymentMethodModel, Long> id;
	public static volatile SingularAttribute<PaymentMethodModel, String> code;
	public static volatile SingularAttribute<PaymentMethodModel, String> label;
	public static volatile SingularAttribute<PaymentMethodModel, String> ticketLabel;
	public static volatile SingularAttribute<PaymentMethodModel, String> htmlKeyLabel;
	public static volatile SingularAttribute<PaymentMethodModel, Boolean> needSomeValue;
	public static volatile SingularAttribute<PaymentMethodModel, BigDecimal> maxQuantity;
	public static volatile SingularAttribute<PaymentMethodModel, BigDecimal> maxTotalAmount;
	public static volatile SingularAttribute<PaymentMethodModel, Boolean> beAlone;
	public static volatile SingularAttribute<PaymentMethodModel, Boolean> canMerge;
	public static volatile SingularAttribute<PaymentMethodModel, PaymentMethodComputation> paymentMethodComputation;
}
