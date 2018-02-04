package br.edu.pucpr.gestaoauto.api.dto.veiculo;

public class VeiculoCompletoDTO {

	private Integer codigo;
	private String nome;
	private ModeloDTO modelo;
	private String placa;
	private String ano;
	private String modalidade;
	private String renavam;
	private Integer odometro;
	private Integer proprietario;
	private Boolean unicoDono;
	private String dataAquisicao;
	private String dataAquisicaoPrimeiroDono;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ModeloDTO getModelo() {
		return modelo;
	}

	public void setModelo(ModeloDTO modelo) {
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

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
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

	public Boolean isUnicoDono() {
		return unicoDono;
	}

	public void setUnicoDono(Boolean unicoDono) {
		this.unicoDono = unicoDono;
	}

	public String getDataAquisicao() {
		return dataAquisicao;
	}

	public void setDataAquisicao(String dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}

	public String getDataAquisicaoPrimeiroDono() {
		return dataAquisicaoPrimeiroDono;
	}

	public void setDataAquisicaoPrimeiroDono(String dataAquisicaoPrimeiroDono) {
		this.dataAquisicaoPrimeiroDono = dataAquisicaoPrimeiroDono;
	}
	
}
