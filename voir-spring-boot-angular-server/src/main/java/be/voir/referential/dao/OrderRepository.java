package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
}
