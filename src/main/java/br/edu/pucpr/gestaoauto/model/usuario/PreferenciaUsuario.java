package br.edu.pucpr.gestaoauto.model.usuario;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "preferencia_usuario", catalog = "gestao_auto", uniqueConstraints = @UniqueConstraint(name = "UK_PREF_USU", columnNames = {"codusuario" }))
public class PreferenciaUsuario implements Serializable {

	private static final long serialVersionUID = 3935151070976116052L;

	private Integer codigo;
	private Usuario usuario;
	private LocalTime horarioNotificacao;
	private Integer diasAntecedenciaNotificacaoRevisao;
	private Integer hodometroAntecedenciaNotificacaoRevisao;

	public PreferenciaUsuario() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "codpreferenciausuario", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "horarionoficacao", length = 8)
	public LocalTime getHorarioNoficacao() {
		return this.horarioNotificacao;
	}

	public void setHorarioNoficacao(LocalTime horarioNotificacao) {
		this.horarioNotificacao = horarioNotificacao;
	}

	@Column(name = "diasantnotficarevisao")
	public Integer getDiasAntecedenciaNotificacaoRevisao() {
		return this.diasAntecedenciaNotificacaoRevisao;
	}

	public void setDiasAntecedenciaNotificacaoRevisao(Integer diasAntecedenciaNotificacaoRevisao) {
		this.diasAntecedenciaNotificacaoRevisao = diasAntecedenciaNotificacaoRevisao;
	}

	@Column(name = "hodometroantnotificarevisao")
	public Integer getHodometroAntecedenciaNotificacaoRevisao() {
		return this.hodometroAntecedenciaNotificacaoRevisao;
	}

	public void setHodometroAntecedenciaNotificacaoRevisao(Integer hodometroAntecedenciaNotificacaoRevisao) {
		this.hodometroAntecedenciaNotificacaoRevisao = hodometroAntecedenciaNotificacaoRevisao;
	}
}
