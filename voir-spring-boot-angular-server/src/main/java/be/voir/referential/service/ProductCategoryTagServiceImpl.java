package be.voir.referential.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import be.voir.exception.ResourceNotFoundException;
import be.voir.referential.dao.ProductCategoryTagRepository;
import be.voir.referential.model.ProductCategoryTag;

@Service
@Transactional
public class ProductCategoryTagServiceImpl implements ProductCategoryTagService {

	private ProductCategoryTagRepository productCategoryTagRepository;

	public ProductCategoryTagServiceImpl(ProductCategoryTagRepository productCategoryTagRepository) {
		this.productCategoryTagRepository = productCategoryTagRepository;
	}

	@Override
	public Iterable<ProductCategoryTag> getAll() {
		return productCategoryTagRepository.findAll();
	}

	@Override
	public ProductCategoryTag get(long id) {
		return productCategoryTagRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product category tag not found"));
	}

	@Override
	public ProductCategoryTag save(ProductCategoryTag productCategoryTag) {
		return productCategoryTagRepository.save(productCategoryTag);
	}

	@Override
	public ProductCategoryTag getByCode(String code) {
		for (ProductCategoryTag productCategoryTag : getAll()) {
			if (productCategoryTag.getCode().equalsIgnoreCase(code)) {
				return productCategoryTag;
			}
		}
		return null;
	}
}
