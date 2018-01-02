package br.edu.pucpr.gestaoauto.model.veiculo;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;

@Entity
@Table(name = "veiculo", catalog = "gestao_auto")
public class Veiculo implements java.io.Serializable {

	private static final long serialVersionUID = 8577505457395568330L;

	private Integer codigo;
	private ModeloVeiculo modeloVeiculo;
	private Proprietario proprietario;
	private String placa;
	private String nome;
	private Integer ano;
	private String modalidade;
	private String renavam;
	private Integer odometro;

	public Veiculo() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codveiculo", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codmodelo", nullable = false)
	public ModeloVeiculo getModeloVeiculo() {
		return this.modeloVeiculo;
	}

	public void setModeloVeiculo(ModeloVeiculo modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codproprietario", nullable = false)
	public Proprietario getProprietario() {
		return this.proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	@Column(name = "placa", length = 8)
	public String getPlaca() {
		return this.placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	@Column(name = "nome", length = 45)
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

	@Column(name = "modalidade", length = 10)
	public String getModalidade() {
		return this.modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	@Column(name = "renavam", length = 45)
	public String getRenavam() {
		return this.renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	@Column(name = "odometro")
	public Integer getOdometro() {
		return this.odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

}
