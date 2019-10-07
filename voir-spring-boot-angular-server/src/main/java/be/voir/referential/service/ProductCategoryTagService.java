package be.voir.referential.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import be.voir.referential.model.ProductCategoryTag;

@Validated
public interface ProductCategoryTagService {

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

	@NotNull
	Iterable<ProductCategoryTag> getAll();

	ProductCategoryTag get(@Min(value = 1L, message = "ID product category tag invalide.") long id);

	ProductCategoryTag getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code);

	ProductCategoryTag save(ProductCategoryTag product);

}
