package br.edu.pucpr.gestaoauto.api.dto.manutencao;

public enum TipoManutencaoDTO {

	REPARO("Reparo"), 
	SINISTRO("Sinistro"), 
	REVISAO("Revisão");

	private String nome;

	TipoManutencaoDTO(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
