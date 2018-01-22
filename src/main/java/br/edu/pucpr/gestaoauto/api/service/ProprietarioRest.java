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

import br.edu.pucpr.gestaoauto.api.dto.usuario.ProprietarioDTO;
import br.edu.pucpr.gestaoauto.manager.usuario.ProprietarioManager;
import br.edu.pucpr.gestaoauto.model.usuario.Proprietario;

@Path("/proprietario")
public class ProprietarioRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(Proprietario.class);
	
	@Inject ProprietarioManager manager;
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createProprietario(ProprietarioDTO dto) {
		try {
			Proprietario proprietario = manager.save(manager.convertDTOToEntity(dto));
			return Response.ok(manager.convertEntityToDTO(manager.getById(proprietario.getCodigo()))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateProprietario(ProprietarioDTO dto) {
		try {
			Proprietario proprietario = manager.update(manager.convertDTOToEntity(dto));
			return Response.ok(manager.convertEntityToDTO(manager.getById(proprietario.getCodigo()))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProprietario(@PathParam("codigo") Integer codigoProprietario) {
		try {
			return Response.ok(manager.convertEntityToDTO(manager.getById(codigoProprietario))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Path("/porUsuario/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProprietarioByUsuario(@PathParam("codigo") Integer codigoUsuario) {
		try {
			Proprietario proprietario = manager.getByUsuario(codigoUsuario);
			if (proprietario == null) {
				return Response.noContent().build();
			}
			return Response.ok(manager.convertEntityToDTO(proprietario)).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
