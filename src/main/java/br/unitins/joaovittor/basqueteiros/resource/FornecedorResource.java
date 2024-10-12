package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;
import java.util.Optional;

import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.service.fornecedor.FornecedorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
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
import org.jboss.logging.Logger;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    private static final Logger LOGGER = Logger.getLogger(FornecedorResource.class.getName());

    @Inject
    FornecedorService fornecedorService;

    @POST
    public Response criarFornecedor(@Valid FornecedorDTO fornecedorDTO) {
        FornecedorResponseDTO fornecedor = fornecedorService.insert(fornecedorDTO);
        LOGGER.info("Fornecedor criado com sucesso: " + fornecedor);
        return Response.status(Response.Status.CREATED).entity(fornecedor).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarFornecedorPorId(@PathParam("id") Long id) {
        Optional<FornecedorResponseDTO> fornecedorOpt = Optional.ofNullable(fornecedorService.findById(id));
        return fornecedorOpt.map(fornecedor -> {
            LOGGER.info("Fornecedor encontrado: " + fornecedor);
            return Response.ok(fornecedor).build();
        }).orElseGet(() -> {
            LOGGER.warn("Fornecedor não encontrado para ID: " + id);
            return Response.status(Response.Status.NOT_FOUND).build();
        });
    }

    @GET
    @Path("/nome/{nome}")
    public Response buscarFornecedoresPorNome(@PathParam("nome") String nome) {
        List<FornecedorResponseDTO> fornecedores = fornecedorService.findByNome(nome);
        LOGGER.info("Fornecedores encontrados para o nome: " + nome);
        return Response.ok(fornecedores).build();
    }

    @GET
    public Response listarTodosFornecedores() {
        List<FornecedorResponseDTO> fornecedores = fornecedorService.findAll();
        LOGGER.info("Listando todos os fornecedores");
        return Response.ok(fornecedores).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizarFornecedor(@PathParam("id") Long id, @Valid FornecedorDTO fornecedorDTO) {
        FornecedorResponseDTO fornecedor = fornecedorService.update(id, fornecedorDTO);
        LOGGER.info("Fornecedor atualizado com sucesso: ID " + id);
        return Response.ok(fornecedor).build();
    }

    @DELETE
    @Path("/{id}")
    public Response excluirFornecedor(@PathParam("id") Long id) {
        fornecedorService.delete(id);
        LOGGER.info("Fornecedor excluído com sucesso: ID " + id);
        return Response.noContent().build();
    }
}
