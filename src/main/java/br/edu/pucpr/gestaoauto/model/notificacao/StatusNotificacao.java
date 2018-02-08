package br.edu.pucpr.gestaoauto.model.notificacao;

public enum StatusNotificacao {
	
	GERADA("Gerada"), 
	LIDA("Lida");

    private String nome;

    StatusNotificacao(String nome){
        this.nome = nome;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}  
    
}
