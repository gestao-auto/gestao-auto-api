package br.edu.pucpr.gestaoauto.api.service.veiculo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.ModeloDTO;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.ModeloManager;
import br.edu.pucpr.gestaoauto.model.veiculo.Marca;

@Path("/veiculo/modelo")
public class ModeloRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(ModeloRest.class);

	@Inject
    ModeloManager modeloManager;
	@Inject
	MarcaManager marcaManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista/porMarca/{codigo}")
	public Response getListModeloPorMarca(@PathParam("codigo") Integer codigoMarca) {
		try {
			Marca marca = marcaManager.getById(codigoMarca);
			List<ModeloDTO> modeloList = modeloManager.convertListToDTO(modeloManager.getListByMarca(marca));
			return Response.ok().entity(modeloList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
