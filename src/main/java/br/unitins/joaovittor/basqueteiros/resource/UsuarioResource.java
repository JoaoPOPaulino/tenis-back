package br.unitins.joaovittor.basqueteiros.resource;

import org.jboss.logging.Logger;

import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.service.usuario.UsuarioService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    @Inject
    UsuarioService service;

    @POST
    @Transactional
    public Response insert(UsuarioDTO dto) {
        LOGGER.info("Iniciando inserção de novo usuário");
        Response response = Response.status(Status.CREATED).entity(service.insert(dto)).build();
        LOGGER.info("Usuário inserido com sucesso");
        return response;
    }

}
