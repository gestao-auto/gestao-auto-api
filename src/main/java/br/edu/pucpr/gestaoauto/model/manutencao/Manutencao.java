package br.edu.pucpr.gestaoauto.model.manutencao;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Entity
@Table(name = "manutencao", catalog = "gestao_auto")
public class Manutencao implements java.io.Serializable {

	private static final long serialVersionUID = 5827011733126840023L;
	
	private Integer codigo;
	private Reparador reparador;
	private Veiculo veiculo;
	private int odometro;
	private Date data;
	private String status;
	private Integer odometroPrevisto;
	private Integer tempoUsoPrevisto;
	private List<ItemManutencao> itemManutencao = new ArrayList<>();

	public Manutencao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codmanutencao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codreparador")
	public Reparador getReparador() {
		return this.reparador;
	}

	public void setReparador(Reparador reparador) {
		this.reparador = reparador;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codveiculo", nullable = false)
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Column(name = "odometro", nullable = false)
	public int getOdometro() {
		return this.odometro;
	}

	public void setOdometro(int odometro) {
		this.odometro = odometro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "data", length = 10)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(name = "status", length = 15)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "onometroprevisto")
	public Integer getOdometroPrevisto() {
		return odometroPrevisto;
	}

	public void setOdometroPrevisto(Integer odometroPrevisto) {
		this.odometroPrevisto = odometroPrevisto;
	}

	@Column(name = "tempousoprevisto")
	public Integer getTempoUsoPrevisto() {
		return tempoUsoPrevisto;
	}

	public void setTempoUsoPrevisto(Integer tempoUsoPrevisto) {
		this.tempoUsoPrevisto = tempoUsoPrevisto;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "manutencao")
	public List<ItemManutencao> getItemManutencao() {
		return itemManutencao;
	}

	public void setItemManutencao(List<ItemManutencao> itemManutencao) {
		this.itemManutencao = itemManutencao;
	}


}