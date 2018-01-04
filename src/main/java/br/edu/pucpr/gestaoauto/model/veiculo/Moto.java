package br.edu.pucpr.gestaoauto.model.veiculo;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class Moto extends Veiculo {
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
