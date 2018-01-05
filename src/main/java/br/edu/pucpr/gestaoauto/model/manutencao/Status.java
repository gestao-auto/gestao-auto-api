package br.edu.pucpr.gestaoauto.model.manutencao;

public enum Status {

	REALIZADA("Realizada"), 
	CANCELADA("Cancelada"), 
	PENDENTE("Pendente");

	private String nome;

	Status(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
