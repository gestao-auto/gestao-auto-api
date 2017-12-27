package br.edu.pucpr.gestaoauto.model.manutencao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_manutencao", catalog = "gestao_auto")
public class ItemManutencao implements java.io.Serializable {

	private static final long serialVersionUID = 6860796959821214551L;

	private Integer codigo;
	private Manutencao manutencao;
	private PecaServico pecaServico;
	private String valorUnitario;
	private String quantidade;
	private String observacao;

	public ItemManutencao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "coditemmanutencao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codmanutencao", nullable = false)
	public Manutencao getManutencao() {
		return this.manutencao;
	}

	public void setManutencao(Manutencao manutencao) {
		this.manutencao = manutencao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codpecaservico", nullable = false)
	public PecaServico getPecaServico() {
		return this.pecaServico;
	}

	public void setPecaServico(PecaServico pecaServico) {
		this.pecaServico = pecaServico;
	}

	@Column(name = "valorunitario", length = 45)
	public String valorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Column(name = "quantidade", length = 45)
	public String getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "observacao", length = 45)
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}
