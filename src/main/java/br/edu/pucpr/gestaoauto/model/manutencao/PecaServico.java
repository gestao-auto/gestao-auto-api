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

import br.edu.pucpr.gestaoauto.model.veiculo.Modalidade;


@Entity
@Table(name = "peca_servico", catalog = "gestao_auto")
public class PecaServico implements Serializable {

	private static final long serialVersionUID = -256078620631633338L;
	private Integer codigo;
	private String tipo;
	private String nome;
	private Modalidade modalidade;

	public PecaServico() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codpecaservico", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codpecaservico) {
		this.codigo = codpecaservico;
	}

	@Column(name = "tipo", nullable = false, length = 10)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "nome", nullable = false, length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codmodalidadeveiculo", nullable = false)
	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

}
