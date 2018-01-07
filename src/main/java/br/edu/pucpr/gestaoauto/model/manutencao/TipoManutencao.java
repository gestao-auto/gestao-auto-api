package br.edu.pucpr.gestaoauto.model.manutencao;

public enum TipoManutencao {

	REPARO("Reparo"), 
	SINISTRO("Sinistro"), 
	REVISAO("Revisão");

	private String nome;

	TipoManutencao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
