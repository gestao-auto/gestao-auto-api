package br.edu.pucpr.gestaoauto.api.dto.indicador;

public class ItemManutencaoDTO {
	private String nome;
	private Double quantidade;

	public ItemManutencaoDTO(String nome, Double quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
