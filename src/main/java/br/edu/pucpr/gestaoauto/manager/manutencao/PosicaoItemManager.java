package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.PosicaoItemDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.PosicaoItemDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.manutencao.ItemManutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.PosicaoItem;

@Stateless
@LocalBean
public class PosicaoItemManager implements Manager<Integer, PosicaoItem> {
	@Inject PosicaoItemDAO dao;
	
	@Override
	public PosicaoItem save(PosicaoItem entity) {
		return dao.save(entity);
	}

	@Override
	public PosicaoItem update(PosicaoItem entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(this.getById(id));
	}

	@Override
	public PosicaoItem getById(Integer id) {
		return dao.getById(id);
	}
	
	public void deleteAll(List<PosicaoItem> list) {
		for (PosicaoItem posicaoItem : list) {
			this.delete(posicaoItem.getCodigo());
		}
	}

	public List<PosicaoItem> saveAll(List<PosicaoItem> posicaoItemList) {
		for (PosicaoItem posicaoItem : posicaoItemList) {
			this.save(posicaoItem);
		}
		return posicaoItemList;
	}

	public List<PosicaoItemDTO> convertPosicaoItemListToDTO(List<PosicaoItem> posicaoItemList) {
		List<PosicaoItemDTO> dtoList = new ArrayList<>();
		for (PosicaoItem posicaoItem : posicaoItemList) {
			dtoList.add(this.convertPosicaoItemToDTO(posicaoItem));
		}
		return dtoList;
	}

	public PosicaoItemDTO convertPosicaoItemToDTO(PosicaoItem posicaoItem) {
		PosicaoItemDTO dto = new PosicaoItemDTO();
		dto.setPosicao(posicaoItem.getPosicao());
		return dto;
	}

	public List<PosicaoItem> convertListPosicaoItemDTOToEntity(List<PosicaoItemDTO> posicaoItemList, ItemManutencao itemManutencao) {
		List<PosicaoItem> posicaoList = new ArrayList<>();
		for (PosicaoItemDTO dto : posicaoItemList) {
			posicaoList.add(this.converPosicaoItemDTOToEntity(dto, itemManutencao));
		}
		return posicaoList;
	}

	private PosicaoItem converPosicaoItemDTOToEntity(PosicaoItemDTO dto, ItemManutencao itemManutencao) {
		PosicaoItem posicaoItem = (dto.getCodigo() != null ? this.getById(dto.getCodigo()) : new PosicaoItem());
		posicaoItem.setItemManutencao(itemManutencao);
		posicaoItem.setPosicao(dto.getPosicao());
		return posicaoItem;
	}

}
