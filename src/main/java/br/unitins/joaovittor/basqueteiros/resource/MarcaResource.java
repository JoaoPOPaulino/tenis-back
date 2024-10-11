package br.unitins.joaovittor.basqueteiros.resource;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.marca.MarcaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
    MarcaService marcaService;

    @POST
    public Response criarMarca(@Valid MarcaDTO marcaDTO) {
        MarcaResponseDTO marca = marcaService.createMarca(marcaDTO);
        return Response.status(Response.Status.CREATED).entity(marca).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarMarcaPorId(@PathParam("id") Long id) {
        MarcaResponseDTO marca = marcaService.getMarcaById(id);
        if (marca != null) {
            return Response.ok(marca).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarMarcaPorNome(@PathParam("nome") String nome) {
        MarcaResponseDTO marca = marcaService.getMarcaByNome(nome);
        if (marca != null) {
            return Response.ok(marca).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}