package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.avaliacao.AvaliacaoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    @Inject
    AvaliacaoService avaliacaoService;

    @POST
    public Response criarAvaliacao(@Valid AvaliacaoDTO avaliacaoDTO, @QueryParam("usuarioId") Long usuarioId) {
        AvaliacaoResponseDTO avaliacao = AvaliacaoResponseDTO.valueOf(avaliacaoService.criarAvaliacao(avaliacaoDTO, usuarioId));
        return Response.status(Response.Status.CREATED).entity(avaliacao).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarAvaliacaoPorId(@PathParam("id") Long id) {
        AvaliacaoResponseDTO avaliacao = AvaliacaoResponseDTO.valueOf(avaliacaoService.buscarAvaliacaoPorId(id));
        if (avaliacao != null) {
            return Response.ok(avaliacao).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public Response buscarAvaliacoesPorUsuarioId(@PathParam("usuarioId") Long usuarioId) {
        List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.buscarAvaliacoesPorUsuarioId(usuarioId)
                .stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
        return Response.ok(avaliacoes).build();
    }

    @GET
    public Response listarTodasAvaliacoes() {
        List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.listarTodasAvaliacoes()
                .stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
        return Response.ok(avaliacoes).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirAvaliacao(@PathParam("id") Long id) {
        avaliacaoService.excluirAvaliacao(id);
        return Response.noContent().build();
    }
}