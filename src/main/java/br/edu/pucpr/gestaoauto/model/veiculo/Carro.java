package br.edu.pucpr.gestaoauto.model.veiculo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Carro extends Veiculo {
    public Carro() {
        this.rodas = 4;
    }

    @Override
    public Short getRodas() {
        return this.rodas;
    }

    @Override
    public void setRodas(Short rodas) {
        this.rodas = 4;
    }
}
