package br.unitins.joaovittor.basqueteiros.Produto.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;

public interface ProdutoService {
    
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto);
    public void update(Long id, ProdutoDTO dto);
    public void updateEstoque(Long id, int qtdComprada);
    public boolean delete(Long id);
    public List<ProdutoResponseDTO> findAll();
    public ProdutoResponseDTO findById(Long id);
    public List<ProdutoResponseDTO> findByNome(String nome);
}
