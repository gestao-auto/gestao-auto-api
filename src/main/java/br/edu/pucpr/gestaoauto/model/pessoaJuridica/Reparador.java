package br.edu.pucpr.gestaoauto.model.pessoaJuridica;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "REPARADOR")
public class Reparador extends PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 7140899803145227471L;

	public Reparador() {

	}
}
