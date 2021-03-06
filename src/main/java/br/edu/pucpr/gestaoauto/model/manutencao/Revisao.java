package br.edu.pucpr.gestaoauto.model.manutencao;

import br.edu.pucpr.gestaoauto.model.revisao.ModeloRevisao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@DiscriminatorValue(value = "REVISAO")
public class Revisao extends Manutencao {

	private LocalDate dataPrevista;
	private Integer odometroPrevisto;
	private ModeloRevisao modeloRevisao;
	private Status status;

	@Column(name = "datprevista")
	public LocalDate getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(LocalDate dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	@Column(name = "onometroprevisto")
	public Integer getOdometroPrevisto() {
		return odometroPrevisto;
	}

	public void setOdometroPrevisto(Integer odometroPrevisto) {
		this.odometroPrevisto = odometroPrevisto;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codmodelorevisao")
	public ModeloRevisao getModeloRevisao() {
		return modeloRevisao;
	}

	public void setModeloRevisao(ModeloRevisao modeloRevisao) {
		this.modeloRevisao = modeloRevisao;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 15)
	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}