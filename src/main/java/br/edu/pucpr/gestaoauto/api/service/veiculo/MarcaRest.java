package br.edu.pucpr.gestaoauto.api.service.veiculo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.MarcaDTO;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaManager;

@Path("/veiculo/marca")
public class MarcaRest extends AbstractRest {
	
	private static Logger log = LoggerFactory.getLogger(MarcaRest.class);

	@Inject
	MarcaManager marcaManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista")
	public Response getListMarca() {
		try {
			List<MarcaDTO> marcaList = marcaManager.convertListToDTO(marcaManager.getList());
			return Response.ok().entity(marcaList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
