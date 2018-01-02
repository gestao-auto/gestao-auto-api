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

import br.edu.pucpr.gestaoauto.api.dto.veiculo.ModeloVeiculoDTO;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaVeiculoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.ModeloVeiculoManager;
import br.edu.pucpr.gestaoauto.model.veiculo.MarcaVeiculo;

@Path("/veiculo/modelo")
public class ModeloVeiculoRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(MarcaVeiculoRest.class);

	@Inject ModeloVeiculoManager modeloManager;
	@Inject MarcaVeiculoManager marcaManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista/porMarca/{codigo}")
	public Response getListModeloVeiculoPorMarca(@PathParam("codigo") Integer codigoMarca) {
		try {
			MarcaVeiculo marca = marcaManager.getById(codigoMarca);
			List<ModeloVeiculoDTO> modeloList = modeloManager.convertListModeloToDTO(modeloManager.getListModeloVeiculoByMarca(marca));
			return Response.ok().entity(modeloList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
