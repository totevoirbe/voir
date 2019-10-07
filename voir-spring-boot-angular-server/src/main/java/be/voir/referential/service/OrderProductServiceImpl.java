package be.voir.referential.service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.OrderProductRepository;
import be.voir.referential.model.OrderProduct;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

	private OrderProductRepository orderProductRepository;

	public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
		this.orderProductRepository = orderProductRepository;
	}

	@Override
	public Iterable<OrderProduct> getAll() {
		return orderProductRepository.findAll();
	}

	@Override
	public OrderProduct get(long id) {
		return orderProductRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public OrderProduct save(OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}

	@Override
	public OrderProduct getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code) {
		throw new NotYetImplementedException();
//		for (OrderProduct orderProduct : getAll()) {
//			if (orderProduct.getCode().equalsIgnoreCase(code)) {
//				return orderProduct;
//			}
//		}
//		return null;
	}

	@Override
	public OrderProduct create(
			@NotNull(message = "The products for order cannot be null.") @Valid OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}
}
