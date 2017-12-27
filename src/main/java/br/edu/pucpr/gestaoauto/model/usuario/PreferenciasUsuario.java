package br.edu.pucpr.gestaoauto.model.usuario;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "preferencias_usuario", catalog = "gestao_auto")
public class PreferenciasUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 3935151070976116052L;

	private Integer codigo;
	private Usuario usuario;
	private Date horarioNotificacao;
	private Integer diasAntecedenciaNotificacaoRevisao;
	private Integer hodometroAntecedenciaNotificacaoRevisao;

	public PreferenciasUsuario() {
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horarionoficacao", length = 8)
	public Date getHorarioNoficacao() {
		return this.horarioNotificacao;
	}

	public void setHorarioNoficacao(Date horarioNotificacao) {
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
