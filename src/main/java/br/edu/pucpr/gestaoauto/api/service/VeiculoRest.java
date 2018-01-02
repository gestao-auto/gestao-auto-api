package br.edu.pucpr.gestaoauto.api.service;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.VeiculoDTO;
import br.edu.pucpr.gestaoauto.manager.UsuarioManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaVeiculoManager;
import br.edu.pucpr.gestaoauto.manager.veiculo.VeiculoManager;
import br.edu.pucpr.gestaoauto.model.usuario.Usuario;
import br.edu.pucpr.gestaoauto.model.veiculo.Veiculo;

@Path("/veiculo")
public class VeiculoRest extends AbstractRest {

	private static Logger log = LoggerFactory.getLogger(VeiculoRest.class);

	@Inject VeiculoManager veiculoManager;
	@Inject UsuarioManager usuarioManager;
	@Inject MarcaVeiculoManager marcaManager;

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
}
