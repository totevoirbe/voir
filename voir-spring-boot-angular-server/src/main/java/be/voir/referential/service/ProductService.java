package be.voir.referential.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import be.voir.referential.model.Product;

@Validated
public interface ProductService {

	@NotNull
	Iterable<Product> getAll();

	Product get(@Min(value = 1L, message = "ID produit invalide.") long id);

	Product getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code);

	Product save(Product product);

}
