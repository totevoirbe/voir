package be.voir.referential.controller;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.voir.referential.model.ProductCategoryTag;
import be.voir.referential.service.ProductCategoryTagService;

@CrossOrigin(origins = { "http://localhost:4200", "http://localhost:4201" })
@RestController
@RequestMapping("/api/product-category-tag")
public class ProductCategoryTagController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductCategoryTagController.class);

	private ProductCategoryTagService productCategoryTagService;

	public ProductCategoryTagController(ProductCategoryTagService productCategoryTagService) {
		this.productCategoryTagService = productCategoryTagService;
	}

	@GetMapping(value = { "", "/" })
	public @NotNull Iterable<ProductCategoryTag> getProductCategoryTags() {
		Iterable<ProductCategoryTag> productCategoryTag = productCategoryTagService.getAll();
		LOG.info("" + productCategoryTag);
		return productCategoryTag;
	}
}
