package br.unitins.joaovittor.basqueteiros.Funcionario.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioPasswordUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioUsernameUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.service.FuncionarioService;
import br.unitins.joaovittor.basqueteiros.Usuario.repository.UsuarioRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.xml.bind.ValidationException;

@Path("/funcionarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FuncionarioResource {
    
    @Inject
    public FuncionarioService service;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    JsonWebToken jwt;

    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);

    @POST
    public Response create(FuncionarioDTO dto){
        return Response.ok(service.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Funcionario")
    public Response delete( @PathParam("id") Long id){
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Funcionario")
    public Response update( @PathParam("id") Long id, FuncionarioDTO dto){
        try {
            service.update(id, dto);
        } catch (ValidationException e) {
            LOG.warnf("Erro de validação", e.getMessage());
        }
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Funcionario")
    @Path("/update-password")
    public Response updateUsuarioPassword(FuncionarioPasswordUpdateDTO passwordUpdateDTO){
        service.updateUsuarioPassword(passwordUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Funcionario")
    @Path("/update-username")
    public Response updateUsuarioUsername(FuncionarioUsernameUpdateDTO usernameUpdateDTO){
        service.updateUsuarioUsername(usernameUpdateDTO);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    @RolesAllowed("Funcionario")
    public Response findById( @PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed("Funcionario")
    public Response findByNome( @PathParam("nome") String nome){
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @Path("/search/cpf/{cpf}")
    @RolesAllowed("Funcionario")
    public Response findByCpf( @PathParam("cpf") String cpf){
        return Response.ok(service.findByCpf(cpf)).build();
    }

    @GET
    @Path("/search/username/{username}")
    @RolesAllowed("Funcionario")
    public Response findByUsername( @PathParam("username") String username){
        return Response.ok(service.findByUsername(username)).build();
    }
}
