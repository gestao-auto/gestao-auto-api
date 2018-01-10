package br.edu.pucpr.gestaoauto.model.notificacao;

import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "notificacao", catalog = "gestao_auto")
public class Notificacao implements java.io.Serializable {
    private Integer codigo;
    private String titulo;
    private String mensagem;
    private StatusNotificacao status;
    private Usuario usuario;

    public Notificacao() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "codnotificacao", unique = true, nullable = false)
    public Integer getCodigo() {
            return this.codigo;
        }

    public void setCodigo(Integer codigo) {
            this.codigo = codigo;
        }

    @Column(name = "titulo", length = 120)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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