package be.voir.referential.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import be.voir.referential.model.OrderProduct;

@Validated
public interface OrderProductService {

	@NotNull
	Iterable<OrderProduct> getAll();

	OrderProduct get(@Min(value = 1L, message = "ID produit invalide.") long id);

	OrderProduct getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code);

	OrderProduct save(OrderProduct product);

	OrderProduct create(@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct);

}
