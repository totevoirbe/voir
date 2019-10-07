package be.voir.referential.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.voir.referential.model.Product;
import be.voir.referential.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<Product> getProducts() {
		Iterable<Product> products = productService.getAll();
		;
		LOG.info("" + products);
		return products;
	}
}
