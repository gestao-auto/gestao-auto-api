package br.edu.pucpr.gestaoauto.model.pessoaJuridica;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pessoa_juridica", catalog = "gestao_auto")
public abstract class PessoaJuridica implements java.io.Serializable {

	private static final long serialVersionUID = -7043219405189925632L;
	private Integer codigo;
	private Integer cnpj;
	private String razaoSocial;
	private String nomeFantasia;

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
	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@Column(name = "nomefantasia", length = 200)
	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
}
