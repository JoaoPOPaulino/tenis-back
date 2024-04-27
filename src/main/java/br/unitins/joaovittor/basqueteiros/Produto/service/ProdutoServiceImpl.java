package br.unitins.joaovittor.basqueteiros.Produto.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Fornecedor.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.Marca.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;
import br.unitins.joaovittor.basqueteiros.Produto.repository.ProdutoRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService{

    @Inject
    ProdutoRepository repository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Override
    @Transactional
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto) {
        Produto produto = new Produto();

        verificarNome(dto.nome());

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoCompra(dto.precoCompra());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        produto.setMarca(marcaRepository.findById(dto.idMarca()));

        repository.persist(produto);
        return ProdutoResponseDTO.valueof(produto);
    }

    public void verificarNome(String nome){
        Produto p = repository.findByNomeCompleto(nome);
        if(p != null)
            throw new ValidationException("nome", "O nome '"+nome+"' ja existe");
        
    }

    @Override
    @Transactional
    public void update(Long id, ProdutoDTO dto) {
        Produto produto = repository.findById(id);

        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoCompra(dto.precoCompra());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        produto.setMarca(marcaRepository.findById(dto.idMarca()));
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return repository.findAll().list()
            .stream()
            .map(p -> ProdutoResponseDTO.valueof(p)).toList();
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        Produto produto = repository.findById(id);
        if(produto != null)
            return ProdutoResponseDTO.valueof(produto);
        return null;
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {

        List<ProdutoResponseDTO> lista = repository.findByNome(nome)
        .stream()
        .map(e -> ProdutoResponseDTO.valueof(e)).toList();

        if(lista.size() != 0)
            return lista;
        return null;
    }
    
}
