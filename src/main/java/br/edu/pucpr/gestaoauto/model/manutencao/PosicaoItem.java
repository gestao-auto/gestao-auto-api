package br.edu.pucpr.gestaoauto.model.manutencao;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posicao_item", catalog = "gestao_auto")
public class PosicaoItem implements Serializable {
	
	private static final long serialVersionUID = -8952964930697250618L;
	private Integer codigo;
	private String posicao;
	private ItemManutencao itemManutencao;
	
	public PosicaoItem() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codposicaoitem", unique = true, nullable = false)
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@Column(name = "observacao", length = 45)
	public String getPosicao() {
		return posicao;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coditemmanutencao", nullable = false)
	public ItemManutencao getItemManutencao() {
		return itemManutencao;
	}

	public void setItemManutencao(ItemManutencao itemManutencao) {
		this.itemManutencao = itemManutencao;
	}
	
	

}
