package br.edu.pucpr.gestaoauto.model.notificacao;

public enum TipoNotificacao {
	REVISAO("Revisão programada"), 
	DOCUMENTO("Documentos"), 
	RODIZIO("Rodízio");

	private String nome;

	TipoNotificacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
