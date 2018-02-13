package br.edu.pucpr.gestaoauto.api.dto.manutencao;

import java.util.List;

public class ItemManutencaoDTO {

	private Integer codigo;
	private PecaServicoDTO pecaServico;
	private Double valorUnitario;
	private Float quantidade;
	private String observacao;
	private List<PosicaoItemDTO> posicoes;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public PecaServicoDTO getPecaServico() {
		return pecaServico;
	}

	public void setPecaServico(PecaServicoDTO pecaServico) {
		this.pecaServico = pecaServico;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public List<PosicaoItemDTO> getPosicoes() {
		return posicoes;
	}

	public void setPosicoes(List<PosicaoItemDTO> posicoes) {
		this.posicoes = posicoes;
	}


}
