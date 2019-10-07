package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.ProductCategoryTag;

@Repository
public interface ProductCategoryTagRepository extends CrudRepository<ProductCategoryTag, Long> {
}
