package br.unitins.joaovittor.basqueteiros.Meia.resource;

import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaDTO;
import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Meia.service.MeiaService;
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

@Path("/meias")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MeiaResource {
    
    @Inject
    MeiaService service;

    @POST
    public Response create(MeiaDTO dto){
        return Response.ok(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, MeiaDTO dto) {
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        if(service.findByNome(nome) != null)
            return Response.ok(service.findByNome(nome)).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") long id){
        MeiaResponseDTO response = service.findById(id);
        if(response != null)
            return Response.ok(response).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    // Novos (fazer test unit)
    // find by descricao
    // find by fornecedor (id)
    // find by marca (id)
    // find by cor (id)
}
