package br.edu.pucpr.gestaoauto.api.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.usuario.PreferenciaDTO;
import br.edu.pucpr.gestaoauto.manager.usuario.PreferenciaManager;
import br.edu.pucpr.gestaoauto.model.usuario.Preferencia;

@Path("/preferencia")
public class PreferenciaRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(PreferenciaRest.class);

	@Inject PreferenciaManager manager;
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(PreferenciaDTO dto) {
		try {
			return Response.ok(manager.convertEntityToDTO(manager.save(manager.convertDTOToEntity(dto)))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(PreferenciaDTO dto) {
		try {
			return Response.ok(manager.convertEntityToDTO(manager.update(manager.convertDTOToEntity(dto)))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Path("/porUsuario/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getByUsuario(@PathParam("codigo") Integer codigoUsuario) {
		try {
			Preferencia preferenciaUsuario = manager.getByUsuario(codigoUsuario);
			if (preferenciaUsuario != null) {
				return Response.ok(manager.convertEntityToDTO(preferenciaUsuario)).build();
			} else {
				return Response.noContent().build();
			}
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
