package br.unitins.joaovittor.basqueteiros.resource;

import br.unitins.joaovittor.basqueteiros.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    public UsuarioService usuarioService;

    @POST
    public Response create(UsuarioDTO dto) {
        return Response.ok(usuarioService.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        usuarioService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UsuarioDTO dto) {
        usuarioService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(usuarioService.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id){
        return Response.ok(usuarioService.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        usuarioService.findByNome(nome);
        return Response.status(Status.OK).build();
    }

}
