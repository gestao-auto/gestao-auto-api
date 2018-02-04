package br.edu.pucpr.gestaoauto.manager.veiculo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoCompletoDTO;
import br.edu.pucpr.gestaoauto.dao.usuario.ProprietarioDAO;
import br.edu.pucpr.gestaoauto.dao.veiculo.VeiculoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.manager.NotificacaoManager;
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

    @Inject VeiculoDAO veiculoDAO;
	@Inject ProprietarioDAO proprietarioDAO;
    @Inject ModeloManager modeloManager;
    @Inject NotificacaoManager notificacaoManager;

	@Override
	public Veiculo save(Veiculo entity) {
        return veiculoDAO.save(entity);
	}

	@Override
	public void delete(Integer id) throws GestaoAutoException {
		veiculoDAO.delete(this.getById(id));
	}

	@Override
	public Veiculo update(Veiculo entity) {
        return veiculoDAO.update(entity);
	}

	@Override
	public Veiculo getById(Integer id) throws GestaoAutoException {
        Veiculo veiculo = veiculoDAO.getById(id);
        if (veiculo == null) {
            throw new ObjetoNaoEncontradoException("error.veiculo.veiculoNaoEncontrado", new Object[] { id });
        }
	    return veiculo;
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

	public void updateOdometro(Integer codigoVeiculo, Integer novoOdometro) throws Exception {
		Veiculo veiculo = this.getById(codigoVeiculo);
		Integer odometroAtual = veiculo.getOdometro();
		if (odometroAtual.intValue() > novoOdometro.intValue()) {
			throw new QuilometragemExeption(
			        "error.veiculo.quilometragemMaiorQueAtual"
                    , new Object[] { veiculo.getNome(), odometroAtual, novoOdometro });
		}
		else if(odometroAtual.intValue() < novoOdometro.intValue()) {
            veiculo.setOdometro(novoOdometro);
            this.update(veiculo);
            notificacaoManager.conferirRevisao(veiculo);
        }
	}

	public void update(VeiculoCompletoDTO dto) throws GestaoAutoException {
		Veiculo veiculo = this.getById(dto.getCodigo());
		if (veiculo.getOdometro() > dto.getOdometro()) {
			throw new QuilometragemExeption(
			        "error.veiculo.quilometragemMaiorQueAtual"
                    , new Object[] { veiculo.getNome(), veiculo.getOdometro(), dto.getOdometro() });
		}
		veiculo.setOdometro(dto.getOdometro());
		veiculo.setNome(dto.getNome());
		veiculo.setPlaca(dto.getPlaca());
		veiculo.setRenavam(dto.getRenavam());
		veiculo.setAno(Integer.parseInt(dto.getAno()));
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
		veiculoDTO.setAno(veiculo.getAno().toString());
		veiculoDTO.setPlaca(veiculo.getPlaca());
		veiculoDTO.setProprietario(veiculo.getProprietario().getCodigo());
		veiculoDTO.setDataAquisicao(veiculo.getDataAquisicao() != null ? veiculo.getDataAquisicao().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		veiculoDTO.setUnicoDono(veiculo.getUnicoDono());
		veiculoDTO.setDataAquisicaoPrimeiroDono(veiculo.getDataAquisicaoPrimeiroDono() != null ? veiculo.getDataAquisicaoPrimeiroDono().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);

		return veiculoDTO;
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
		veiculo.setAno(Integer.parseInt(veiculoDTO.getAno()));
		veiculo.setPlaca(veiculoDTO.getPlaca());
		veiculo.setDataAquisicao(veiculoDTO.getDataAquisicao() != null ? LocalDate.parse(veiculoDTO.getDataAquisicao(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		veiculo.setUnicoDono(veiculoDTO.isUnicoDono());
		if (veiculo.getUnicoDono()) {
			veiculo.setDataAquisicaoPrimeiroDono(null);
		} else {
			veiculo.setDataAquisicaoPrimeiroDono(veiculoDTO.getDataAquisicaoPrimeiroDono() != null ? LocalDate.parse(veiculoDTO.getDataAquisicaoPrimeiroDono(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null);
		}
		
		Modelo modelo = modeloManager.getById(veiculoDTO.getModelo().getCodigo());
		veiculo.setModelo(modelo);

        Proprietario proprietario = null;
        proprietario = proprietarioDAO.getById(veiculoDTO.getProprietario());
        veiculo.setProprietario(proprietario);

		return veiculo;
	}
}