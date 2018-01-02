package br.edu.pucpr.gestaoauto.model.manutencao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Reparador;
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

	public Manutencao() {
	}

	@Id
	@Column(name = "codmanutencao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codreparador", nullable = false)
	public Reparador getReparador() {
		return this.reparador;
	}

	public void setReparador(Reparador reparador) {
		this.reparador = reparador;
	}

	@ManyToOne(fetch = FetchType.LAZY)
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

}