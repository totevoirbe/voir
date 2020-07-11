package be.panidel.dataLayer.dao;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author tote
 *
 * @param <T>
 */
public interface GenericDao<T> {

	public enum CaisseName {
		ID_CAISSE_TEST
	};

	void delete(Object id, EntityManager em);

	T find(Object id, EntityManager em);

	List<? extends T> findAll(EntityManager em);

	T write(T t, EntityManager em);

	void populate(T persistedEntity, T entity);

	void detach(T entity, EntityManager em);

}