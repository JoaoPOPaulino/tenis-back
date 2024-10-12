package br.unitins.joaovittor.basqueteiros.service.cidade;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeDTO;
import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeResponseDTO;
import jakarta.validation.Valid;

public interface CidadeService {

    CidadeResponseDTO create(@Valid CidadeDTO dto);

    CidadeResponseDTO update(Long id, CidadeDTO dto);

    void delete(Long id);

    List<CidadeResponseDTO> findAll(int page, int pageSize);

    CidadeResponseDTO findById(Long id);

    List<CidadeResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}
