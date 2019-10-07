package be.voir.referential.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dto.OrderProductDto;
import be.voir.referential.model.Order;
import be.voir.referential.model.OrderProduct;
import be.voir.referential.model.OrderStatus;
import be.voir.referential.service.OrderProductService;
import be.voir.referential.service.OrderService;
import be.voir.referential.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	ProductService productService;
	OrderService orderService;
	OrderProductService orderProductService;

	public OrderController(ProductService productService, OrderService orderService,
			OrderProductService orderProductService) {
		this.productService = productService;
		this.orderService = orderService;
		this.orderProductService = orderProductService;
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public @NotNull Iterable<Order> list() {
		return this.orderService.getAll();
	}

	@PostMapping
	public ResponseEntity<Order> create(@RequestBody OrderForm form) {
		List<OrderProductDto> formDtos = form.getProductOrders();
		validateProductsExistence(formDtos);
		Order order = new Order();
		order.setStatus(OrderStatus.PAID.name());
		order = this.orderService.create(order);

		List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
		for (OrderProductDto dto : formDtos) {
			orderProducts.add(orderProductService
					.create(new OrderProduct(order, productService.get(dto.getProduct().getId()), dto.getQuantity())));
		}

		order.setOrderProducts(orderProducts);

		this.orderService.update(order);

		String uri = ServletUriComponentsBuilder.fromCurrentServletMapping().path("/orders/{id}")
				.buildAndExpand(order.getId()).toString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", uri);

		return new ResponseEntity<Order>(order, headers, HttpStatus.CREATED);
	}

	private void validateProductsExistence(List<OrderProductDto> orderProducts) {
		List<OrderProductDto> list = orderProducts.stream()
				.filter(op -> Objects.isNull(productService.get(op.getProduct().getId()))).collect(Collectors.toList());

		if (!CollectionUtils.isEmpty(list)) {
			new ResourceNotFoundException("Product not found");
		}
	}

	public static class OrderForm {

		private List<OrderProductDto> productOrders;

		public List<OrderProductDto> getProductOrders() {
			return productOrders;
		}

		public void setProductOrders(List<OrderProductDto> productOrders) {
			this.productOrders = productOrders;
		}
	}
}
