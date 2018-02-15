package br.edu.pucpr.gestaoauto.model.manutencao;

import br.edu.pucpr.gestaoauto.model.pessoaJuridica.Reparador;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipmanutencao", discriminatorType = DiscriminatorType.STRING)
@Table(name = "manutencao", catalog = "gestao_auto")
public abstract class Manutencao {

	private Integer codigo;
	private String descricao;
	private Reparador reparador;
	private Veiculo veiculo;
	private Integer odometro;
	private LocalDate data;
	private List<ItemManutencao> itemManutencao = new ArrayList<>();

	public Manutencao() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codmanutencao", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codreparador")
	public Reparador getReparador() {
		return this.reparador;
	}

	public void setReparador(Reparador reparador) {
		this.reparador = reparador;
	}

	@Column(name = "descricao", length = 45)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codveiculo", nullable = false)
	public Veiculo getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	@Column(name = "odometro")
	public Integer getOdometro() {
		return this.odometro;
	}

	public void setOdometro(Integer odometro) {
		this.odometro = odometro;
	}

	@Column(name = "data", length = 10)
	public LocalDate getData() {
		return this.data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "manutencao", cascade = CascadeType.REMOVE,orphanRemoval = true)
	public List<ItemManutencao> getItemManutencao() {
		return itemManutencao;
	}

	public void setItemManutencao(List<ItemManutencao> itemManutencao) {
		this.itemManutencao = itemManutencao;
	}
}
