package br.edu.pucpr.gestaoauto.model.manutencao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reparador", catalog = "gestao_auto")
public class Reparador implements java.io.Serializable {

	private static final long serialVersionUID = -5023288711885485686L;

	private Integer codigo;
	private Integer cnpj;
	private String razaosocial;
	private String nomefantasia;

	public Reparador() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codreparador", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "cnpj")
	public Integer getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(Integer cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "razaosocial", length = 200)
	public String getRazaosocial() {
		return this.razaosocial;
	}

	public void setRazaosocial(String razaosocial) {
		this.razaosocial = razaosocial;
	}

	@Column(name = "nomefantasia", length = 200)
	public String getNomefantasia() {
		return this.nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}
}
