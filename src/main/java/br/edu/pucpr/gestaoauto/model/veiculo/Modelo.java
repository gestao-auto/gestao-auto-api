package br.edu.pucpr.gestaoauto.model.veiculo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modelo_veiculo", catalog = "gestao_auto")
public class Modelo implements Serializable {

	private static final long serialVersionUID = -5763024517503780570L;
	private Integer codigo;
	private Marca marca;
	private String nome;
	private Integer ano;
	private String versao;
	private Short capacidadeCombustivel;

	public Modelo() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codmodelo", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codmarca", nullable = false)
	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Column(name = "nome", length = 200)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ano")
	public Integer getAno() {
		return this.ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@Column(name = "versao")
	public String getVersao() {
		return this.versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	@Column(name = "capacidadetanque")
	public Short getCapacidadeCombustivel() {
		return this.capacidadeCombustivel;
	}

	public void setCapacidadeCombustivel(Short capacidadeCombustivel) {
		this.capacidadeCombustivel = capacidadeCombustivel;
	}
}
