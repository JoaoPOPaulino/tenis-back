package br.unitins.joaovittor.basqueteiros.Basqueteira.resource;

import org.jboss.logging.Logger;

import br.unitins.joaovittor.basqueteiros.Basqueteira.dto.BasqueteiraDTO;
import br.unitins.joaovittor.basqueteiros.Basqueteira.service.BasqueteiraService;
import br.unitins.joaovittor.basqueteiros.Cor.resource.CorResource;
import jakarta.annotation.security.RolesAllowed;
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
@RolesAllowed("Funcionario")
public class BasqueteiraResource {
    
    @Inject
    BasqueteiraService service;

    private static final Logger LOG = Logger.getLogger(CorResource.class);
    
    @POST
    public Response create(BasqueteiraDTO dto){
        LOG.info("Executando create");
        LOG.debugf("DTO: %s", dto);
        return Response.ok(service.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, BasqueteiraDTO dto) {
        LOG.info("Executando update");
        LOG.debugf("New DTO: %s", dto);
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Executando delete");
        LOG.info("ID informado: "+id);
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    public Response findAll(){
        LOG.info("Executando findAll");
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("Executando o metodo findByNome. Nome: %s", nome);
        if(service.findByNome(nome) != null)
            return Response.ok(service.findByNome(nome)).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        LOG.infof("Executando o metodo findById. Id: %s", id.toString());
        if(service.findById(id) != null)
            return Response.ok(service.findById(id)).build();
        return Response.status(Status.NOT_FOUND).build();
    }

}
