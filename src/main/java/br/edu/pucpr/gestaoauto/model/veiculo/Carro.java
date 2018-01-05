package br.edu.pucpr.gestaoauto.model.veiculo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CARRO")
public class Carro extends Veiculo {

	private static final long serialVersionUID = -1677960466983444204L;

	public Carro() {
		this.rodas = 5;
    }

    @Override
    public Short getRodas() {
        return this.rodas;
    }

    @Override
    public void setRodas(Short rodas) {
		this.rodas = 5;
    }
}
