package br.unitins.joaovittor.basqueteiros.Pedido.resource;

import org.jboss.logging.Logger;

import br.unitins.joaovittor.basqueteiros.Cor.resource.CorResource;
import br.unitins.joaovittor.basqueteiros.Pedido.dto.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.Pedido.service.PedidoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    public PedidoService service;

    private static final Logger LOG = Logger.getLogger(CorResource.class);

    @POST
    @RolesAllowed("Cliente")
    public Response create(PedidoDTO dto){
        LOG.info("Executando o create");
        LOG.debugf("DTO: %s", dto);
        return Response.ok(service.create(dto)).build(); 
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll(){
        LOG.info("Executando o findAll");
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/cliente/id/{id}")
    @RolesAllowed("Funcionario")
    public Response findById( @PathParam("id") Long id){
        LOG.infof("Executando o metodo findById. Id: %s", id.toString());
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findByCliente( @PathParam("idCliente") Long idCliente ){
        LOG.infof("Executando o metodo findByCliente. IdCliente: %s", idCliente.toString());
        return Response.ok(service.findByCliente(idCliente)).build();
    }
    
}
