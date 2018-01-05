package br.edu.pucpr.gestaoauto.model.veiculo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MOTOCICLETA")
public class Moto extends Veiculo {

	private static final long serialVersionUID = -1616988479273263891L;

	public Moto() {
        this.rodas = 2;
    }

    @Override
    public Short getRodas() {
        return this.rodas;
    }

    @Override
    public void setRodas(Short rodas) {
        this.rodas = 2;
    }
}
