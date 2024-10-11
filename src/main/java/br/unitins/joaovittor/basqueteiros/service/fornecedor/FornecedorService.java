package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import jakarta.validation.Valid;

public interface FornecedorService {

    FornecedorResponseDTO insert(@Valid FornecedorDTO fornecedorDTO);

    void delete(Long id);

    FornecedorResponseDTO findById(Long id);

    List<FornecedorResponseDTO> findByNome(String nome);

    List<FornecedorResponseDTO> findAll();

    FornecedorResponseDTO update(Long id, @Valid FornecedorDTO fornecedorDTO);
}
