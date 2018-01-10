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

import br.edu.pucpr.gestaoauto.api.dto.usuario.PreferenciaUsuarioDTO;
import br.edu.pucpr.gestaoauto.manager.usuario.PreferenciaUsuarioManager;
import br.edu.pucpr.gestaoauto.model.usuario.PreferenciaUsuario;

@Path("/preferenciaUsuario")
public class PreferenciaUsuarioRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(PreferenciaUsuarioRest.class);

	@Inject PreferenciaUsuarioManager manager;
	
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(PreferenciaUsuarioDTO dto) {
		try {
			return Response.ok(manager.convertPreferenciaUsuarioToDTO(manager.save(manager.convertPreferenciaUsuarioDTOToEntity(dto)))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(PreferenciaUsuarioDTO dto) {
		try {
			return Response.ok(manager.convertPreferenciaUsuarioToDTO(manager.update(manager.convertPreferenciaUsuarioDTOToEntity(dto)))).build();
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
			PreferenciaUsuario preferenciaUsuario = manager.getById(codigoUsuario);
			if (preferenciaUsuario != null) {
				return Response.ok(manager.convertPreferenciaUsuarioToDTO(preferenciaUsuario)).build();
			} else {
				return Response.noContent().build();
			}
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
