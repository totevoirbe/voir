package be.voir.referential.service;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.ProductRepository;
import be.voir.referential.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Iterable<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product get(long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public Product save(Product product) {
		Product productSaved = null;
		if (product != null && product.getId() != null) {
			Product productToDelete = get(product.getId());
			if (productToDelete != null) {
				productRepository.delete(productToDelete);
			}
		}
		productSaved = productRepository.save(product);
		return productSaved;
	}

	@Override
	public Product getByCode(@NotNull(message = "CODE code TVA ne peut pas être null.") String code) {
		for (Product product : getAll()) {
			if (product.getCode().equalsIgnoreCase(code)) {
				return product;
			}
		}
		return null;
	}
}
