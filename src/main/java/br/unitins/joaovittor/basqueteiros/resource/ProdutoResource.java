package br.unitins.joaovittor.basqueteiros.resource;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.dto.ProdutoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.Produto;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.repository.ProdutoRepository;
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

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
    @Inject
    public ProdutoRepository produtoRepository;

    @Inject
    public FornecedorRepository fornecedorRepository;

    @POST
    @Transactional
    public ProdutoResponseDTO create(ProdutoDTO dto){

        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoCompra(dto.precoCompra());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));

        produtoRepository.persist(produto);
        
        return ProdutoResponseDTO.parse(produto);
    }

    @GET
    public List<ProdutoResponseDTO> findAll(){
        return produtoRepository.findAll().list()
            .stream()
            .map(p -> ProdutoResponseDTO.parse(p)).toList();
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<ProdutoResponseDTO> findByNome(@PathParam("nome") String nome) {
        return produtoRepository.findByNome(nome)
        .stream()
        .map(e -> ProdutoResponseDTO.parse(e)).toList();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void update(@PathParam("id") Long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id);

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoCompra(dto.precoCompra());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        produtoRepository.deleteById(id);
    }

}
