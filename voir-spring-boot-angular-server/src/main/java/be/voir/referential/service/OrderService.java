package be.voir.referential.service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import be.voir.referential.model.Order;

@Validated
public interface OrderService {

	@NotNull
	Iterable<Order> getAll();

	Order get(@Min(value = 1L, message = "ID invalide.") long id);

	Order getByCode(@NotNull(message = "CODE ne peut pas être null.") String code);

	Order save(Order product);

	Order create(@NotNull(message = "The order cannot be null.") @Valid Order order);

	void update(@NotNull(message = "The order cannot be null.") @Valid Order order);

}
