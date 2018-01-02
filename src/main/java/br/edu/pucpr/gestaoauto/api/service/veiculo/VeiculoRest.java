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

import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoDTO;
import br.edu.pucpr.gestaoauto.manager.UsuarioManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Path("/veiculo")
public class VeiculoRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(VeiculoRest.class);

	@Inject VeiculoManager veiculoManager;
	@Inject UsuarioManager usuarioManager;
	@Inject	MarcaManager marcaManager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Response getVeiculo(@PathParam("codigo") Integer codigoVeiculo) {
		try {
			VeiculoDTO dto = veiculoManager.convertVeiculoToDTO(veiculoManager.getById(codigoVeiculo));
			return Response.ok().entity(dto).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/lista/porUsuario/{codigo}")
	public Response getListVeiculoPorUsuario(@PathParam("codigo") Integer codigoUsuario) {
		try {
			Usuario usuario = usuarioManager.getById(codigoUsuario);
			List<VeiculoDTO> veiculoList = veiculoManager.convertListVeiculoToDTO(veiculoManager.getListVeiculoByUsuario(usuario));
			return Response.ok().entity(veiculoList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createVeiculo(VeiculoDTO dto) {
		try {
			Veiculo veiculo = veiculoManager.convertVeiculoDTOToEntity(dto);
			veiculoManager.save(veiculo);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@PUT
	@Path("/update/{codigo}/odometro/{odometro}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateOdometroVeiculo(@PathParam("codigo") Integer codigoVeiculo, @PathParam("odometro") Integer odometro) {
		try {
			veiculoManager.updateOdometroVeiculo(codigoVeiculo, odometro);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateVeiculo(VeiculoDTO dto) {
		try {
			veiculoManager.update(dto);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{codigo}")
	public Response deleteVeiculo(@PathParam("codigo") Integer codigoVeiculo) {
		try {
			veiculoManager.delete(codigoVeiculo);
			return Response.ok().build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}
}
