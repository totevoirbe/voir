package be.voir.referential.controller;

import javax.validation.constraints.NotNull;

import org.hibernate.cfg.NotYetImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.voir.referential.model.Product;
import be.voir.referential.service.ProductService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
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

	@PostMapping(value = { "", "/" })
	public Product newProduct(@RequestBody Product newProduct) {
		return productService.save(newProduct);
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		Product product = productService.get(id);
		return product;
	}

	@PutMapping("/{id}")
	public Product replaceEmployee(@RequestBody Product newProduct, @PathVariable Long id) {

//		return repository.findById(id).map(employee -> {
//			employee.setName(newEmployee.getName());
//			employee.setRole(newEmployee.getRole());
//			return repository.save(employee);
//		}).orElseGet(() -> {
//			newEmployee.setId(id);
//			return repository.save(newEmployee);
//		});

		throw new NotYetImplementedException();
	}

	@DeleteMapping("/{id}")
	void deleteEmployee(@PathVariable Long id) {
//		productService.deleteById(id);
		throw new NotYetImplementedException();
	}

}
