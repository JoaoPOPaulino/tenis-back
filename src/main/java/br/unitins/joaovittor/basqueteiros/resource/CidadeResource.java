package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.application.Result;
import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeDTO;
import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.cidade.CidadeService;
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

@Path("/cidades")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CidadeResource {

    @Inject
    CidadeService cidadeService;

    @POST
    public Response create(@Valid CidadeDTO dto) {
        try {
            CidadeResponseDTO cidade = cidadeService.create(dto);
            return Response.status(Status.CREATED)
                    .entity(cidade)
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
    public Response update(@PathParam("id") Long id, @Valid CidadeDTO dto) {
        try {
            CidadeResponseDTO cidade = cidadeService.update(id, dto);
            return Response.ok(cidade).build();

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
            cidadeService.delete(id);
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
            List<CidadeResponseDTO> cidades = cidadeService.findAll(page, pageSize);
            return Response.ok(cidades).build();

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
            CidadeResponseDTO cidade = cidadeService.findById(id);
            return Response.ok(cidade).build();

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
    @Path("/count")
    public Response count() {
        try {
            return Response.ok(cidadeService.count()).build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }

    @GET
    @Path("/search/{nome}/count")
    public Response countByNome(@PathParam("nome") String nome) {
        try {
            return Response.ok(cidadeService.countByNome(nome)).build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }

    @GET
    @Path("/search/{nome}")
    public Response search(@PathParam("nome") String nome,
            @QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        try {
            List<CidadeResponseDTO> cidades = cidadeService.findByNome(nome, page, pageSize);
            return Response.ok(cidades).build();

        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity(new Result("Erro interno no servidor", false))
                    .build();
        }
    }
}
