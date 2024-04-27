package br.unitins.joaovittor.basqueteiros.Marca.resource;

import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.Marca.service.MarcaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response.Status;
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

@Path("/marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {
    
    @Inject
    public MarcaService service;

    @POST
    public Response create(MarcaDTO dto){
        return Response.ok(service.create(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete( @PathParam("id") Long id){
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, MarcaDTO dto){
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    public Response findById( @PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome( @PathParam("nome") String nome){
        
        return Response.ok(service.findByNome(nome)).build();
    }

}
