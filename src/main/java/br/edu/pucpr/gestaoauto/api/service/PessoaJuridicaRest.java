package br.edu.pucpr.gestaoauto.api.service;

import br.edu.pucpr.gestaoauto.api.dto.pessoaJuridica.PessoaJuridicaDTO;
import br.edu.pucpr.gestaoauto.manager.PessoaJuridicaManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pessoaJuridica")
public class PessoaJuridicaRest extends AbstractRest {
	
	private static Logger log = LoggerFactory.getLogger(PessoaJuridicaRest.class);

	@Inject
	PessoaJuridicaManager pessoaJuridicaManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getPessoasJuridicas() {
		try {
			List<PessoaJuridicaDTO> pessoasJuridicasDTO = pessoaJuridicaManager.convertListToDTO(
					pessoaJuridicaManager.getListPessoaJuridica());
			return Response.ok().entity(pessoasJuridicasDTO).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
