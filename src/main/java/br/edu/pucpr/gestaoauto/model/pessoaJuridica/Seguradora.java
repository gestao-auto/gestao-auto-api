package br.edu.pucpr.gestaoauto.model.pessoaJuridica;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "SEGURADORA")
public class Seguradora extends PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 2601518109641801332L;

	public Seguradora() {

	}
}
