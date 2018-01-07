package br.edu.pucpr.gestaoauto.model.manutencao;

public enum TipoPecaServico {

	PECA("Peça"), 
	SERVICO("Serviço");

	private String nome;

	TipoPecaServico(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
