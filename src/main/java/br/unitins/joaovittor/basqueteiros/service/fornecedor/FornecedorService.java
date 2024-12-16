package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;

public interface FornecedorService {

    // Operações CRUD básicas
    FornecedorResponseDTO create(FornecedorDTO dto);

    FornecedorResponseDTO update(Long id, FornecedorDTO dto);

    void delete(Long id);

    // Operações de busca
    List<FornecedorResponseDTO> findAll(int page, int pageSize);

    FornecedorResponseDTO findById(Long id);

    List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize);

    // Operação de endereço
    FornecedorResponseDTO updateEndereco(Long id, EnderecoDTO enderecoDTO);

    // Operações de contagem
    long count();

    long countByNome(String nome);
}
