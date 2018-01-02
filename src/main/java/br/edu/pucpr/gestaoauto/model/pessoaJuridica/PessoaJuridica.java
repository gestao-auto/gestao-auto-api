package br.edu.pucpr.gestaoauto.model.pessoaJuridica;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "pessoa_juridica", catalog = "gestao_auto")
public abstract class PessoaJuridica implements java.io.Serializable {

	private Integer codigo;
	private Integer cnpj;
	private String razaosocial;
	private String nomefantasia;

	public PessoaJuridica() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codigo", unique = true, nullable = false)
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
