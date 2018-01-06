package br.edu.pucpr.gestaoauto.manager;

import javax.ejb.Local;

@Local
public interface Manager<Id, T> {
	
	T save(T entity) throws Exception;
	
	T update(T entity)throws Exception;

	void delete(Id id)throws Exception;

	T getById(Id id)throws Exception;
}
