package br.edu.pucpr.gestaoauto.manager;

import javax.ejb.Local;

@Local
public interface Manager<Id, T> {
	
	void save(T entity);
	
	void update(T entity);

	void delete(T entity);	

	T getById(Id id);
}
