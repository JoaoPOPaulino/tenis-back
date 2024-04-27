package br.unitins.joaovittor.basqueteiros.Basqueteira.resource;

import br.unitins.joaovittor.basqueteiros.Basqueteira.dto.BasqueteiraDTO;
import br.unitins.joaovittor.basqueteiros.Basqueteira.service.BasqueteiraService;
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

@Path("/basqueteiras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BasqueteiraResource {
    
    @Inject
    BasqueteiraService service;

    @POST
    public Response create(BasqueteiraDTO dto){
        return Response.ok(service.create(dto)).build();
        // tem como criar uma basqueteira sem ter criado, por exemplo cor
        // ou tem como criar uma cor a partir de uma tentativa de criar basqueteira ("view" do modelo mvc)

        // tabela "basqueteira" nao existe, nem "meia" por ser uma herança de produto
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, BasqueteiraDTO dto) {
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
    @Path("/{id}")
    public Response findById(@PathParam("id") long id){
        if(service.findById(id) != null)
            return Response.ok(service.findById(id)).build();
        return Response.status(Status.NOT_FOUND).build();
    }

}
