package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.OrderProduct;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Long> {
}
