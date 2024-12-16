package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.application.Result;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.tenis.TenisService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tenis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TenisResource {

    @Inject
    TenisService tenisService;

    @POST
    public Response create(@Valid TenisDTO tenisDTO) {
        try {
            TenisResponseDTO tenis = tenisService.create(tenisDTO);
            return Response.status(Response.Status.CREATED).entity(tenis).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        } catch (Exception e) {
            Result result = new Result(e.getMessage(), false);
            return Response.status(Response.Status.BAD_REQUEST).entity(result).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        try {
            TenisResponseDTO tenis = tenisService.findById(id);
            return Response.ok(tenis).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new Result(e.getMessage(), false))
                    .build();
        }
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(
            @PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        try {
            List<TenisResponseDTO> tenis = tenisService.findByNome(nome, page, pageSize);
            return Response.ok(tenis).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Result(e.getMessage(), false))
                    .build();
        }
    }

    @GET
    @Path("/search/marca/{marcaNome}")
    public Response findByMarca(
            @PathParam("marcaNome") String marcaNome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        try {
            List<TenisResponseDTO> tenis = tenisService.findByMarca(marcaNome, page, pageSize);
            return Response.ok(tenis).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Result(e.getMessage(), false))
                    .build();
        }
    }

    @GET
    @Path("/search/preco")
    public Response findByPrecoRange(
            @QueryParam("min") float minPreco,
            @QueryParam("max") float maxPreco,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        try {
            List<TenisResponseDTO> tenis = tenisService.findByPrecoRange(minPreco, maxPreco, page, pageSize);
            return Response.ok(tenis).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new Result(e.getMessage(), false))
                    .build();
        }
    }

    @GET
    public Response findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        List<TenisResponseDTO> tenis = tenisService.findAll(page, pageSize);
        return Response.ok(tenis).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            tenisService.delete(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new Result(e.getMessage(), false))
                    .build();
        }
    }
}
