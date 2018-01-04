package br.edu.pucpr.gestaoauto.manager;

import javax.ejb.Local;

@Local
public interface Manager<Id, T> {
	
	T save(T entity);
	
	T update(T entity);

	void delete(Id id);

	T getById(Id id);
}
