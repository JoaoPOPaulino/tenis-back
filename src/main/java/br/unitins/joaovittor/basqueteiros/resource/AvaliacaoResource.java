package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;
import java.util.Optional;
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
import org.jboss.logging.Logger;

@Path("/avaliacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvaliacaoResource {

    private static final Logger LOGGER = Logger.getLogger(AvaliacaoResource.class.getName());

    @Inject
    AvaliacaoService avaliacaoService;

    @POST
    public Response criarAvaliacao(@Valid AvaliacaoDTO avaliacaoDTO, @QueryParam("usuarioId") Long usuarioId) {
        AvaliacaoResponseDTO avaliacao = AvaliacaoResponseDTO.valueOf(avaliacaoService.criarAvaliacao(avaliacaoDTO, usuarioId));
        LOGGER.info("Avaliação criada com sucesso: " + avaliacao);
        return Response.status(Response.Status.CREATED).entity(avaliacao).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarAvaliacaoPorId(@PathParam("id") Long id) {
        Optional<AvaliacaoResponseDTO> avaliacaoOpt = Optional.ofNullable(AvaliacaoResponseDTO.valueOf(avaliacaoService.buscarAvaliacaoPorId(id)));
        return avaliacaoOpt.map(avaliacao -> {
            LOGGER.info("Avaliação encontrada: " + avaliacao);
            return Response.ok(avaliacao).build();
        }).orElseGet(() -> {
            LOGGER.warn("Avaliação não encontrada para ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        });
    }

    @GET
    @Path("/usuario/{usuarioId}")
    public Response buscarAvaliacoesPorUsuarioId(@PathParam("usuarioId") Long usuarioId) {
        List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.buscarAvaliacoesPorUsuarioId(usuarioId)
                .stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
        LOGGER.info("Avaliações encontradas para o usuário ID: " + usuarioId);
        return Response.ok(avaliacoes).build();
    }

    @GET
    public Response listarTodasAvaliacoes() {
        List<AvaliacaoResponseDTO> avaliacoes = avaliacaoService.listarTodasAvaliacoes()
                .stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
        LOGGER.info("Listando todas as avaliações");
        return Response.ok(avaliacoes).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirAvaliacao(@PathParam("id") Long id) {
        avaliacaoService.excluirAvaliacao(id);
        LOGGER.info("Avaliação excluída com sucesso: ID " + id);
        return Response.noContent().build();
    }
}
