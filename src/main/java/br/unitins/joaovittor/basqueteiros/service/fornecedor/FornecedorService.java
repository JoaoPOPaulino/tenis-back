package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;

public interface FornecedorService {

    FornecedorResponseDTO create(FornecedorDTO dto);

    FornecedorResponseDTO update(Long id, FornecedorDTO dto);

    void delete(Long id);

    List<FornecedorResponseDTO> findAll(int page, int pageSize);

    FornecedorResponseDTO findById(Long id);

    List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize);

    FornecedorResponseDTO createEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO);

    FornecedorResponseDTO updateEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO);

    FornecedorResponseDTO removeEnderecos(Long fornecedorId, Long enderecoId);

    long count();

    long countByNome(String nome);
}
