package br.edu.pucpr.gestaoauto.api.dto.home;

import br.edu.pucpr.gestaoauto.model.manutencao.Status;

public class HomeManutencaoDTO {

	private Integer codigo;
	private String descricao;
	private Integer codigoVeiculo;
	private Status status;
	private Integer quilometragemPrevista;
	private Integer tempoUsoPrevisto;
	private Integer diasRestantes;
	private Integer quilometrosRestantes;
	private String valorMedio;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getCodigoVeiculo() {
		return codigoVeiculo;
	}
	public void setCodigoVeiculo(Integer codigoVeiculo) {
		this.codigoVeiculo = codigoVeiculo;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getQuilometragemPrevista() {
		return quilometragemPrevista;
	}

	public void setQuilometragemPrevista(Integer quilometragemPrevista) {
		this.quilometragemPrevista = quilometragemPrevista;
	}

	public Integer getTempoUsoPrevisto() {
		return tempoUsoPrevisto;
	}

	public void setTempoUsoPrevisto(Integer tempoUsoPrevisto) {
		this.tempoUsoPrevisto = tempoUsoPrevisto;
	}
	public Integer getDiasRestantes() {
		return diasRestantes;
	}
	public void setDiasRestantes(Integer diasRestantes) {
		this.diasRestantes = diasRestantes;
	}
	public Integer getQuilometrosRestantes() {
		return quilometrosRestantes;
	}
	public void setQuilometrosRestantes(Integer quilometrosRestantes) {
		this.quilometrosRestantes = quilometrosRestantes;
	}
	public String getValorMedio() {
		return valorMedio;
	}
	public void setValorMedio(String valorMedio) {
		this.valorMedio = valorMedio;
	}
}
