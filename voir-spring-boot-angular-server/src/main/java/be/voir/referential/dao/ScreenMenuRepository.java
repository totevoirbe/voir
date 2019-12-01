package be.voir.referential.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.voir.referential.model.ScreenMenu;

@Repository
public interface ScreenMenuRepository extends CrudRepository<ScreenMenu, Long> {
}
