package br.edu.pucpr.gestaoauto.api.dto.manutencao;

import java.util.List;

public class ManutencaoDTO {

	private TipoManutencaoDTO tipoManutencao;
	private Integer codigo;
	private String descricao;
	private Integer codigoReparador;
	private Integer codigoSeguradora;
	private Integer codigoVeiculo;
	private Integer odometro;
	private String data;
	private String status;
	private String dataPrevista;
	private Integer odometroPrevisto;
	private String motivo;
	private List<ItemManutencaoDTO> itemManutencaoList;

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

	public Integer getCodigoReparador() {
		return codigoReparador;
	}

	public void setCodigoReparador(Integer codigoReparador) {
		this.codigoReparador = codigoReparador;
	}

	public Integer getCodigoSeguradora() {
		return codigoSeguradora;
	}

	public void setCodigoSeguradora(Integer codigoSeguradora) {
		this.codigoSeguradora = codigoSeguradora;
	}

	public Integer getCodigoVeiculo() {
		return codigoVeiculo;
	}

	public void setCodigoVeiculo(Integer codigoVeiculo) {
		this.codigoVeiculo = codigoVeiculo;
	}

	public Integer getOdometro() {
		return odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(String dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Integer getOdometroPrevisto() {
		return odometroPrevisto;
	}

	public void setOdometroPrevisto(Integer odometroPrevisto) {
		this.odometroPrevisto = odometroPrevisto;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public List<ItemManutencaoDTO> getItemManutencaoList() {
		return itemManutencaoList;
	}

	public void setItemManutencaoList(List<ItemManutencaoDTO> itemManutencaoList) {
		this.itemManutencaoList = itemManutencaoList;
	}

	public TipoManutencaoDTO getTipoManutencao() {
		return tipoManutencao;
	}

	public void setTipoManutencao(TipoManutencaoDTO tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}

}
