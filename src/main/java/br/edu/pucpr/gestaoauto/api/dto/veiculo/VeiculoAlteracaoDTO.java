package br.edu.pucpr.gestaoauto.api.dto.veiculo;

public class VeiculoAlteracaoDTO {

	private Integer codigo;
	private String nome;
	private Integer modelo;
	private String placa;
	private Integer ano;
	private String modalidade;
	private String renavam;
	private Integer odometro;
	private Integer proprietario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
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

	public Integer getProprietario() {
		return proprietario;
	}

	public void setProprietario(Integer proprietario) {
		this.proprietario = proprietario;
	}
	
	
}
