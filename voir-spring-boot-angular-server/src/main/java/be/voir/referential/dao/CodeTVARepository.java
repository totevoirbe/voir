package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.CodeTVA;

@Repository
public interface CodeTVARepository extends CrudRepository<CodeTVA, Long> {
}
