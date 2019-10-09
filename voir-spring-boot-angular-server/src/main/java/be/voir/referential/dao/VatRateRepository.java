package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.VatRate;

@Repository
public interface VatRateRepository extends CrudRepository<VatRate, Long> {
}
