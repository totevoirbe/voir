package be.voir.dataLayer;

import java.util.HashMap;
import java.util.Map;

public class ProductCategoryTagDAO {

	Map<String, ProductCategoryTag> productCategoryTags;

	public enum ProductCategoryTagEnum {

		ASSIETTE("assiette"), AUTRE("autre"), BAGUETTE("baguette"), BOISSON("boisson"), CHEVRE("chèvre"),
		CLASSIQUE("classiques"), DESSERT("dessert"), MER("mer"), PANINI("panini"), POULET("poulet"), SALADE("salade"),
		TARTINER("tartiner"), SALAISON("salaison"), SANDWICH("sandwich"), SUD("sud"), SUGGESTION("suggestion"),
		VIENNOISERIE("viennoiserie");

		private final String label;

		private ProductCategoryTagEnum(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	protected void mock() {

		productCategoryTags = new HashMap<String, ProductCategoryTag>();

		for (ProductCategoryTagEnum productCategoryTagEnum : ProductCategoryTagEnum.values()) {

			String code = productCategoryTagEnum.name().trim().toUpperCase();
			String label = productCategoryTagEnum.getLabel();

			ProductCategoryTag productCategoryTag = new ProductCategoryTag(code, label);
			productCategoryTags.put(code, productCategoryTag);

		}

	}

	public ProductCategoryTagDAO() {
		mock();
	}

	public ProductCategoryTag get(String code) {
		ProductCategoryTag productCategoryTag = null;
		if (code != null && code.trim().length() > 0) {
			code = code.trim().toUpperCase();
			productCategoryTag = productCategoryTags.get(code);
		}
		return productCategoryTag;
	}

}
