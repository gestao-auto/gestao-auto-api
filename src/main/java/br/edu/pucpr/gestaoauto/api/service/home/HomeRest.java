package br.edu.pucpr.gestaoauto.api.service.home;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import br.edu.pucpr.gestaoauto.api.dto.home.HomeManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import br.edu.pucpr.gestaoauto.manager.home.HomeManager;

@Path("/home")
public class HomeRest extends AbstractRest {

	@Inject
	HomeManager manager;

	@GET
	@Path("/manutencao/{veiculo}")
	public Response getProximaRevisao(@PathParam("veiculo") Integer codigoVeiculo) {
		HomeManutencaoDTO dto = manager.getProximaRevisaoByVeiculo(codigoVeiculo);
		if (dto == null) {
			return Response.noContent().build();
		}
		return Response.ok(dto).build();
	}
}
