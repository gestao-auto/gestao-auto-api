package br.edu.pucpr.gestaoauto.model.veiculo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipveiculo", discriminatorType = DiscriminatorType.STRING)
@Table(name = "veiculo", catalog = "gestao_auto")
public abstract class Veiculo implements Serializable {

	private static final long serialVersionUID = 8577505457395568330L;

	private Integer codigo;
	private Modelo modelo;
	private Proprietario proprietario;
	private String placa;
	private String nome;
	private Integer ano;
	private String renavam;
	private Integer odometro;
	private LocalDate dataAquisicao;
	private Integer odometroAquisicao;
	private Boolean unicoDono;
	private LocalDate dataAquisicaoPrimeiroDono;

	protected Short rodas;

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
	public Modelo getModelo() {
		return this.modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
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

	public Short getRodas() {
		return this.rodas;
	}

	public void setRodas(Short rodas) {
		this.rodas = rodas;
	}

	@Column(name = "dataquisicao")
	public LocalDate getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(LocalDate dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	@Column(name = "odometroaquisicao")
	public Integer getOdometroAquisicao() {
		return odometroAquisicao;
	}

	public void setOdometroAquisicao(Integer odometroAquisicao) {
		this.odometroAquisicao = odometroAquisicao;
	}

	@Column(name = "unicodono")
	public Boolean getUnicoDono() {
		return unicoDono;
	}

	public void setUnicoDono(Boolean unicoDono) {
		this.unicoDono = unicoDono;
	}

	@Column(name = "dataquisicaoprimeirodono")
	public LocalDate getDataAquisicaoPrimeiroDono() {
		return dataAquisicaoPrimeiroDono;
	}

	public void setDataAquisicaoPrimeiroDono(LocalDate dataAquisicaoPrimeiroDono) {
		this.dataAquisicaoPrimeiroDono = dataAquisicaoPrimeiroDono;
	}

}
