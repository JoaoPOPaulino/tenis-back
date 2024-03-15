package br.unitins.joaovittor.basqueteiros.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.dto.ProdutoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.Produto;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.repository.ProdutoRepository;
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

    @Override
    @Transactional
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setDescricao(dto.descricao());
        produto.setQuantidade(dto.quantidade());
        produto.setPrecoCompra(dto.precoCompra());
        produto.setPrecoVenda(dto.precoVenda());
        produto.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        repository.persist(produto);
        return ProdutoResponseDTO.parse(produto);
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
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<ProdutoResponseDTO> findAll() {
        return repository.findAll().list()
            .stream()
            .map(p -> ProdutoResponseDTO.parse(p)).toList();
    }

    @Override
    public ProdutoResponseDTO findById(Long id) {
        return ProdutoResponseDTO.parse(repository.findById(id));
    }

    @Override
    public List<ProdutoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
        .stream()
        .map(e -> ProdutoResponseDTO.parse(e)).toList();
    }
    
}
