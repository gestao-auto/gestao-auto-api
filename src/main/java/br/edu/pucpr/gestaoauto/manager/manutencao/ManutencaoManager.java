package br.edu.pucpr.gestaoauto.manager.manutencao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ObjectNotFoundException;
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
import br.edu.pucpr.gestaoauto.model.revisao.ItemRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.ModeloRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Stateless
@LocalBean
public class ManutencaoManager implements Manager<Integer, Manutencao> {
	
	@EJB ManutencaoDAO dao;
	@Inject VeiculoManager veiculoManager;
	@Inject PacoteRevisaoManager pacoteRevisaoManager;
	@Inject PessoaJuridicaManager pessoaJuridicaManager;
	@Inject ItemManutencaoManager itemManutencaoManager;

	public Manutencao save(Manutencao entity) {
		return dao.save(entity);
	}

	@Override
	public Manutencao update(Manutencao entity) {
		return dao.update(entity);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(this.getById(id));
	}

	@Override
	public Manutencao getById(Integer id) {
		return dao.getById(id);
	}

	public List<Manutencao> getListManutencaoPorVeiculo(Integer codigoVeiculo) throws ObjectNotFoundException {
		List<Manutencao> manutencaoList = dao.getListManutencaoPorVeiculo(codigoVeiculo);
		if (manutencaoList.isEmpty()) {
			this.carregarPacoteRevisaoParaManutencao(codigoVeiculo);
			return dao.getListManutencaoPorVeiculo(codigoVeiculo);
		}
		return manutencaoList;
	}

	public void carregarPacoteRevisaoParaManutencao(Integer codigoVeiculo) throws ObjectNotFoundException {
		Veiculo veiculo = veiculoManager.getById(codigoVeiculo);
		PacoteRevisao pacoteRevisao = pacoteRevisaoManager.getPacoteRevisaoPorModeloVeiculo(veiculo.getModelo());

		if (pacoteRevisao == null) {
			throw new ObjectNotFoundException("Programação de revisão não encontrada para o veículo : " + veiculo.getModelo().getNome() 
					+ " ano: " + veiculo.getModelo().getAno());
		}

		for (ModeloRevisao modelo : pacoteRevisao.getModeloRevisaoList()) {
			Revisao revisao = new Revisao();
			revisao.setDescricao(modelo.getDescricao());
			revisao.setVeiculo(veiculo);
			revisao.setDataPrevista(this.getDataPrevistaRevisao(veiculo, modelo));
			revisao.setOdometroPrevisto(modelo.getOdometro());
			revisao.setPacote(pacoteRevisao);
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
	}

	private LocalDate getDataPrevistaRevisao(Veiculo veiculo, ModeloRevisao revisaoProgramada) {
		return veiculo.getDataAquisicaoPrimeiroDono().plusMonths(revisaoProgramada.getTempouso());
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

	public List<ManutencaoDTO> convertListManutencaoToDTO(List<Manutencao> manutencaoList) {
		List<ManutencaoDTO> dtoList = new ArrayList<>();
		for (Object manutencao : manutencaoList) {
			if (manutencao instanceof Revisao) {
				dtoList.add(this.convertRevisaoToDTO((Revisao) manutencao));
			}
			if (manutencao instanceof Reparo) {
				dtoList.add(this.convertReparoToDTO((Reparo) manutencao));
			}
			if (manutencao instanceof Sinistro) {
				dtoList.add(this.convertSinistroToDTO((Sinistro) manutencao));
			}
		}
		return dtoList;
	}

	public ManutencaoDTO convertRevisaoToDTO(Revisao revisao) {
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
			dto.setReparador(pessoaJuridicaManager.convertPessoaJuridicaToDTO(revisao.getReparador()));
		}
		dto.setStatus(revisao.getStatus().getNome());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(revisao.getItemManutencao()));
		return dto;
	}

	public ManutencaoDTO convertReparoToDTO(Reparo reparo) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.REPARO);
		dto.setCodigo(reparo.getCodigo());
		dto.setDescricao(reparo.getDescricao());
		dto.setCodigoVeiculo(reparo.getVeiculo().getCodigo());
		dto.setData(reparo.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dto.setOdometro(reparo.getOdometro());
		dto.setMotivo(reparo.getMotivo());
		dto.setReparador(pessoaJuridicaManager.convertPessoaJuridicaToDTO(reparo.getReparador()));
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(reparo.getItemManutencao()));
		return dto;
	}

	public ManutencaoDTO convertSinistroToDTO(Sinistro sinistro) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.SINISTRO);
		dto.setCodigo(sinistro.getCodigo());
		dto.setDescricao(sinistro.getDescricao());
		dto.setCodigoVeiculo(sinistro.getVeiculo().getCodigo());
		dto.setData(sinistro.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dto.setOdometro(sinistro.getOdometro());
		dto.setReparador(pessoaJuridicaManager.convertPessoaJuridicaToDTO(sinistro.getReparador()));
		// dto.setSeguradora(pessoaJuridicaManager.convertPessoaJuridicaToDTO(sinistro.getSeguradora()));
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(sinistro.getItemManutencao()));
		return dto;
	}
}
