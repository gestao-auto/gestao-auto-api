package br.edu.pucpr.gestaoauto.manager.veiculo;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoAlteracaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoCompletoDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.VeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;
import br.edu.pucpr.gestaoauto.model.veiculo.Carro;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;
import br.edu.pucpr.gestaoauto.model.veiculo.Moto;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;
import br.edu.pucpr.gestaoauto.util.GestaoAutoException;
import br.edu.pucpr.gestaoauto.util.ObjetoNaoEncontradoException;
import br.edu.pucpr.gestaoauto.util.QuilometragemExeption;

@Stateless
@LocalBean
public class VeiculoManager implements Manager<Integer, Veiculo> {

    public static final String CARRO = "Carro";
    public static final String MOTOCICLETA = "Motocicleta";

    @EJB VeiculoDAO veiculoDAO;
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

	public void deleteAll(List<Veiculo> veiculoList) {
		for (Veiculo veiculo : veiculoList) {
			this.delete(veiculo.getCodigo());
		}
	}

	public List<Veiculo> getListByUsuario(Integer codigoUsuario) {
		return veiculoDAO.getListByUsuario(codigoUsuario);
	}

	public List<VeiculoCompletoDTO> convertListToDTO(List<Veiculo> veiculoList) {
		List<VeiculoCompletoDTO> veiculoDTOList = new ArrayList<>();
		for (Veiculo veiculo : veiculoList) {
			veiculoDTOList.add(this.convertVeiculoToDTO(veiculo));
		}
		return veiculoDTOList;
	}

	public void updateOdometro(Integer codigoVeiculo, Integer odometro) throws GestaoAutoException {
		Veiculo veiculo = this.getById(codigoVeiculo);
		if (veiculo == null) {
			throw new ObjetoNaoEncontradoException("error.veiculo.veiculoNaoEncontrado", new Object[] { codigoVeiculo });
		}
		Integer odometroAtual = veiculo.getOdometro();
		if (odometroAtual.intValue() > odometro.intValue()) {
			throw new QuilometragemExeption("error.veiculo.quilometragemMaiorQueAtual", new Object[] { veiculo.getNome(), odometroAtual, odometro });
		}
		veiculo.setOdometro(odometro);
		this.update(veiculo);
	}

	public void update(VeiculoCompletoDTO dto) throws GestaoAutoException {
		Veiculo veiculo = this.getById(dto.getCodigo());
		if (veiculo == null) {
			throw new ObjetoNaoEncontradoException("error.veiculo.veiculoNaoEncontrado", new Object[] { dto.getCodigo() });
		}
		if (veiculo.getOdometro() > dto.getOdometro()) {
			throw new QuilometragemExeption("error.veiculo.quilometragemMaiorQueAtual", new Object[] { veiculo.getNome(), veiculo.getOdometro(), dto.getOdometro() });
		}
		veiculo.setOdometro(dto.getOdometro());
		veiculo.setNome(dto.getNome());
		veiculo.setPlaca(dto.getPlaca());
		veiculo.setRenavam(dto.getRenavam());
		veiculo.setAno(dto.getAno());
		// Demais informações não poderão ser alteradas.
		this.update(veiculo);
	}

	public VeiculoCompletoDTO convertVeiculoToDTO(Veiculo veiculo) {
		VeiculoCompletoDTO veiculoDTO = new VeiculoCompletoDTO();
		veiculoDTO.setCodigo(veiculo.getCodigo());
		veiculoDTO.setNome(veiculo.getNome());
		veiculoDTO.setModalidade(veiculo instanceof Carro ? CARRO : MOTOCICLETA);
		veiculoDTO.setModelo(modeloManager.convertModeloVeiculoToDTO(veiculo.getModelo()));
		veiculoDTO.setOdometro(veiculo.getOdometro());
		veiculoDTO.setRenavam(veiculo.getRenavam());
		veiculoDTO.setAno(veiculo.getAno());
		veiculoDTO.setPlaca(veiculo.getPlaca());
		veiculoDTO.setProprietario(veiculo.getProprietario().getCodigo());
		return veiculoDTO;
	}

    public Veiculo convertDTOToEntity(VeiculoAlteracaoDTO veiculoDTO) {
        VeiculoCompletoDTO completoDTO = new VeiculoCompletoDTO();
        completoDTO.setCodigo(veiculoDTO.getCodigo());
        completoDTO.setNome(veiculoDTO.getNome());

        Modelo modelo = modeloManager.getById(veiculoDTO.getModelo());
        completoDTO.setModelo(modeloManager.convertModeloVeiculoToDTO(modelo));

        completoDTO.setPlaca(veiculoDTO.getPlaca());
        completoDTO.setAno(veiculoDTO.getAno());
        completoDTO.setModalidade(veiculoDTO.getModalidade());
        completoDTO.setRenavam(veiculoDTO.getRenavam());
        completoDTO.setOdometro(veiculoDTO.getOdometro());
        completoDTO.setProprietario(veiculoDTO.getProprietario());
	    return this.convertDTOToEntity(completoDTO);
    }

	public Veiculo convertDTOToEntity(VeiculoCompletoDTO veiculoDTO) {
		Veiculo veiculo = null;
		if (veiculoDTO.getModalidade().equals(CARRO)) {
			veiculo = new Carro();
		} else {
			veiculo = new Moto();
		}
		veiculo.setCodigo(veiculoDTO.getCodigo());
		veiculo.setNome(veiculoDTO.getNome());
		veiculo.setOdometro(veiculoDTO.getOdometro());
		veiculo.setRenavam(veiculoDTO.getRenavam());
		veiculo.setAno(veiculoDTO.getAno());
		veiculo.setPlaca(veiculoDTO.getPlaca());

		Modelo modelo = modeloManager.getById(veiculoDTO.getModelo().getCodigo());
		veiculo.setModelo(modelo);

		Proprietario proprietario = proprietarioDAO.getById(veiculoDTO.getProprietario());
		veiculo.setProprietario(proprietario);

		return veiculo;
	}
}