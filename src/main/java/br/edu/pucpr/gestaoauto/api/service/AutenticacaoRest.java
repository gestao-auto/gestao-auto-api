package br.edu.pucpr.gestaoauto.api.service;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.Message;
import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioCompletoDTO;
import br.edu.pucpr.gestaoauto.manager.usuario.UsuarioManager;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;
import br.edu.pucpr.gestaoauto.seguranca.REQUEST_Autenticacao;
import br.edu.pucpr.gestaoauto.seguranca.TokenHandler;

@Path("/auth")
public class AutenticacaoRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(AutenticacaoRest.class);

	@Inject
	private UsuarioManager manager;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response createUsuario(UsuarioCompletoDTO dto) {
		log.info("AutenticacaoRest -> create");
		Status status = Status.INTERNAL_SERVER_ERROR;
		String token = null;
		try {
			Usuario usuario = manager.save(manager.convertUsuarioCompletoDTOToEntity(dto));
			
			token = TokenHandler.getToken(usuario.getCodigo().toString(), usuario.getEmail());

			return Response.status(Status.OK).entity(token).build();
		} catch (Exception e) {
			log.error(e.toString());
			return Response.status(status).entity(e.getMessage()).build();
		}
	}

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(REQUEST_Autenticacao credenciais) {
		log.info("AutenticacaoRest -> login");
		String token = null;
		Status status = Status.INTERNAL_SERVER_ERROR;// Prepara a requisição para um erro inesperado

		try {
			Usuario usuario = manager.validaAcesso(credenciais);

			if (usuario == null) {
				return Response.status(Status.UNAUTHORIZED).entity(
						new Message("Usu\u00E1rio ou senha inv\u00E1lidos", ValidationException.class.getName()))
						.build();
			}

			token = TokenHandler.getToken(usuario.getCodigo().toString(), credenciais.getUsuario());

			return Response.status(Status.OK).entity(token).build();
		} catch (Exception e) {
			log.error(e.toString());
			return Response.status(status).entity(e.getMessage()).build();
		}
	}
}
