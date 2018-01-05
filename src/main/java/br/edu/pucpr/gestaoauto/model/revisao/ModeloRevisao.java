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

@Entity
@Table(name = "modelo_revisao", catalog = "gestao_auto")
public class ModeloRevisao implements java.io.Serializable {

	private static final long serialVersionUID = 1265657507301391141L;

	private Integer codigo;
	private PacoteRevisao pacote;
	private String descricao;
	private Integer tempouso;
	private Integer odometro;
	private List<ItemRevisao> itemRevisaoList = new ArrayList<>();

	public ModeloRevisao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codmodelo", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codpacrevisao", nullable = false)
	public PacoteRevisao getPacoteRevisao() {
		return this.pacote;
	}

	public void setPacoteRevisao(PacoteRevisao pacoteRevisao) {
		this.pacote = pacoteRevisao;
	}

	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "tempouso")
	public Integer getTempouso() {
		return this.tempouso;
	}

	public void setTempouso(Integer tempouso) {
		this.tempouso = tempouso;
	}

	@Column(name = "odometro")
	public Integer getOdometro() {
		return this.odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "revisao")
	public List<ItemRevisao> getItemRevisaoList() {
		return itemRevisaoList;
	}

	public void setItemRevisaoList(List<ItemRevisao> itemRevisaoList) {
		this.itemRevisaoList = itemRevisaoList;
	}
}
