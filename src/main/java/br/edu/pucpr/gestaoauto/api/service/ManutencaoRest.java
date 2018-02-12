package br.edu.pucpr.gestaoauto.api.service;

import br.edu.pucpr.gestaoauto.api.dto.manutencao.ManutencaoDTO;
import br.edu.pucpr.gestaoauto.manager.manutencao.ItemManutencaoManager;
import br.edu.pucpr.gestaoauto.manager.manutencao.ManutencaoManager;
import br.edu.pucpr.gestaoauto.model.manutencao.Manutencao;
import br.edu.pucpr.gestaoauto.util.ObjetoNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@Path("/manutencao")
public class ManutencaoRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(ManutencaoRest.class);
	
	@Inject ManutencaoManager manutencaoManager;
	@Inject ItemManutencaoManager itemManutencaoManager;
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createManutencao(ManutencaoDTO dto) {
		try {
			Manutencao manutencao = manutencaoManager.save(manutencaoManager.convertDTOToEntity(dto));
			return Response.ok(manutencaoManager.convertEntityToDTO(manutencaoManager.getById(manutencao.getCodigo()))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@PUT
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateManutencao(ManutencaoDTO dto) {
		try {
			Manutencao manutencao = manutencaoManager.update(manutencaoManager.convertDTOToEntity(dto));
			return Response.ok(manutencaoManager.convertEntityToDTO(manutencaoManager.getById(manutencao.getCodigo()))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@DELETE
	@Path("/{codigo}")
	public Response deleteManutencao(@PathParam("codigo") Integer codigoManutencao) {
		try {
			manutencaoManager.delete(codigoManutencao);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Path("/porVeiculo/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListManutencaoByVeiculo(@PathParam("codigo") Integer codigoVeiculo) {
		try {
			List<Manutencao> manutencaoList = manutencaoManager.getListManutencaoByVeiculo(codigoVeiculo);
			if (manutencaoList.isEmpty()) {
				manutencaoManager.carregarPacoteRevisaoParaManutencao(codigoVeiculo);
				manutencaoList.addAll(manutencaoManager.getListManutencaoByVeiculo(codigoVeiculo));
			}
			return Response.ok(manutencaoManager.convertListToDTO(manutencaoManager.getListManutencaoByVeiculo(codigoVeiculo))).build();
		} catch (ObjetoNaoEncontradoException e) {
			return Response.status(Status.NO_CONTENT).entity(e.getMessage()).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Path("/{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getManutencao(@PathParam("codigo") Integer codigoManutencao) {
		try {
			return Response.ok(manutencaoManager.convertEntityToDTO(manutencaoManager.getById(codigoManutencao))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@DELETE
	@Path("/item/{codigo}")
	public Response deleteItemManuencao(@PathParam("codigo") Integer codigoItemManutencao) {
		try {
			itemManutencaoManager.delete(codigoItemManutencao);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
