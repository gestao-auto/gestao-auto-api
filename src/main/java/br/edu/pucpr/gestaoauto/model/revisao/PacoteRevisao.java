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

import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;

@Entity
@Table(name = "pacote_revisao", catalog = "gestao_auto")
public class PacoteRevisao implements java.io.Serializable {

	private static final long serialVersionUID = 6660095982718297373L;

	private Integer codigo;
	private MarcaVeiculo marcaVeiculo;
	private String nome;

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
	@JoinColumn(name = "codmarca", nullable = false)
	public MarcaVeiculo getMarcaVeiculo() {
		return this.marcaVeiculo;
	}

	public void setMarcaVeiculo(MarcaVeiculo marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}

	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}
