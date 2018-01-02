package br.edu.pucpr.gestaoauto.manager.veiculo;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.MarcaVeiculoDTO;
import br.edu.pucpr.gestaoauto.dao.veiculo.MarcaVeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.veiculo.Marca;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class MarcaManager implements Manager<Integer, Marca> {

	@EJB
	MarcaVeiculoDAO marcaDAO;

	@Override
	public void save(Marca entity) {
		marcaDAO.save(entity);
	}

	@Override
	public void update(Marca entity) {
		marcaDAO.update(entity);
	}

	@Override
	public void delete(Integer id) {
		marcaDAO.delete(this.getById(id));
	}

	@Override
	public Marca getById(Integer id) {
		return marcaDAO.getById(id);
	}

	public List<Marca> getListMarcaVeiculo() {
		return marcaDAO.findAll();
	}

	public List<MarcaVeiculoDTO> convertListMarcaVeiculoToDTO(List<Marca> listMarcaVeiculo) {
		List<MarcaVeiculoDTO> dtoList = new ArrayList<>();
		for (Marca marca : listMarcaVeiculo) {
			dtoList.add(this.convertMarcaVeiculoToDTO(marca));
		}
		return dtoList;
	}

	public MarcaVeiculoDTO convertMarcaVeiculoToDTO(Marca marca) {
		MarcaVeiculoDTO marcaDTO = new MarcaVeiculoDTO();
		marcaDTO.setCodigo(marca.getCodigo());
		marcaDTO.setNome(marca.getNome());
		return marcaDTO;
	}
}
