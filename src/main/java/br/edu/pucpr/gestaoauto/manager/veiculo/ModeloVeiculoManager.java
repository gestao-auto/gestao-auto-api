package br.edu.pucpr.gestaoauto.manager.veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.ModeloVeiculoDTO;
import br.edu.pucpr.gestaoauto.dao.veiculo.ModeloVeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;
import br.edu.pucpr.gestaoauto.model.veiculo.ModeloVeiculo;

public class ModeloVeiculoManager implements Manager<Integer, ModeloVeiculo> {

	@EJB
	ModeloVeiculoDAO modeloDAO;
	@Inject MarcaVeiculoManager marcaManager;
	
	@Override
	public void save(ModeloVeiculo entity) {
		modeloDAO.save(entity);
	}

	@Override
	public void update(ModeloVeiculo entity) {
		modeloDAO.update(entity);
	}

	@Override
	public void delete(ModeloVeiculo entity) {
		modeloDAO.delete(entity);
	}

	@Override
	public ModeloVeiculo getById(Integer id) {
		return modeloDAO.getById(id);
	}

	public List<ModeloVeiculo> getListModeloVeiculoByMarca(MarcaVeiculo marca) {
		return modeloDAO.getModeloVeiculoPorMarca(marca);
	}

	public ModeloVeiculoDTO convertModeloVeiculoToDTO(ModeloVeiculo modelo) {
		ModeloVeiculoDTO modeloDTO = new ModeloVeiculoDTO();
		modeloDTO.setCodigo(modelo.getCodigo());
		modeloDTO.setNome(modelo.getNome());
		modeloDTO.setAno(modelo.getAno());
		modeloDTO.setMarca(marcaManager.convertMarcaVeiculoToDTO(modelo.getMarca()));
		return modeloDTO;
	}

	public List<ModeloVeiculoDTO> convertListModeloToDTO(List<ModeloVeiculo> modeloList) {
		List<ModeloVeiculoDTO> modeloDTOList = new ArrayList<>();
		for (ModeloVeiculo modeloVeiculo : modeloList) {
			modeloDTOList.add(this.convertModeloVeiculoToDTO(modeloVeiculo));
		}
		return modeloDTOList;
	}
}
