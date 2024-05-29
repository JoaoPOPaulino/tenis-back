package br.unitins.joaovittor.basqueteiros.Pedido.resource;

import br.unitins.joaovittor.basqueteiros.Pedido.dto.PedidoDTO;
import br.unitins.joaovittor.basqueteiros.Pedido.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    public PedidoService service;

    @POST
    public Response create(PedidoDTO dto){
        return Response.ok(service.create(dto)).build();
    }

    @GET
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/cliente/id/{id}")
    public Response findById( @PathParam("id") Long id){
        return Response.ok(service.findById(id)).build();
    }

    public Response findByCliente( @PathParam("idCliente") Long idCliente ){
        return Response.ok(service.findByCliente(idCliente)).build();
    }
    
}
