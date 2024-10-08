package br.unitins.joaovittor.basqueteiros.resource;

import org.jboss.logging.Logger;

import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioDTO;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {

    @Inject
    UsuarioService service;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @POST
    public Response login(@Valid LoginDTO dto) {
        LOG.info("Iniciando autenticação.");
        String hashSenha = hashService.getHashSenha(dto.senha());

        UsuarioDTO result = service.findByLoginAndSenha(dto.login(),
                hashSenha);

        if (result != null) {
            LOG.info("Login e senha corretos.");
            String token = jwtService.generateJwt(result);
            LOG.info("Token JWT gerado com sucesso.");
            return Response.ok().header("Authorization", "Bearer " + token).build();
        } else {
            LOG.warn("Login e senha incorretos.");
            return Response.status(Response.Status.UNAUTHORIZED).entity("Login ou senha incorretos").build();
        }
    }

}
