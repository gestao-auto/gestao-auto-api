package br.edu.pucpr.gestaoauto.model.notificacao;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

@Entity
@Table(name = "notificacao", catalog = "gestao_auto")
public class Notificacao implements java.io.Serializable {

	private static final long serialVersionUID = 124825933065413328L;

	private Integer codigo;
    private TipoNotificacao tipoNotificacao;
    private String mensagem;
    private StatusNotificacao status;
    private Usuario usuario;

    public Notificacao() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "cod_notificacao", unique = true, nullable = false)
    public Integer getCodigo() {
            return this.codigo;
        }

    public void setCodigo(Integer codigo) {
            this.codigo = codigo;
        }

    @Column(name = "tipo_notificacao")
    public TipoNotificacao getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(TipoNotificacao tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    @Column(name = "mensagem", length = 400)
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Column(name = "status")
    public StatusNotificacao getStatus() {
        return status;
    }

    public void setStatus(StatusNotificacao status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codusuario", nullable = false)
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}