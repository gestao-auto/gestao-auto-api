package br.edu.pucpr.gestaoauto.model.revisao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.pucpr.gestaoauto.model.manutencao.PecaServico;

@Entity
@Table(name = "item_revisao", catalog = "gestao_auto")
public class ItemRevisao implements java.io.Serializable {

	private static final long serialVersionUID = 8900393981450077302L;

	private Integer codigo;
	private ModeloRevisao revisao;
	private PecaServico pecaServico;
	private Float quantidade;

	public ItemRevisao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "coditemrevisao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codpecaservico", nullable = false)
	public PecaServico getPecaServico() {
		return this.pecaServico;
	}

	public void setPecaServico(PecaServico pecaServico) {
		this.pecaServico = pecaServico;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codrevisao", nullable = false)
	public ModeloRevisao getRevisao() {
		return this.revisao;
	}

	public void setRevisao(ModeloRevisao revisao) {
		this.revisao = revisao;
	}

	@Column(name = "quantidade")
	public Float getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}
}
