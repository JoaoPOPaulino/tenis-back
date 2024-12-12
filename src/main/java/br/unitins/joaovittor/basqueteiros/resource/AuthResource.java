package br.unitins.joaovittor.basqueteiros.resource;

import org.jboss.logging.Logger;

import br.unitins.joaovittor.basqueteiros.dto.login.LoginDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.hash.HashService;
import br.unitins.joaovittor.basqueteiros.service.jwt.JwtService;
import br.unitins.joaovittor.basqueteiros.service.usuario.UsuarioService;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
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

    private static final Logger LOG = Logger.getLogger(AuthResource.class);

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    @Path("/login")
    public Response login(@Valid LoginDTO dto) {
        LOG.info("Iniciando processo de autenticação para usuário: " + dto.login());

        try {
            String hashSenha = hashService.getHashSenha(dto.senha());
            UsuarioResponseDTO usuario = usuarioService.findByLoginAndSenha(dto.login(), hashSenha);
            String token = jwtService.generateJwt(usuario);

            LOG.info("Autenticação bem-sucedida para usuário: " + dto.login());
            return Response.ok()
                    .header("Authorization", "Bearer " + token)
                    .build();

        } catch (ValidationException e) {
            LOG.warn("Falha na autenticação: " + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("credenciais", e.getMessage()))
                    .build();
        } catch (Exception e) {
            LOG.error("Erro interno durante autenticação: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("erro", "Erro interno no servidor"))
                    .build();
        }
    }
}

record ErrorResponse(String field, String message) {

}
