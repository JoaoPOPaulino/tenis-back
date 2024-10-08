package br.unitins.joaovittor.basqueteiros.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.unitins.joaovittor.basqueteiros.service.usuario.UsuarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.core.Response;

public class UsuarioLogadoResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioLogadoResource.class);

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService service;

    @GET
    @RolesAllowed({"User", "Admin"})
    public Response getUsuario() {
        String login = jwt.getSubject();
        LOGGER.info("Obtendo usuário para login: " + login);
        return Response.ok(service.findByLogin(login)).build();
    }
}
