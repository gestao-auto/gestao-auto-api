package br.edu.pucpr.gestaoauto.api.dto.veiculo;

public class ModeloVeiculoDTO {

	private Integer codigo;
	private String nome;
	private MarcaVeiculoDTO marca;
	private Integer ano;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public MarcaVeiculoDTO getMarca() {
		return marca;
	}

	public void setMarca(MarcaVeiculoDTO marca) {
		this.marca = marca;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}
