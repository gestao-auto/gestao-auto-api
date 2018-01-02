package br.edu.pucpr.gestaoauto.manager.veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.MarcaVeiculoDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.ModeloVeiculoDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.VeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;
import br.edu.pucpr.gestaoauto.model.veiculo.ModeloVeiculo;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Stateless
@LocalBean
public class VeiculoManager implements Manager<Integer, Veiculo> {

	@EJB VeiculoDAO veiculoDAO;
	@EJB MarcaVeiculoDAO marcaDAO;
	@EJB ModeloVeiculoDAO modeloDAO;
	@EJB ProprietarioDAO proprietarioDAO;
	@Inject ModeloVeiculoManager modeloManager;

	@Override
	public void save(Veiculo entity) {
		veiculoDAO.save(entity);
	}

	@Override
	public void delete(Veiculo entity) {
		veiculoDAO.delete(entity);
	}

	@Override
	public void update(Veiculo entity) {
		veiculoDAO.update(entity);
	}

	@Override
	public Veiculo getById(Integer id) {
		return veiculoDAO.getById(id);
	}

	public List<Veiculo> getListVeiculoByUsuario(Usuario usuario) {
		return veiculoDAO.getListVeiculoPorUsuario(usuario);
	}

	public List<VeiculoDTO> convertListVeiculoToDTO(List<Veiculo> veiculoList) {
		List<VeiculoDTO> veiculoDTOList = new ArrayList<>();
		for (Veiculo veiculo : veiculoList) {
			veiculoDTOList.add(this.convertVeiculoToDTO(veiculo));
		}
		return veiculoDTOList;
	}

	public VeiculoDTO convertVeiculoToDTO(Veiculo veiculo) {
		VeiculoDTO veiculoDTO = new VeiculoDTO();
		veiculoDTO.setCodigo(veiculo.getCodigo());
		veiculoDTO.setNome(veiculo.getNome());
		veiculoDTO.setModalidade(veiculo.getModalidade());
		veiculoDTO.setModelo(modeloManager.convertModeloVeiculoToDTO(veiculo.getModeloVeiculo()));
		veiculoDTO.setOdometro(veiculo.getOdometro());
		veiculoDTO.setRenavam(veiculo.getRenavam());
		veiculoDTO.setAno(veiculo.getAno());
		veiculoDTO.setPlaca(veiculo.getPlaca());
		return veiculoDTO;
	}

	public Veiculo convertVeiculoDTOToEntity(VeiculoDTO veiculoDTO) {
		Veiculo veiculo = new Veiculo();
		veiculo.setCodigo(veiculoDTO.getCodigo());
		veiculo.setNome(veiculoDTO.getNome());
		veiculo.setModalidade(veiculoDTO.getModalidade());
		veiculo.setOdometro(veiculoDTO.getOdometro());
		veiculo.setRenavam(veiculoDTO.getRenavam());
		veiculo.setAno(veiculoDTO.getAno());
		veiculo.setPlaca(veiculoDTO.getPlaca());

		ModeloVeiculo modelo = modeloDAO.getById(veiculoDTO.getModelo().getCodigo());
		veiculo.setModeloVeiculo(modelo);

		Proprietario proprietario = proprietarioDAO.getById(veiculoDTO.getProprietario().getCodigo());
		veiculo.setProprietario(proprietario);

		return veiculo;
	}

}
