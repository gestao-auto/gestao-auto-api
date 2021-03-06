package br.edu.pucpr.gestaoauto.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SuppressWarnings("unchecked")
public class DAO<Id, T> {

	@PersistenceContext(name="gestaoAuto")
	protected EntityManager entityManager;

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T getById(Id id) {
		return (T) entityManager.find(getTypeClass(), id);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public T save(T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findAll() {
		return entityManager.createQuery(("from " + getTypeClass().getName())).getResultList();
	}

	private Class<?> getTypeClass() {
		Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
		return clazz;
	}
}
