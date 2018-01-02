package br.edu.pucpr.gestaoauto.api.service;

import br.edu.pucpr.gestaoauto.api.Message;
import br.edu.pucpr.gestaoauto.api.dto.usuario.UsuarioCompletoDTO;
import br.edu.pucpr.gestaoauto.manager.UsuarioManager;

import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/user")
public class UsuarioRest extends AbstractRest {
	private static Logger log = LoggerFactory.getLogger(UsuarioRest.class);

	@Inject
	UsuarioManager service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response getById (@PathParam("id") int id){
		log.info("UsuarioRest -> get {id}");
		try {
			return Response.ok().entity(service.getDTOCompleto(service.getById(id))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUsuario (UsuarioCompletoDTO dto){
		log.info("UsuarioRest -> create");
		try {
			return Response.ok().entity(
					service.getDTO(
							service.save(
									service.getEntity(
											dto))))
							.build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario (
			@PathParam("id") int id
			, UsuarioCompletoDTO dto){
		log.info("UsuarioRest -> update");
		try {
			if(id == 0){
				return Response.status(Response.Status.NOT_FOUND).
						entity(new Message("Id vazio ou recurso n\u00E3o encontrado"
								, ValidationException.class.getName()))
						.build();
			}
			return Response.ok().entity(service.update(dto)).build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response delete (@PathParam("id") int id){
		log.info("UsuarioRest -> delete");
		try {
			service.delete(id);
			return Response.ok().entity(new Message("Sucesso"))
					.build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
}