package br.edu.pucpr.gestaoauto.api.dto.manutencao;

import java.util.Date;
import java.util.List;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoCompletoDTO;

public class ManutencaoDTO {

	private Integer codigo;
	private ReparadorDTO reparador;
	private VeiculoCompletoDTO veiculo;
	private Integer odometro;
	private Date data;
	private String status;
	private Integer tempoUsoPrevisto;
	private Integer odometroPrevisto;
	private List<ItemManutencaoDTO> itemManuteido;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ReparadorDTO getReparador() {
		return reparador;
	}

	public void setReparador(ReparadorDTO reparador) {
		this.reparador = reparador;
	}

	public VeiculoCompletoDTO getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoCompletoDTO veiculo) {
		this.veiculo = veiculo;
	}

	public Integer getOdometro() {
		return odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ItemManutencaoDTO> getItemManuteido() {
		return itemManuteido;
	}

	public void setItemManuteido(List<ItemManutencaoDTO> itemManuteido) {
		this.itemManuteido = itemManuteido;
	}

	public Integer getTempoUsoPrevisto() {
		return tempoUsoPrevisto;
	}

	public void setTempoUsoPrevisto(Integer tempoUsoPrevisto) {
		this.tempoUsoPrevisto = tempoUsoPrevisto;
	}

	public Integer getOdometroPrevisto() {
		return odometroPrevisto;
	}

	public void setOdometroPrevisto(Integer odometroPrevisto) {
		this.odometroPrevisto = odometroPrevisto;
	}
}
