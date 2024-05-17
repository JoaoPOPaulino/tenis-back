package br.unitins.joaovittor.basqueteiros.AuthUsuario.resource;

import br.unitins.joaovittor.basqueteiros.AuthUsuario.dto.AuthUsuarioDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.service.ClienteService;
import br.unitins.joaovittor.basqueteiros.Hash.service.HashService;
import br.unitins.joaovittor.basqueteiros.Jwt.service.JwtService;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthUsuarioResource {
    
    @Inject
    public ClienteService clienteService;

    @Inject
    public HashService hashService;

    @Inject
    JwtService jwtService;

    @POST
    public Response login(AuthUsuarioDTO dto){
        String hashSenha = hashService.getHashSenha(dto.senha());

        UsuarioResponseDTO usuario = null;

        if(dto.perfil() == 1){
            // cliente
            usuario = clienteService.login(dto.username(), hashSenha);
        } else if (dto.perfil() == 2){
            // funcionario
            return Response.status(Status.NOT_IMPLEMENTED).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }

        if(usuario != null){
            return Response.ok(usuario).header("Authorization", jwtService.generateJwt(usuario))
        .status(Status.CREATED)
        .build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
        
    }
}
