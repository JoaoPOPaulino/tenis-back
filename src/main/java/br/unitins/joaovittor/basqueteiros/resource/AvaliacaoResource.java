package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.application.Result;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.avaliacao.AvaliacaoService;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
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
    public Response create(@Valid AvaliacaoDTO dto) {
        try {
            AvaliacaoResponseDTO avaliacao = avaliacaoService.create(dto);
            return Response.status(Status.CREATED)
                    .entity(avaliacao)
                    .build();

        } catch (ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(new Result(e.getConstraintViolations()))
                    .build();

        } catch (ValidationException e) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(new Result(e.getMessage(), false))
                    .build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid AvaliacaoDTO dto) {
        try {
            AvaliacaoResponseDTO avaliacao = avaliacaoService.update(id, dto);
            return Response.ok(avaliacao).build();

        } catch (ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST)
                    .entity(new Result(e.getConstraintViolations()))
                    .build();

        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new Result(e.getMessage(), false))
                    .build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            avaliacaoService.delete(id);
            return Response.noContent().build();

        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new Result(e.getMessage(), false))
                    .build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }

    @GET
    public Response findAll(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        try {
            List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.findAll(page, pageSize);
            return Response.ok(avaliacoes).build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        try {
            AvaliacaoResponseDTO avaliacao = avaliacaoService.findById(id);
            return Response.ok(avaliacao).build();

        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND)
                    .entity(new Result(e.getMessage(), false))
                    .build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }
}
