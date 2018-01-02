package br.edu.pucpr.gestaoauto.manager.veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.MarcaVeiculoDTO;
import br.edu.pucpr.gestaoauto.dao.veiculo.MarcaVeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;

@Stateless
@LocalBean
public class MarcaVeiculoManager implements Manager<Integer, MarcaVeiculo> {

	@EJB
	MarcaVeiculoDAO marcaDAO;

	@Override
	public void save(MarcaVeiculo entity) {
		marcaDAO.save(entity);
	}

	@Override
	public void update(MarcaVeiculo entity) {
		marcaDAO.update(entity);
	}

	@Override
	public void delete(Integer id) {
		marcaDAO.delete(this.getById(id));
	}

	@Override
	public MarcaVeiculo getById(Integer id) {
		return marcaDAO.getById(id);
	}

	public List<MarcaVeiculo> getListMarcaVeiculo() {
		return marcaDAO.findAll();
	}

	public List<MarcaVeiculoDTO> convertListMarcaVeiculoToDTO(List<MarcaVeiculo> listMarcaVeiculo) {
		List<MarcaVeiculoDTO> dtoList = new ArrayList<>();
		for (MarcaVeiculo marca : listMarcaVeiculo) {
			dtoList.add(this.convertMarcaVeiculoToDTO(marca));
		}
		return dtoList;
	}

	public MarcaVeiculoDTO convertMarcaVeiculoToDTO(MarcaVeiculo marca) {
		MarcaVeiculoDTO marcaDTO = new MarcaVeiculoDTO();
		marcaDTO.setCodigo(marca.getCodigo());
		marcaDTO.setNome(marca.getNome());
		return marcaDTO;
	}
}
