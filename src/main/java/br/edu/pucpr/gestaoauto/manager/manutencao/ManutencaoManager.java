package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.manutencao.TipoManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.ManutencaoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.manager.PessoaJuridicaManager;
import br.edu.pucpr.gestaoauto.manager.revisao.PacoteRevisaoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.ItemManutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Reparo;
import br.edu.pucpr.gestaoauto.model.manutencao.Revisao;
import br.edu.pucpr.gestaoauto.model.manutencao.Sinistro;
import br.edu.pucpr.gestaoauto.model.manutencao.Status;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Reparador;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Seguradora;
import br.edu.pucpr.gestaoauto.model.revisao.ItemRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.ModeloRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;
import br.edu.pucpr.gestaoauto.util.GestaoAutoException;
import br.edu.pucpr.gestaoauto.util.ObjetoNaoEncontradoException;

@Stateless
@LocalBean
public class ManutencaoManager implements Manager<Integer, Manutencao> {
	@Inject ManutencaoDAO dao;

	@Inject VeiculoManager veiculoManager;
	@Inject PacoteRevisaoManager pacoteRevisaoManager;
	@Inject PessoaJuridicaManager pessoaJuridicaManager;
	@Inject ItemManutencaoManager itemManutencaoManager;

	@Override
	public Manutencao save(Manutencao manutencao) throws Exception {
		dao.save(manutencao);
		for (ItemManutencao item : manutencao.getItemManutencao()) {
			item.setManutencao(manutencao);
			itemManutencaoManager.save(item);
		}
		veiculoManager.updateOdometro(manutencao.getVeiculo().getCodigo(), manutencao.getOdometro());
		return manutencao;
	}

	@Override
	public Manutencao update(Manutencao manutencao) throws Exception {
		veiculoManager.updateOdometro(manutencao.getVeiculo().getCodigo(), manutencao.getOdometro());
		dao.update(manutencao);
		for (ItemManutencao item : manutencao.getItemManutencao()) {
			itemManutencaoManager.update(item);
		}
		return manutencao;
	}

	@Override
	public void delete(Integer id) {
		Manutencao manutencao = this.getById(id);
		if (manutencao instanceof Revisao) {
			this.criarManutencaoConformeModeloRevisao(((Revisao) manutencao).getModeloRevisao(), manutencao.getVeiculo());
		}
		itemManutencaoManager.deleteAll(manutencao.getItemManutencao());
		dao.delete(manutencao);
	}

	public void deleteAll(List<Manutencao> manutencaoList) {
		for (Manutencao manutencao : manutencaoList) {
			itemManutencaoManager.deleteAll(manutencao.getItemManutencao());
			dao.delete(manutencao);
		}
	}

	@Override
	public Manutencao getById(Integer id) {
		return dao.getById(id);
	}

	public List<Manutencao> getListManutencaoByVeiculo(Integer codigoVeiculo) {
		return dao.getListManutencaoByVeiculo(codigoVeiculo);
	}

	public List<Revisao> getProximaRevisaoByVeiculo(Integer codigoVeiculo){
	    return dao.getProximaRevisaoByVeiculo(codigoVeiculo);
    }

	public void carregarPacoteRevisaoParaManutencao(Integer codigoVeiculo) throws GestaoAutoException {
		Veiculo veiculo = veiculoManager.getById(codigoVeiculo);
		PacoteRevisao pacoteRevisao = pacoteRevisaoManager.getPacoteRevisaoPorModeloVeiculo(veiculo.getModelo());
		if (pacoteRevisao == null) {
			throw new ObjetoNaoEncontradoException("error.manutencao.pacoteRevisaoNaoEncontrado",
					new Object[] { veiculo.getModelo().getNome(), veiculo.getModelo().getAno() });
		}
		for (ModeloRevisao modelo : pacoteRevisao.getModeloRevisaoList()) {
			this.criarManutencaoConformeModeloRevisao(modelo, veiculo);
		}
	}

	private void criarManutencaoConformeModeloRevisao(ModeloRevisao modelo, Veiculo veiculo) {
		Revisao revisao = new Revisao();
		revisao.setDescricao(modelo.getDescricao());
		revisao.setVeiculo(veiculo);
		revisao.setDataPrevista(this.getDataPrevistaRevisao(veiculo, modelo));
		revisao.setOdometroPrevisto(modelo.getOdometro());
		revisao.setModeloRevisao(modelo);
		revisao.setStatus(this.getStatusRevisaoPorTempoDeUsoOuOdometroPrevisto(veiculo, modelo));
		dao.save(revisao);
		for (ItemRevisao itemRevisao : modelo.getItemRevisaoList()) {
			ItemManutencao itemManutencao = new ItemManutencao();
			itemManutencao.setPecaServico(itemRevisao.getPecaServico());
			itemManutencao.setQuantidade(itemRevisao.getQuantidade());
			itemManutencao.setValorUnitario(this.getValorUnitarioMedioPecaServicoPrevisto(veiculo.getModelo(), modelo, itemManutencao));
			itemManutencao.setManutencao(revisao);
			itemManutencaoManager.save(itemManutencao);
		}
	}

	private LocalDate getDataPrevistaRevisao(Veiculo veiculo, ModeloRevisao revisaoProgramada) {
		return veiculo.getDataAquisicaoPrimeiroDono().plusMonths(revisaoProgramada.getTempoUso());
	}

	private Status getStatusRevisaoPorTempoDeUsoOuOdometroPrevisto(Veiculo veiculo, ModeloRevisao revisaoProgramada) {
		boolean isDataJaOcorreu = this.getDataPrevistaRevisao(veiculo, revisaoProgramada).isBefore(LocalDate.now());
		boolean isOdometroJaOcorreu = veiculo.getOdometro() > revisaoProgramada.getOdometro();
		if (isDataJaOcorreu || isOdometroJaOcorreu) {
			return Status.REALIZADA;
		}
		return Status.PENDENTE;
	}

	private Double getValorUnitarioMedioPecaServicoPrevisto(Modelo modelo, ModeloRevisao modeloRevisao, ItemManutencao item) {
		// TODO: implementar
		return null;
	}

	public List<ManutencaoDTO> convertListToDTO(List<Manutencao> manutencaoList) {
		List<ManutencaoDTO> dtoList = new ArrayList<>();
		for (Object manutencao : manutencaoList) {
			if (manutencao instanceof Revisao) {
				dtoList.add(this.convertEntityToDTO((Revisao) manutencao));
			}
			if (manutencao instanceof Reparo) {
				dtoList.add(this.convertEntityToDTO((Reparo) manutencao));
			}
			if (manutencao instanceof Sinistro) {
				dtoList.add(this.convertEntityToDTO((Sinistro) manutencao));
			}
		}
		return dtoList;
	}

	public ManutencaoDTO convertEntityToDTO(Revisao revisao) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.REVISAO);
		dto.setCodigo(revisao.getCodigo());
		dto.setDescricao(revisao.getDescricao());
		dto.setCodigoVeiculo(revisao.getVeiculo().getCodigo());
		dto.setData(revisao.getData() != null ? revisao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
		dto.setDataPrevista(revisao.getDataPrevista() != null ? revisao.getDataPrevista().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
		dto.setOdometro(revisao.getOdometro());
		dto.setOdometroPrevisto(revisao.getOdometroPrevisto());
		if (revisao.getReparador() != null) {
			dto.setCodigoReparador(revisao.getReparador().getCodigo());
		}
		dto.setStatus(revisao.getStatus().getNome());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(revisao.getItemManutencao()));
		return dto;
	}

	public ManutencaoDTO convertEntityToDTO(Reparo reparo) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.REPARO);
		dto.setCodigo(reparo.getCodigo());
		dto.setDescricao(reparo.getDescricao());
		dto.setCodigoVeiculo(reparo.getVeiculo().getCodigo());
		dto.setData(reparo.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dto.setOdometro(reparo.getOdometro());
		dto.setMotivo(reparo.getMotivo());
		dto.setCodigoReparador(reparo.getReparador().getCodigo());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(reparo.getItemManutencao()));
		return dto;
	}

	public ManutencaoDTO convertEntityToDTO(Sinistro sinistro) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.SINISTRO);
		dto.setCodigo(sinistro.getCodigo());
		dto.setDescricao(sinistro.getDescricao());
		dto.setCodigoVeiculo(sinistro.getVeiculo().getCodigo());
		dto.setData(sinistro.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dto.setOdometro(sinistro.getOdometro());
		dto.setCodigoReparador(sinistro.getReparador().getCodigo());
		dto.setCodigoSeguradora(sinistro.getSeguradora().getCodigo());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(sinistro.getItemManutencao()));
		return dto;
	}

	public Manutencao convertDTOToEntity(ManutencaoDTO dto) throws GestaoAutoException {
		if (dto.getTipoManutencao().equals(TipoManutencaoDTO.REPARO)) {
			Reparo reparo = (dto.getCodigo() != null ? (Reparo) this.getById(dto.getCodigo()) : new Reparo());
			reparo.setDescricao("MANUT");
			reparo.setVeiculo(veiculoManager.getById(dto.getCodigoVeiculo()));
			reparo.setData((dto.getData() != null ? LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null));
			reparo.setMotivo(dto.getMotivo());
			reparo.setOdometro(dto.getOdometro());
			reparo.setReparador((Reparador) pessoaJuridicaManager.getById(dto.getCodigoReparador()));
			reparo.setItemManutencao(itemManutencaoManager.convertListItemManutencaoDTOToEntity(dto.getItemManutencaoList(), reparo));
			return reparo;
		} else if (dto.getTipoManutencao().equals(TipoManutencaoDTO.SINISTRO)) {
			Sinistro sinistro = (dto.getCodigo() != null ? (Sinistro) this.getById(dto.getCodigo()) : new Sinistro());
			sinistro.setDescricao("SINIS");
			sinistro.setVeiculo(veiculoManager.getById(dto.getCodigoVeiculo()));
			sinistro.setData((dto.getData() != null ? LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null));
			sinistro.setOdometro(dto.getOdometro());
			sinistro.setReparador((Reparador) pessoaJuridicaManager.getById(dto.getCodigoReparador()));
			sinistro.setSeguradora((Seguradora) pessoaJuridicaManager.getById(dto.getCodigoSeguradora()));
			sinistro.setItemManutencao(itemManutencaoManager.convertListItemManutencaoDTOToEntity(dto.getItemManutencaoList(), sinistro));
			return sinistro;
		} else {
			Revisao revisao = (Revisao) this.getById(dto.getCodigo());
			revisao.setData((dto.getData() != null ? LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null));
			revisao.setOdometro(dto.getOdometro());
			revisao.setReparador((Reparador) pessoaJuridicaManager.getById(dto.getCodigoReparador()));
			revisao.setStatus(Status.valueOf(dto.getStatus()));
			revisao.setItemManutencao(itemManutencaoManager.convertListItemManutencaoDTOToEntity(dto.getItemManutencaoList(), revisao));
			return revisao;
		}
	}

	public ManutencaoDTO convertEntityToDTO(Manutencao manutencao) {
		if (manutencao instanceof Reparo) {
			return this.convertEntityToDTO((Reparo) manutencao);
		} else if (manutencao instanceof Sinistro) {
			return this.convertEntityToDTO((Sinistro) manutencao);
		} else {
			return this.convertEntityToDTO((Revisao) manutencao);
		}
	}

    public List<Revisao> findByDate(LocalDate data) {
        return dao.revisaoPendenteByData(data);
    }
}