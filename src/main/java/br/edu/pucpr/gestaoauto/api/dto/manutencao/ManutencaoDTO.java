package br.edu.pucpr.gestaoauto.api.dto.manutencao;

import java.util.Date;
import java.util.List;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoDTO;

public class ManutencaoDTO {

	private Integer codigo;
	private ReparadorDTO reparador;
	private VeiculoDTO veiculo;
	private Integer odometro;
	private Date data;
	private StatusDTO status;
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

	public VeiculoDTO getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(VeiculoDTO veiculo) {
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

	public StatusDTO getStatus() {
		return status;
	}

	public void setStatus(StatusDTO status) {
		this.status = status;
	}

	public List<ItemManutencaoDTO> getItemManuteido() {
		return itemManuteido;
	}

	public void setItemManuteido(List<ItemManutencaoDTO> itemManuteido) {
		this.itemManuteido = itemManuteido;
	}

}
