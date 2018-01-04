package br.edu.pucpr.gestaoauto.model.veiculo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modalidade_veiculo", catalog = "gestao_auto")
public class Modalidade implements Serializable {
	
	private static final long serialVersionUID = 877256952644881901L;
	
	private Integer codigo;
	private String nome;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codmodalidade", unique = true, nullable = false)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome", unique = true, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
