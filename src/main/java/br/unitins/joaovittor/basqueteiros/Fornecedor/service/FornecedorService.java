package br.unitins.joaovittor.basqueteiros.Fornecedor.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import jakarta.validation.Valid;

public interface FornecedorService {

    public FornecedorResponseDTO create(@Valid FornecedorDTO dto);
    public void update(Long id, FornecedorDTO dto);
    public boolean delete(Long id);
    public List<FornecedorResponseDTO> findAll();
    public FornecedorResponseDTO findById(Long id);
    public List<FornecedorResponseDTO> findByNomeEmpresa(String nome);

} 