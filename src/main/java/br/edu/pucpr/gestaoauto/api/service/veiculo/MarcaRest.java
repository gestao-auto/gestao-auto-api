package br.edu.pucpr.gestaoauto.api.service.veiculo;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.pucpr.gestaoauto.api.Message;
import br.edu.pucpr.gestaoauto.api.service.AbstractRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.edu.pucpr.gestaoauto.api.dto.veiculo.MarcaDTO;
import br.edu.pucpr.gestaoauto.manager.veiculo.MarcaManager;

@Path("/veiculo/marca")
public class MarcaRest extends AbstractRest {
	
	private static Logger log = LoggerFactory.getLogger(MarcaRest.class);

	@Inject
	MarcaManager manager;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{codigo}")
	public Response getById(@PathParam("codigo") int codigo) {
		log.info("MarcaRest -> getById");
		try {
			return Response.ok().entity(manager.getById(codigo)).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response getListMarca() {
		log.info("MarcaRest -> getListMarca");
		try {
			List<MarcaDTO> marcaList = manager.convertListToDTO(manager.getList());
			return Response.ok().entity(marcaList).build();
		} catch (Exception e) {
			log.error(e.toString());
			return super.serverError(e);
		}
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create (MarcaDTO dto){
		log.info("MarcaRest -> create");
		try {
			return Response.ok().entity(
					manager.convertEntityToDTO(
							manager.save(
									manager.convertDTOToEntity(
											dto))))
					.build();
		} catch (Exception e) {
			log.error(e.toString());
			return this.serverError(e);
		}
	}

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update (@PathParam("codigo") Integer codigo, String nome){
        log.info("MarcaRest -> update -> codigo " + codigo + " > nome " + nome);
        try {
            MarcaDTO dto = new MarcaDTO();
            dto.setCodigo(codigo);
            dto.setNome(nome);
            return Response.ok().entity(
                    manager.convertEntityToDTO(
                            manager.update(
                                    manager.convertDTOToEntity(
                                            dto))))
                    .build();
        } catch (Exception e) {
            log.error(e.toString());
            return this.serverError(e);
        }
    }

    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update (@PathParam("codigo") Integer codigo){
        log.info("MarcaRest -> delete");
        try {
            manager.delete(codigo);
            return Response.ok().entity(new Message("Sucesso")).build();
        } catch (Exception e) {
            log.error(e.toString());
            return this.serverError(e);
        }
    }
}