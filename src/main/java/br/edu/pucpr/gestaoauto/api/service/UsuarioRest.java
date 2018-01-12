package br.edu.pucpr.gestaoauto.api.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioCompletoDTO;
import br.edu.pucpr.gestaoauto.manager.usuario.UsuarioManager;

@Path("/usuario")
public class UsuarioRest extends AbstractRest {
	private static Logger log = LoggerFactory.getLogger(UsuarioRest.class);

	@Inject
	UsuarioManager manager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Response getById(@PathParam("codigo") Integer codigo) {
		log.info("UsuarioRest -> get {codigo}");
		try {
			return Response.ok().entity(manager.convertUsuarioToDTOCompleto(manager.getById(codigo))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@PUT
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(UsuarioCompletoDTO dto) {
		log.info("UsuarioRest -> update");
		try {
			return Response.ok().entity(manager.update(manager.convertUsuarioCompletoDTOToEntity(dto))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Response deleteUsuario(@PathParam("codigo") Integer codigo) {
		log.info("UsuarioRest -> delete {codigo}");
		try {
			manager.delete(codigo);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
}