package br.edu.pucpr.gestaoauto.api.dto.indicador;

public class GastoIndividualComManutencaoDTO {
	
	private Integer dias;
	private Integer registros;
	private String custoTotal;
	private String custoPorDia;
	private String custoPorQuilometragem;
	private String[] nomeItensManuteidos;
	private Double[] quantidadeItensManuteidos;

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Integer getRegistros() {
		return registros;
	}

	public void setRegistros(Integer registros) {
		this.registros = registros;
	}

	public String getCustoTotal() {
		return custoTotal;
	}

	public void setCustoTotal(String custoTotal) {
		this.custoTotal = custoTotal;
	}

	public String getCustoPorDia() {
		return custoPorDia;
	}

	public void setCustoPorDia(String custoPorDia) {
		this.custoPorDia = custoPorDia;
	}

	public String getCustoPorQuilometragem() {
		return custoPorQuilometragem;
	}

	public void setCustoPorQuilometragem(String custoPorQuilometragem) {
		this.custoPorQuilometragem = custoPorQuilometragem;
	}

	public String[] getNomeItensManuteidos() {
		return nomeItensManuteidos;
	}

	public void setNomeItensManuteidos(String[] nomeItensManuteidos) {
		this.nomeItensManuteidos = nomeItensManuteidos;
	}

	public Double[] getQuantidadeItensManuteidos() {
		return quantidadeItensManuteidos;
	}

	public void setQuantidadeItensManuteidos(Double[] quantidadeItensManuteidos) {
		this.quantidadeItensManuteidos = quantidadeItensManuteidos;
	}
}
