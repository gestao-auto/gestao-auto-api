package br.edu.pucpr.gestaoauto.manager.manutencao;

import br.edu.pucpr.gestaoauto.dao.manutencao.ManutencaoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;

import javax.ejb.EJB;

public class ManutencaoManager implements Manager<Integer, Manutencao> {

	@EJB
	ManutencaoDAO dao;

	@Override
	public Manutencao save(Manutencao entity) {
		return dao.save(entity);
	}

	@Override
	public Manutencao update(Manutencao entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(dao.getById(id));
	}

	@Override
	public Manutencao getById(Integer id) {
		return dao.getById(id);
	}
}