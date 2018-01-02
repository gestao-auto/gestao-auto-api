package br.edu.pucpr.gestaoauto.api.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.MarcaVeiculoDTO;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaVeiculoManager;

@Path("/veiculo/marca")
public class MarcaVeiculoRest extends AbstractRest {
	
	private static Logger log = LoggerFactory.getLogger(MarcaVeiculoRest.class);

	@Inject
	MarcaVeiculoManager marcaManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista")
	public Response getListMarcaVeiculo() {
		try {
			List<MarcaVeiculoDTO> marcaList = marcaManager.convertListMarcaVeiculoToDTO(marcaManager.getListMarcaVeiculo());
			return Response.ok().entity(marcaList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
