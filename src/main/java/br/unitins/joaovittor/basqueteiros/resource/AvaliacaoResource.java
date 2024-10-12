package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.application.Result;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.avaliacao.AvaliacaoService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    @Inject
    AvaliacaoService avaliacaoService;

    @POST
    public Response create(AvaliacaoDTO dto) {
        Result result = null;

        try {
            AvaliacaoResponseDTO avaliacao = avaliacaoService.create(dto);
            return Response.status(Status.CREATED).entity(avaliacao).build();
        } catch (ConstraintViolationException e) {
            result = new Result(e.getConstraintViolations());
        } catch (Exception e) {
            result = new Result(e.getMessage(), false);
        }

        return Response.status(Status.NOT_FOUND).entity(result).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, AvaliacaoDTO dto) {
        try {
            AvaliacaoResponseDTO avaliacao = avaliacaoService.update(id, dto);
            return Response.ok(avaliacao).build();
        } catch (ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        } catch (Exception e) {
            Result result = new Result(e.getMessage(), false);
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            avaliacaoService.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (Exception e) {
            Result result = new Result(e.getMessage(), false);
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @GET
    public List<AvaliacaoResponseDTO> findAll(
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return avaliacaoService.findAll(page, pageSize);
    }

    @GET
    @Path("/{id}")
    public AvaliacaoResponseDTO findById(@PathParam("id") Long id) {
        return avaliacaoService.findById(id);
    }

}
