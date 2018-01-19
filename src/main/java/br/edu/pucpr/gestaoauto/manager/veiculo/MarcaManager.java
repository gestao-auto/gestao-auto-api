package br.edu.pucpr.gestaoauto.manager.veiculo;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.MarcaDTO;
import br.edu.pucpr.gestaoauto.dao.veiculo.MarcaVeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.veiculo.Marca;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class MarcaManager implements Manager<Integer, Marca> {
	@Inject	MarcaVeiculoDAO dao;

	@Override
	public Marca save(Marca entity) {
		return dao.save(entity);
	}

	@Override
	public Marca update(Marca entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(this.getById(id));
	}

	@Override
	public Marca getById(Integer id) {
		return dao.getById(id);
	}

	public List<Marca> getList() {
		return dao.findAll();
	}

	public List<MarcaDTO> convertListToDTO(List<Marca> listMarcaVeiculo) {
		List<MarcaDTO> dtos = new ArrayList<>();
		for (Marca marca : listMarcaVeiculo) {
			dtos.add(this.convertEntityToDTO(marca));
		}
		return dtos;
	}

	public MarcaDTO convertEntityToDTO(Marca marca) {
		MarcaDTO dto = new MarcaDTO();
		dto.setCodigo(marca.getCodigo());
		dto.setNome(marca.getNome());
		return dto;
	}

	public Marca convertDTOToEntity(MarcaDTO dto) {
		Marca entity = new Marca();
		entity.setCodigo(dto.getCodigo());
		entity.setNome(dto.getNome());
		return entity;
	}
}