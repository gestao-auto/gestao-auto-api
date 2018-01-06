package br.edu.pucpr.gestaoauto.api.service.veiculo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoAlteracaoDTO;
import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoCompletoDTO;
import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import br.edu.pucpr.gestaoauto.manager.UsuarioManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;

@Path("/veiculo")
public class VeiculoRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(VeiculoRest.class);

	@Inject VeiculoManager veiculoManager;
	@Inject UsuarioManager usuarioManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Response getVeiculo(@PathParam("codigo") Integer codigoVeiculo) {
        log.info("VeiculoRest -> getVeiculo");
		try {
			VeiculoCompletoDTO dto = veiculoManager.convertVeiculoToDTO(veiculoManager.getById(codigoVeiculo));
			return Response.ok().entity(dto).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/porUsuario/{codigo}")
	public Response getListVeiculoPorUsuario(@PathParam("codigo") Integer codigoUsuario) {
        log.info("VeiculoRest -> getListVeiculoPorUsuario");
		try {
			Usuario usuario = usuarioManager.getById(codigoUsuario);
			List<VeiculoCompletoDTO> veiculoList = veiculoManager.convertListToDTO(veiculoManager.getListByUsuario(usuario));
			return Response.ok().entity(veiculoList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(VeiculoAlteracaoDTO dto) {
		log.info("VeiculoRest -> create");
		try {
			return Response.ok().entity(veiculoManager.convertVeiculoToDTO(
			        veiculoManager.save(
			                veiculoManager.convertDTOToEntity(dto)
            ))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@PUT
	@Path("/{codigo}/odometro/{odometro}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOdometro(@PathParam("codigo") Integer codigoVeiculo, @PathParam("odometro") Integer odometro) {
        log.info("VeiculoRest -> updateOdometro");
        try {
			veiculoManager.updateOdometro(codigoVeiculo, odometro);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@PUT
	@Path("/{codigo}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("codigo") Integer codigoVeiculo, VeiculoAlteracaoDTO dto) {
        log.info("VeiculoRest -> update");
		try {
            dto.setCodigo(codigoVeiculo);
			return Response.ok().entity(
                    veiculoManager.convertVeiculoToDTO(
                            veiculoManager.update(
                                    veiculoManager.convertDTOToEntity(dto)
            ))).build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Response delete(@PathParam("codigo") Integer codigoVeiculo) {
        log.info("VeiculoRest -> delete");
        try {
			veiculoManager.delete(codigoVeiculo);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
}