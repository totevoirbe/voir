package be.panidel.dataLayer.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;

abstract class GenericDaoImpl<T> implements GenericDao<T> {

	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {

		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];

	}

	T persist(final T t, EntityManager em) {

		em.persist(t);

		return t;
	}

	@Override
	public void delete(final Object id, EntityManager em) {

		em.remove(em.getReference(type, id));

	}

	@Override
	public void detach(final T t, EntityManager em) {

		em.detach(t);

	}

	@Override
	public T find(final Object id, EntityManager em) {
		return em.find(type, id);
	}

}