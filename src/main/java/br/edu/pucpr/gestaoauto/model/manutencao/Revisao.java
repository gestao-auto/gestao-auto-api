package br.edu.pucpr.gestaoauto.model.manutencao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.pucpr.gestaoauto.model.revisao.PacoteRevisao;

@Entity
@DiscriminatorValue(value = "REVISAO")
public class Revisao extends Manutencao {

	private Date dataPrevista;
	private Integer odometroPrevisto;
	private PacoteRevisao pacote;
	private Status status;

	@Temporal(TemporalType.DATE)
	@Column(name = "datprevista")
	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	@Column(name = "onometroprevisto")
	public Integer getOdometroPrevisto() {
		return odometroPrevisto;
	}

	public void setOdometroPrevisto(Integer odometroPrevisto) {
		this.odometroPrevisto = odometroPrevisto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codpacrevisao")
	public PacoteRevisao getPacote() {
		return pacote;
	}

	public void setPacote(PacoteRevisao pacote) {
		this.pacote = pacote;
	}

	@Column(name = "status", length = 15)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
