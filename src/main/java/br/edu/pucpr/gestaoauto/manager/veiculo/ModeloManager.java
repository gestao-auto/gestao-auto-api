package br.edu.pucpr.gestaoauto.manager.veiculo;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.ModeloDTO;
import br.edu.pucpr.gestaoauto.dao.veiculo.ModeloVeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.veiculo.Marca;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ModeloManager implements Manager<Integer, Modelo> {

	@EJB
	ModeloVeiculoDAO modeloDAO;
	@Inject
    MarcaManager marcaManager;
	
	@Override
	public void save(Modelo entity) {
		modeloDAO.save(entity);
	}

	@Override
	public void update(Modelo entity) {
		modeloDAO.update(entity);
	}

	@Override
	public void delete(Integer id) {
		modeloDAO.delete(this.getById(id));
	}

	@Override
	public Modelo getById(Integer id) {
		return modeloDAO.getById(id);
	}

	public List<Modelo> getListByMarca(Marca marca) {
		return modeloDAO.getModeloVeiculoPorMarca(marca);
	}

	public ModeloDTO convertModeloVeiculoToDTO(Modelo modelo) {
		ModeloDTO modeloDTO = new ModeloDTO();
		modeloDTO.setCodigo(modelo.getCodigo());
		modeloDTO.setNome(modelo.getNome());
		modeloDTO.setAno(modelo.getAno());
		modeloDTO.setMarca(marcaManager.convertMarcaVeiculoToDTO(modelo.getMarca()));
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
