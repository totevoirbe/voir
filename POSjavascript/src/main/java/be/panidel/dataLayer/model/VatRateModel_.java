package be.panidel.dataLayer.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-10T07:18:25.819+0100")
@StaticMetamodel(VatRateModel.class)
public class VatRateModel_ {
	public static volatile SingularAttribute<VatRateModel, Long> id;
	public static volatile SingularAttribute<VatRateModel, String> code;
	public static volatile SingularAttribute<VatRateModel, Date> fromDate;
	public static volatile SingularAttribute<VatRateModel, Date> toDate;
	public static volatile SingularAttribute<VatRateModel, BigDecimal> rate;
}
