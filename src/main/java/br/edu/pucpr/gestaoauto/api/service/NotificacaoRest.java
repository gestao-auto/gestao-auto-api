package br.edu.pucpr.gestaoauto.api.service;

import br.edu.pucpr.gestaoauto.api.dto.notificacao.NotificacaoDTOFull;
import br.edu.pucpr.gestaoauto.model.notificacao.Notificacao;
import br.edu.pucpr.gestaoauto.manager.NotificacaoManager;
import br.edu.pucpr.gestaoauto.model.notificacao.StatusNotificacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/notificacao")
public class NotificacaoRest extends AbstractRest {

    private static Logger log = LoggerFactory.getLogger(PreferenciaUsuarioRest.class);

    @Inject
    NotificacaoManager manager;

    @GET
    @Path("/ler/usuario/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response lerNotificacoesPendentes(@PathParam("codigo") Integer codigoUsuario) {
        try {
            List<Notificacao> notificacoes = manager.lerNotificacoesPendentes(codigoUsuario);
            if (notificacoes != null && notificacoes.size() > 0) {
                return Response.ok(manager.convertToDTO(notificacoes)).build();
            } else {
                return Response.noContent().build();
            }
        } catch (Exception e) {
            log.error(e.toString());
            return super.serverError(e);
        }
    }
}