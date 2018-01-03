package br.edu.pucpr.gestaoauto.api.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pecaServico")
public class PecaServicoRest extends AbstractRest {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista/porTipoVeiculo/{tipoVeiculo}")
	public Response getListPecaServicoPorTipoVeiculo(@PathParam("tipoVeiculo") String tipoVeiculo) {
		return null;
	}

}
