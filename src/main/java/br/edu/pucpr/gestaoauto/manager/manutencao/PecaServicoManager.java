package br.edu.pucpr.gestaoauto.manager.manutencao;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.PecaServicoDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.PecaServicoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.manutencao.PecaServico;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
@LocalBean
public class PecaServicoManager implements Manager<Integer, PecaServico> {
	@Inject	PecaServicoDAO dao;

	@Override
	public PecaServico save(PecaServico entity) {
		return dao.save(entity);
	}

	@Override
	public PecaServico update(PecaServico entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(this.getById(id));
	}

	@Override
	public PecaServico getById(Integer id) {
		return dao.getById(id);
	}

	public PecaServicoDTO convertPecaServicoToDTO(PecaServico pecaServico) {
		PecaServicoDTO dto = new PecaServicoDTO();
		dto.setCodigo(pecaServico.getCodigo());
		dto.setNome(pecaServico.getNome());
		dto.setTipo(pecaServico.getTipo().getNome());
		return dto;
	}
}
