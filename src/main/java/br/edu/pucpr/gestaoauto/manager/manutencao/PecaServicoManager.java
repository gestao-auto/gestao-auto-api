package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.PecaServicoDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.PecaServicoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.manutencao.PecaServico;

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
	
	public List<PecaServico> getListPecaServico() {
		return dao.findAll();
	}
	
	public List<PecaServicoDTO> convertListToDTO(List<PecaServico> pecaServicos) {
		List<PecaServicoDTO> dtoList = new ArrayList<>();
		for (PecaServico pecaServico : pecaServicos) {
			dtoList.add(this.convertPecaServicoToDTO(pecaServico));
		}
		return dtoList;
	}

	public PecaServicoDTO convertPecaServicoToDTO(PecaServico pecaServico) {
		PecaServicoDTO dto = new PecaServicoDTO();
		dto.setCodigo(pecaServico.getCodigo());
		dto.setNome(pecaServico.getNome());
		dto.setTipo(pecaServico.getTipo().getNome());
		return dto;
	}
}
