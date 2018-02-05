package br.edu.pucpr.gestaoauto.manager.manutencao;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ItemManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.manutencao.ManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.manutencao.TipoManutencaoDTO;
import br.edu.pucpr.gestaoauto.dao.manutencao.ManutencaoDAO;
import br.edu.pucpr.gestaoauto.manager.Manager;
import br.edu.pucpr.gestaoauto.manager.PessoaJuridicaManager;
import br.edu.pucpr.gestaoauto.manager.revisao.PacoteRevisaoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.*;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Seguradora;
import br.edu.pucpr.gestaoauto.model.revisao.ItemRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.ModeloRevisao;
import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;
import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;
import br.edu.pucpr.gestaoauto.util.GestaoAutoException;
import br.edu.pucpr.gestaoauto.util.ObjetoNaoEncontradoException;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
	    this.validarManutencao(manutencao);

		dao.save(manutencao);
		for (ItemManutencao item : manutencao.getItemManutencao()) {
			item.setManutencao(manutencao);
			itemManutencaoManager.save(item);
		}

        if(manutencao.getOdometro() != null) {
            veiculoManager.updateOdometro(manutencao.getVeiculo().getCodigo(), manutencao.getOdometro());
        }
		return manutencao;
	}

    @Override
	public Manutencao update(Manutencao manutencao) throws Exception {
        this.validarManutencao(manutencao);

		dao.update(manutencao);
		for (ItemManutencao item : manutencao.getItemManutencao()) {
			itemManutencaoManager.update(item);
		}

        if(manutencao.getOdometro() != null) {
            veiculoManager.updateOdometro(manutencao.getVeiculo().getCodigo(), manutencao.getOdometro());
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
		if (veiculo.getUnicoDono()) {
			return veiculo.getDataAquisicao().plusMonths(revisaoProgramada.getTempoUso());
		}
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
		double valor = modelo.getMarca().getCodigo() * 0.15 * (modeloRevisao.getOdometro() * 0.05);
        //TODO remover calculo fake
        return valor;
    }

	public Double getValorTotal(Manutencao manutencao) {
		Double valor = 0d;
		for (ItemManutencao item : manutencao.getItemManutencao()) {
			valor += item.getValorUnitario() * item.getQuantidade();
		}
		return valor;
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
			dto.setNomeReparador(revisao.getReparador().getNomeFantasia());
		}
		dto.setStatus(revisao.getStatus());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(revisao.getItemManutencao()));
        Double valorTotal = 0.0;
		for(ItemManutencaoDTO item: dto.getItemManutencaoList()){
            valorTotal += item.getValorUnitario() * item.getQuantidade();
        }
        dto.setValorTotal(valorTotal);
		return dto;
	}

	public ManutencaoDTO convertEntityToDTO(Reparo reparo) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.REPARO);
		dto.setCodigo(reparo.getCodigo());
		dto.setDescricao(reparo.getDescricao());
		dto.setCodigoVeiculo(reparo.getVeiculo().getCodigo());
        dto.setData(reparo.getData() != null ? reparo.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
		dto.setOdometro(reparo.getOdometro());
		dto.setMotivo(reparo.getMotivo());
		dto.setCodigoReparador(reparo.getReparador().getCodigo());
		dto.setNomeReparador(reparo.getReparador().getNomeFantasia());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(reparo.getItemManutencao()));
		return dto;
	}

	public ManutencaoDTO convertEntityToDTO(Sinistro sinistro) {
		ManutencaoDTO dto = new ManutencaoDTO();
		dto.setTipoManutencao(TipoManutencaoDTO.SINISTRO);
		dto.setCodigo(sinistro.getCodigo());
		dto.setDescricao(sinistro.getDescricao());
		dto.setCodigoVeiculo(sinistro.getVeiculo().getCodigo());
        dto.setData(sinistro.getData() != null ? sinistro.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null);
		dto.setOdometro(sinistro.getOdometro());
		dto.setCodigoReparador(sinistro.getReparador().getCodigo());
		dto.setNomeReparador(sinistro.getReparador().getNomeFantasia());
		dto.setCodigoSeguradora(sinistro.getSeguradora().getCodigo());
		dto.setNomeSeguradora(sinistro.getSeguradora().getNomeFantasia());
		dto.setItemManutencaoList(itemManutencaoManager.convertItemManutencaoListToDTO(sinistro.getItemManutencao()));
		return dto;
	}

	public Manutencao convertDTOToEntity(ManutencaoDTO dto) throws GestaoAutoException {
	    this.validarManutencao(dto);

	    Manutencao manutencao = this.getEntity(dto);
	    manutencao.setDescricao((dto.getDescricao() != null && dto.getDescricao().isEmpty())
                ? dto.getTipoManutencao().getNome() : dto.getDescricao());
        manutencao.setVeiculo(veiculoManager.getById(dto.getCodigoVeiculo()));
        manutencao.setData((dto.getData() != null
                ? LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : null));
        manutencao.setOdometro(dto.getOdometro());
        manutencao.setReparador(pessoaJuridicaManager.carregaReparador(dto));
        manutencao.setItemManutencao(
                itemManutencaoManager.convertListItemManutencaoDTOToEntity(
                        dto.getItemManutencaoList(), manutencao));
		if (dto.getTipoManutencao().equals(TipoManutencaoDTO.REPARO)) {
			Reparo reparo = (Reparo) manutencao;
			reparo.setMotivo(dto.getMotivo());
			return reparo;
		} else if (dto.getTipoManutencao().equals(TipoManutencaoDTO.SINISTRO)) {
			Sinistro sinistro = (Sinistro) manutencao;
			sinistro.setSeguradora(pessoaJuridicaManager.carregaSeguradora(dto));
			return sinistro;
		} else {
			Revisao revisao = (Revisao) manutencao;
			revisao.setStatus(dto.getStatus());
			return revisao;
		}
	}

    private Manutencao getEntity(ManutencaoDTO dto) {
        return (dto.getTipoManutencao().equals(TipoManutencaoDTO.REPARO))
            ? (dto.getCodigo() != null) ? (Reparo) this.getById(dto.getCodigo()) : new Reparo()
                : (dto.getTipoManutencao().equals(TipoManutencaoDTO.SINISTRO))
                ? (dto.getCodigo() != null) ? (Sinistro) this.getById(dto.getCodigo()) : new Sinistro()
                    : (Revisao) this.getById(dto.getCodigo());
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

    private void validarManutencao(Manutencao manutencao) throws GestaoAutoException{
        if(manutencao.getItemManutencao().isEmpty()){
            //throw new ObjetoNaoEncontradoException("error.manutencao.item.vazio");
        }
        else if(manutencao.getData() == null){
            throw new ObjetoNaoEncontradoException("error.manutencao.data.vazia");
        }
        else if(!(manutencao instanceof Revisao)
                && (manutencao.getOdometro() == null || manutencao.getOdometro().intValue() <= 0)){
            throw new ObjetoNaoEncontradoException("error.manutencao.odometro.vazio");
        }
        else if(manutencao.getVeiculo() == null){
            throw new ObjetoNaoEncontradoException("error.manutencao.veiculo.vazio");
        }
        else if(manutencao.getReparador() == null){
            throw new ObjetoNaoEncontradoException("error.manutencao.reparador.vazio");
        }
    }

    private void validarManutencao(ManutencaoDTO dto) throws GestaoAutoException{
        if(dto.getItemManutencaoList().isEmpty()){
            //throw new ObjetoNaoEncontradoException("error.manutencao.item.vazio");
        }
        else if(dto.getData() == null){
            throw new ObjetoNaoEncontradoException("error.manutencao.data.vazia");
        }
        else if(!dto.getTipoManutencao().equals(TipoManutencaoDTO.REVISAO)
                && (dto.getOdometro() == null || dto.getOdometro().intValue() <= 0)){
            throw new ObjetoNaoEncontradoException("error.manutencao.odometro.vazio");
        }
        else if(dto.getCodigoVeiculo() == null){
            throw new ObjetoNaoEncontradoException("error.manutencao.veiculo.vazio");
        }
        else if(dto.getCodigoReparador() == null && dto.getNomeReparador() == null){
            throw new ObjetoNaoEncontradoException("error.manutencao.reparador.vazio");
        }
    }
}