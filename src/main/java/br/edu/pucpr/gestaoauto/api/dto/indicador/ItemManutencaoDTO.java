package br.edu.pucpr.gestaoauto.api.dto.indicador;

public class ItemManutencaoDTO {
	private String nomePecaServico;
	private Double valorUnitario;
	private Float quantidade;

	public ItemManutencaoDTO(String nomePecaServico, Double valorUnitario, Float quantidade) {
		this.nomePecaServico = nomePecaServico;
		this.valorUnitario = valorUnitario;
		this.quantidade = quantidade;
	}

	public String getNomePecaServico() {
		return nomePecaServico;
	}

	public void setNomePecaServico(String nomePecaServico) {
		this.nomePecaServico = nomePecaServico;
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

}
