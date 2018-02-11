package br.edu.pucpr.gestaoauto.api.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.PecaServicoDTO;
import br.edu.pucpr.gestaoauto.manager.manutencao.PecaServicoManager;

@Path("/pecaServico")
public class PecaServicoRest extends AbstractRest {
	
	private static Logger log = LoggerFactory.getLogger(PecaServicoRest.class);

	@Inject
	PecaServicoManager pecaServicoManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista/porTipoVeiculo/{tipoVeiculo}")
	public Response getListPecaServicoPorTipoVeiculo(@PathParam("tipoVeiculo") String tipoVeiculo) {
		return null;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getpecaServicos() {
		try {
			List<PecaServicoDTO> pecaServicoDTO = pecaServicoManager.convertListToDTO(pecaServicoManager.getListPecaServico());
			return Response.ok().entity(pecaServicoDTO).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
	
}
