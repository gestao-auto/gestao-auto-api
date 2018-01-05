package br.edu.pucpr.gestaoauto.model.revisao;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.edu.pucpr.gestaoauto.model.veiculo.Modelo;

@Entity
@Table(name = "pacote_revisao", catalog = "gestao_auto")
public class PacoteRevisao implements java.io.Serializable {

	private static final long serialVersionUID = 6660095982718297373L;

	private Integer codigo;
	private Modelo modeloVeiculo;
	private String nome;
	private Integer ano;
	private List<ModeloRevisao> modeloRevisaoList = new ArrayList<>();

	public PacoteRevisao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codpacrevisao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codpacrevisao) {
		this.codigo = codpacrevisao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codmodeloveiculo", nullable = false)
	public Modelo getModeloVeiculo() {
		return this.modeloVeiculo;
	}

	public void setModeloVeiculo(Modelo modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ano")
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pacoteRevisao")
	public List<ModeloRevisao> getModeloRevisaoList() {
		return this.modeloRevisaoList;
	}

	public void setModeloRevisaoList(List<ModeloRevisao> modeloRevisaoList) {
		this.modeloRevisaoList = modeloRevisaoList;
	}
}
