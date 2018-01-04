package br.edu.pucpr.gestaoauto.manager.veiculo;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.MarcaVeiculoDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.ModeloVeiculoDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.VeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.manager.ProprietarioManager;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ObjectNotFoundException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class VeiculoManager implements Manager<Integer, Veiculo> {

	@EJB VeiculoDAO veiculoDAO;
	@EJB ModeloVeiculoDAO modeloDAO;
	@EJB ProprietarioDAO proprietarioDAO;
	@Inject ModeloManager modeloManager;

	@Override
	public Veiculo save(Veiculo entity) {
        return veiculoDAO.save(entity);
	}

	@Override
	public void delete(Integer id) {
		veiculoDAO.delete(this.getById(id));
	}

	@Override
	public Veiculo update(Veiculo entity) {
        return veiculoDAO.update(entity);
	}

	@Override
	public Veiculo getById(Integer id) {
		return veiculoDAO.getById(id);
	}

	public List<Veiculo> getListByUsuario(Usuario usuario) {
		return veiculoDAO.getListVeiculoPorUsuario(usuario);
	}

	public List<VeiculoDTO> convertListToDTO(List<Veiculo> veiculoList) {
		List<VeiculoDTO> veiculoDTOList = new ArrayList<>();
		for (Veiculo veiculo : veiculoList) {
			veiculoDTOList.add(this.convertVeiculoToDTO(veiculo));
		}
		return veiculoDTOList;
	}

	public void updateOdometroVeiculo(Integer codigoVeiculo, Integer odometro) throws Exception {
		Veiculo veiculo = this.getById(codigoVeiculo);
		if (veiculo == null) {
			throw new ObjectNotFoundException("Veículo não encontrado");
		}
		Integer odometroAtual = veiculo.getOdometro();
		if (odometroAtual.intValue() > odometro.intValue()) {
			throw new Exception("A quilometragem atual do veículo " + veiculo.getNome() + " (" + odometroAtual 
					+ ") é maior que a informada (" + odometro + ")");
		}
		veiculo.setOdometro(odometro);
		this.update(veiculo);
	}

	public void update(VeiculoDTO dto) throws Exception {
		Veiculo veiculo = this.getById(dto.getCodigo());
		if (veiculo == null) {
			throw new ObjectNotFoundException("Veículo não encontrado");
		}
		if (veiculo.getOdometro() > dto.getOdometro()) {
			throw new Exception("A quilometragem atual do veículo " + veiculo.getNome() + " (" + veiculo.getOdometro() + ") é maior que a informada ("
					+ dto.getOdometro() + ")");
		}
		veiculo.setOdometro(dto.getOdometro());
		veiculo.setNome(dto.getNome());
		veiculo.setPlaca(dto.getPlaca());
		veiculo.setRenavam(dto.getRenavam());
		veiculo.setAno(dto.getAno());
		// Demais informações não poderão ser alteradas.
		this.update(veiculo);
	}

	public VeiculoDTO convertVeiculoToDTO(Veiculo veiculo) {
		VeiculoDTO veiculoDTO = new VeiculoDTO();
		veiculoDTO.setCodigo(veiculo.getCodigo());
		veiculoDTO.setNome(veiculo.getNome());
		veiculoDTO.setModalidade(veiculo.getModalidade());
		veiculoDTO.setModelo(modeloManager.convertModeloVeiculoToDTO(veiculo.getModelo()));
		veiculoDTO.setOdometro(veiculo.getOdometro());
		veiculoDTO.setRenavam(veiculo.getRenavam());
		veiculoDTO.setAno(veiculo.getAno());
		veiculoDTO.setPlaca(veiculo.getPlaca());
		veiculoDTO.setProprietario(veiculo.getProprietario().getCodigo());
		return veiculoDTO;
	}

	public Veiculo convertDTOToEntity(VeiculoDTO veiculoDTO) {
		Veiculo veiculo = new Veiculo();
		veiculo.setCodigo(veiculoDTO.getCodigo());
		veiculo.setNome(veiculoDTO.getNome());
		veiculo.setModalidade(veiculoDTO.getModalidade());
		veiculo.setOdometro(veiculoDTO.getOdometro());
		veiculo.setRenavam(veiculoDTO.getRenavam());
		veiculo.setAno(veiculoDTO.getAno());
		veiculo.setPlaca(veiculoDTO.getPlaca());

		Modelo modelo = modeloDAO.getById(veiculoDTO.getModelo().getCodigo());
		veiculo.setModelo(modelo);

		Proprietario proprietario = proprietarioDAO.getById(veiculoDTO.getProprietario());
		veiculo.setProprietario(proprietario);

		return veiculo;
	}

}
