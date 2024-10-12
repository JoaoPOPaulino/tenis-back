package br.unitins.joaovittor.basqueteiros.service.marca;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;

public interface MarcaService {

    MarcaResponseDTO create(MarcaDTO dto);

    MarcaResponseDTO update(Long id, MarcaDTO dto);

    void delete(Long id);

    List<MarcaResponseDTO> findAll(int page, int pageSize);

    MarcaResponseDTO findById(Long id);

    List<MarcaResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}
