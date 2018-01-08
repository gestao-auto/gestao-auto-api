package br.edu.pucpr.gestaoauto.model.manutencao;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Seguradora;

@Entity
@DiscriminatorValue(value = "SINISTRO")
public class Sinistro extends Manutencao {

	private Seguradora seguradora;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codseguradora")
	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}
}
