package br.unitins.joaovittor.basqueteiros.resource;

import java.time.LocalDate;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.Fornecedor;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    @Inject
    public FornecedorRepository fornecedorRepository;

    @POST
    @Transactional
    public FornecedorResponseDTO create(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setDataCadastro(LocalDate.of(dto.ano(), dto.mes(), dto.dia()));

        fornecedorRepository.persist(fornecedor);

        return FornecedorResponseDTO.parse(fornecedor);
    }

    @GET
    public List<FornecedorResponseDTO> findAll() {
        return fornecedorRepository.findAll()
                .stream()
                .map(e -> FornecedorResponseDTO.parse(e)).toList();
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<FornecedorResponseDTO> findByNome(@PathParam("nome") String nome) {
        return fornecedorRepository.findByNome(nome)
        .stream()
        .map(e -> FornecedorResponseDTO.parse(e)).toList();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void update(@PathParam("id") Long id, FornecedorDTO dto) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);

        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        LocalDate dataNasc = LocalDate.of(dto.ano(), dto.mes(), dto.dia());
        fornecedor.setDataCadastro(dataNasc == null ? dataNasc : null);

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        fornecedorRepository.deleteById(id);
    }
}
