package br.edu.pucpr.gestaoauto.model.veiculo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "marca_veiculo", catalog = "gestao_auto")
public class Marca implements java.io.Serializable {

	private static final long serialVersionUID = 6537648009628484983L;

	private Integer codigo;
	private String nome;

	public Marca() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codmarca", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
