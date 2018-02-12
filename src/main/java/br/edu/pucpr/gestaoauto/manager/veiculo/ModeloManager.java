package br.edu.pucpr.gestaoauto.manager.veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.ModeloDTO;
import br.edu.pucpr.gestaoauto.dao.veiculo.ModeloVeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.veiculo.Marca;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;

public class ModeloManager implements Manager<Integer, Modelo> {
	@Inject	ModeloVeiculoDAO dao;

	@Inject MarcaManager marcaManager;
	
	@Override
	public Modelo save(Modelo entity) {
		return dao.save(entity);
	}

	@Override
	public Modelo update(Modelo entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(this.getById(id));
	}

	@Override
	public Modelo getById(Integer id) {
		return dao.getById(id);
	}

	public List<Modelo> getListByMarca(Marca marca) {
		return dao.getModeloVeiculoPorMarca(marca);
	}

	public ModeloDTO convertModeloVeiculoToDTO(Modelo modelo) {
		ModeloDTO modeloDTO = new ModeloDTO();
		modeloDTO.setCodigo(modelo.getCodigo());
		modeloDTO.setNome(modelo.getNome());
		modeloDTO.setAno(modelo.getAno());
		modeloDTO.setVersao(modelo.getVersao());
		modeloDTO.setMarca(marcaManager.convertEntityToDTO(modelo.getMarca()));
		return modeloDTO;
	}

	public List<ModeloDTO> convertListToDTO(List<Modelo> modeloList) {
		List<ModeloDTO> modeloDTOList = new ArrayList<>();
		for (Modelo modeloVeiculo : modeloList) {
			modeloDTOList.add(this.convertModeloVeiculoToDTO(modeloVeiculo));
		}
		return modeloDTOList;
	}
}