package br.edu.pucpr.gestaoauto.model.usuario;

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
@Table(name = "permissao_usuario", catalog = "gestao_auto")
public class PermissaoUsuario implements java.io.Serializable {

	private static final long serialVersionUID = 2570008963504521822L;

	private Integer codigo;
	private Permissao permissao;
	private Usuario usuario;

	public PermissaoUsuario() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "codpermissaousuario", unique = true, nullable = false)
	public Integer getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codpermissao", nullable = false)
	public Permissao getPermissao() {
		return this.permissao;
	}

	public void setPermissao(Permissao permissao) {
		this.permissao = permissao;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "codusuario", nullable = false)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
