package br.unitins.joaovittor.basqueteiros.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.dto.ProdutoResponseDTO;
import jakarta.validation.Valid;

public interface ProdutoService {
    
    public ProdutoResponseDTO create(@Valid ProdutoDTO dto);
    public void update(Long id, ProdutoDTO dto);
    public void delete(Long id);
    public List<ProdutoResponseDTO> findAll();
    public ProdutoResponseDTO findById(Long id);
    public List<ProdutoResponseDTO> findByNome(String nome);
}
