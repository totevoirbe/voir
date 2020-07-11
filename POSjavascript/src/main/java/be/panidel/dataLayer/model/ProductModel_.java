package be.panidel.dataLayer.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-10T07:18:25.816+0100")
@StaticMetamodel(ProductModel.class)
public class ProductModel_ {
	public static volatile SingularAttribute<ProductModel, Long> id;
	public static volatile SingularAttribute<ProductModel, String> label;
	public static volatile SingularAttribute<ProductModel, String> ticketLabel;
	public static volatile SingularAttribute<ProductModel, String> code;
	public static volatile SingularAttribute<ProductModel, String> name;
	public static volatile SingularAttribute<ProductModel, String> htmlKeyLabel;
	public static volatile SingularAttribute<ProductModel, String> type;
	public static volatile SingularAttribute<ProductModel, String> image;
	public static volatile SingularAttribute<ProductModel, VatRateModel> vatRateOnPlace;
	public static volatile SingularAttribute<ProductModel, VatRateModel> vatRateTakeAway;
	public static volatile SingularAttribute<ProductModel, BigDecimal> mini;
	public static volatile SingularAttribute<ProductModel, BigDecimal> normal;
	public static volatile SingularAttribute<ProductModel, BigDecimal> geant;
	public static volatile SingularAttribute<ProductModel, BigDecimal> fitmini;
	public static volatile SingularAttribute<ProductModel, BigDecimal> fitnormal;
	public static volatile SingularAttribute<ProductModel, BigDecimal> fitgeant;
	public static volatile SingularAttribute<ProductModel, String> webDetail;
	public static volatile SingularAttribute<ProductModel, String> afficheDetail;
	public static volatile SingularAttribute<ProductModel, Boolean> canMerge;
}
