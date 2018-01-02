package br.edu.pucpr.gestaoauto.api.dto.veiculo;

import br.edu.pucpr.gestaoauto.api.dto.usuario.ProprietarioDTO;

public class VeiculoDTO {

	private Integer codigo;
	private String nome;
	private ModeloVeiculoDTO modelo;
	private String placa;
	private Integer ano;
	private String modalidade;
	private String renavam;
	private Integer odometro;
	private ProprietarioDTO proprietario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ModeloVeiculoDTO getModelo() {
		return modelo;
	}

	public void setModelo(ModeloVeiculoDTO modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public Integer getOdometro() {
		return odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	public ProprietarioDTO getProprietario() {
		return proprietario;
	}

	public void setProprietario(ProprietarioDTO proprietario) {
		this.proprietario = proprietario;
	}
	
	
}
