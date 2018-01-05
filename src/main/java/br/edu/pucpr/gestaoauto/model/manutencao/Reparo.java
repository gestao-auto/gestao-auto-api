package br.edu.pucpr.gestaoauto.model.manutencao;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "REPARO")
public class Reparo extends Manutencao {

	private String motivo;

	@Column(name = "motivo")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
