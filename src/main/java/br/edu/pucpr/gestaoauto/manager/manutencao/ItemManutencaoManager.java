package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ItemManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.ItemManutencaoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.manutencao.ItemManutencao;

@Stateless
@LocalBean
public class ItemManutencaoManager implements Manager<Integer, ItemManutencao> {

	@EJB ItemManutencaoDAO dao;
	@Inject PecaServicoManager pecaServicoManager;

	@Override
	public ItemManutencao save(ItemManutencao entity) {
		return dao.save(entity);
	}

	@Override
	public ItemManutencao update(ItemManutencao entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(this.getById(id));

	}

	@Override
	public ItemManutencao getById(Integer id) {
		return dao.getById(id);
	}

	public void deleteAll(List<ItemManutencao> list) {
		for (ItemManutencao itemManutencao : list) {
			this.delete(itemManutencao.getCodigo());
		}
	}

	public List<ItemManutencao> saveAll(List<ItemManutencao> itemManutencaoList) {
		for (ItemManutencao itemManutencao : itemManutencaoList) {
			this.save(itemManutencao);
		}
		return itemManutencaoList;
	}

	public List<ItemManutencaoDTO> convertItemManutencaoListToDTO(List<ItemManutencao> itemManutencaoList) {
		List<ItemManutencaoDTO> dtoList = new ArrayList<>();
		for (ItemManutencao itemManutencao : itemManutencaoList) {
			dtoList.add(this.convertItemManutencaoToDTO(itemManutencao));
		}
		return dtoList;
	}

	public ItemManutencaoDTO convertItemManutencaoToDTO(ItemManutencao itemManutencao) {
		ItemManutencaoDTO dto = new ItemManutencaoDTO();
		dto.setCodigo(itemManutencao.getCodigo());
		dto.setQuantidade(itemManutencao.getQuantidade());
		dto.setValorUnitario(itemManutencao.getValorUnitario());
		dto.setPecaServico(pecaServicoManager.convertPecaServicoToDTO(itemManutencao.getPecaServico()));
		return dto;
	}

	public List<ItemManutencao> convertListItemManutencaoDTOToEntity(List<ItemManutencaoDTO> itemManutencaoDTOList) {
		List<ItemManutencao> itemList = new ArrayList<>();
		for (ItemManutencaoDTO dto : itemManutencaoDTOList) {
			itemList.add(this.convertItemManutencaoListToDTO(dto));
		}
		return itemList;
	}

	private ItemManutencao convertItemManutencaoListToDTO(ItemManutencaoDTO dto) {
		ItemManutencao itemManutencao = (dto.getCodigo() != null ? this.getById(dto.getCodigo()) : new ItemManutencao());
		itemManutencao.setPecaServico(pecaServicoManager.getById(dto.getPecaServico().getCodigo()));
		itemManutencao.setQuantidade(dto.getQuantidade());
		itemManutencao.setValorUnitario(dto.getValorUnitario());
		itemManutencao.setObservacao(dto.getObservacao());
		return itemManutencao;
	}
}
