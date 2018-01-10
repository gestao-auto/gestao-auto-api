package br.edu.pucpr.gestaoauto.api.dto.notificacao;

import br.edu.pucpr.gestaoauto.api.dto.notificacao.NotificacaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioDTO;
import br.edu.pucpr.gestaoauto.model.notificacao.StatusNotificacao;

public class NotificacaoDTOFull extends NotificacaoDTO{
    private StatusNotificacao status;

    public StatusNotificacao getStatus() {
        return status;
    }

    public void setStatus(StatusNotificacao status) {
        this.status = status;
    }
}
