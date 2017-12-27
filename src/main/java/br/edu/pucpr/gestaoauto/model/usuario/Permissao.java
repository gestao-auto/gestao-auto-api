package br.edu.pucpr.gestaoauto.model.usuario;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissao", catalog = "gestao_auto")
public class Permissao implements java.io.Serializable {

	private static final long serialVersionUID = 6787208705559520897L;

	private Integer codigo;
	private String nome;

	public Permissao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codpermissao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
