package br.edu.pucpr.gestaoauto.api.service;

import br.edu.pucpr.gestaoauto.api.dto.pessoaJuridica.PessoaJuridicaDTO;
import br.edu.pucpr.gestaoauto.manager.PessoaJuridicaManager;
import br.edu.pucpr.gestaoauto.model.pessoaJuridica.PessoaJuridica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/reparador/{nome}")
	public Response getReparadores(@PathParam("nome") String nome) {
		try {
			List<PessoaJuridicaDTO> pessoasJuridicasDTO = pessoaJuridicaManager.convertListToDTO(
					pessoaJuridicaManager.getListPessoaJuridica(nome, PessoaJuridica.REPARADOR));
			return Response.ok().entity(pessoasJuridicasDTO).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/seguradora/{nome}")
	public Response getSeguradoras(@PathParam("nome") String nome) {
		try {
			List<PessoaJuridicaDTO> pessoasJuridicasDTO = pessoaJuridicaManager.convertListToDTO(
					pessoaJuridicaManager.getListPessoaJuridica(nome, PessoaJuridica.SEGURADORA));
			return Response.ok().entity(pessoasJuridicasDTO).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/postocombustivel/{nome}")
	public Response getPostosCombustivel(@PathParam("nome") String nome) {
		try {
			List<PessoaJuridicaDTO> pessoasJuridicasDTO = pessoaJuridicaManager.convertListToDTO(
					pessoaJuridicaManager.getListPessoaJuridica(nome, PessoaJuridica.POSTO_COMBUSTIVEL));
			return Response.ok().entity(pessoasJuridicasDTO).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}
}
