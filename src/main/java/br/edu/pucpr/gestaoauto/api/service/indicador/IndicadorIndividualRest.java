package br.edu.pucpr.gestaoauto.api.service.indicador;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.indicador.GastoIndividualComManutencaoDTO;
import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import br.edu.pucpr.gestaoauto.manager.indicador.IndicadorIndividualManager;

@Path("/indicador/individual")
public class IndicadorIndividualRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(IndicadorIndividualRest.class);

	@Inject
	IndicadorIndividualManager manager;

	@GET
	@Path("/manutencao/{veiculo}/{dataInicial}/{dataFinal}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGastosComManutencao(@PathParam("veiculo") Integer codigoVeiculo, @PathParam("dataInicial") String dataInicial,
			@PathParam("dataFinal") String dataFinal) {
		try {
			GastoIndividualComManutencaoDTO result = manager.getGastosComManutencaoByVeiculo(dataInicial, dataFinal, codigoVeiculo);
			if (result == null) {
				return Response.noContent().build();
			}
			return Response.ok().entity(result).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}