package be.panidel.dataLayer.model;

import be.panidel.businessLayer.helper.EnumHelper.CashSaleStatus;
import be.panidel.dataLayer.DataFacade.ConsumptionPlace;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-10T07:18:25.628+0100")
@StaticMetamodel(CashSaleModel.class)
public class CashSaleModel_ {
	public static volatile SingularAttribute<CashSaleModel, Long> id;
	public static volatile SingularAttribute<CashSaleModel, CashSaleStatus> cashSaleStatus;
	public static volatile SingularAttribute<CashSaleModel, Date> openDate;
	public static volatile SingularAttribute<CashSaleModel, Date> endDate;
	public static volatile SingularAttribute<CashSaleModel, String> identifier;
	public static volatile SingularAttribute<CashSaleModel, String> source;
	public static volatile SingularAttribute<CashSaleModel, ConsumptionPlace> consumptionPlace;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleTotal;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleExcludVAT;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleDeducedExcludVAT;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleFree;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleLost;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleTrash;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleCancelled;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSalePaymentTotal;
	public static volatile SingularAttribute<CashSaleModel, BigDecimal> cashSaleNbArticles;
	public static volatile CollectionAttribute<CashSaleModel, ItemPaymentModel> itemPayments;
	public static volatile CollectionAttribute<CashSaleModel, ItemProductModel> itemProducts;
}
