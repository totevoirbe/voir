package be.voir.referential.service;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.OrderRepository;
import be.voir.referential.model.Order;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override
	public Iterable<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order get(long id) {
		return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public Order save(Order order) {
		return orderRepository.save(order);
	}

	@Override
	public Order getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code) {
		throw new NotYetImplementedException();
//		for (Order order : getAll()) {
//			if (order.getCode().equalsIgnoreCase(code)) {
//				return product;
//			}
//		}
//		return null;
	}

	@Override
	public Order create(Order order) {
		order.setDateCreated(LocalDate.now());

		return this.orderRepository.save(order);
	}

	@Override
	public void update(Order order) {
		this.orderRepository.save(order);
	}

}
