package br.edu.pucpr.gestaoauto.model.pessoaJuridica;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "POSTO_COMBUSTIVEL")
public class PostoCombustivel extends PessoaJuridica implements Serializable {

	private static final long serialVersionUID = -2755418613296827436L;

	public PostoCombustivel() {
	}
}
