package br.edu.pucpr.gestaoauto.api.dto.indicador;

import java.util.ArrayList;
import java.util.List;

import br.edu.pucpr.gestaoauto.model.manutencao.ItemManutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.model.manutencao.Sinistro;

public class GastoIndividualComManutencaoDTO {
	
	private String nomeVeiculo;
	private String modeloVeiculo;
	private String marcaVeiculo;
	private String descricao;
	private String nomeReparador;
	private String nomeSeguradora;
	private Integer odometro;
	private String data;
	private List<ItemManutencaoDTO> itemManutencaoList;

	public GastoIndividualComManutencaoDTO(Manutencao manutencao) {
		this.nomeVeiculo = manutencao.getVeiculo().getNome();
		this.modeloVeiculo = manutencao.getVeiculo().getModelo().getNome();
		this.marcaVeiculo = manutencao.getVeiculo().getModelo().getMarca().getNome();
		this.descricao = manutencao.getDescricao();
		this.nomeReparador = manutencao.getReparador().getNomeFantasia();
		this.nomeSeguradora = (manutencao instanceof Sinistro) ? ((Sinistro) manutencao).getSeguradora().getNomeFantasia() : null;
		this.odometro = manutencao.getOdometro();
		this.itemManutencaoList = this.convertItemManutencaoToDTO(manutencao.getItemManutencao());
	}

	public String getNomeVeiculo() {
		return nomeVeiculo;
	}

	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public String getMarcaVeiculo() {
		return marcaVeiculo;
	}

	public void setMarcaVeiculo(String marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeReparador() {
		return nomeReparador;
	}

	public void setNomeReparador(String nomeReparador) {
		this.nomeReparador = nomeReparador;
	}

	public String getNomeSeguradora() {
		return nomeSeguradora;
	}

	public void setNomeSeguradora(String nomeSeguradora) {
		this.nomeSeguradora = nomeSeguradora;
	}

	public Integer getOdometro() {
		return odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<ItemManutencaoDTO> getItemManutencaoList() {
		return itemManutencaoList;
	}

	public void setItemManutencaoList(List<ItemManutencaoDTO> itemManutencaoList) {
		this.itemManutencaoList = itemManutencaoList;
	}

	public List<ItemManutencaoDTO> convertItemManutencaoToDTO(List<ItemManutencao> itens) {
		List<ItemManutencaoDTO> dto = new ArrayList<>();
		for (ItemManutencao item : itens) {
			dto.add(new ItemManutencaoDTO(item.getPecaServico().getNome(), item.getValorUnitario(), item.getQuantidade()));
		}
		return dto;
	}
}
