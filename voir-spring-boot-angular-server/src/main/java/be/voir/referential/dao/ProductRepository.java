package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
