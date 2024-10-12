package br.unitins.joaovittor.basqueteiros.service.estado;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoDTO;
import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoResponseDTO;
import jakarta.validation.Valid;

public interface EstadoService {

    EstadoResponseDTO create(@Valid EstadoDTO dto);

    EstadoResponseDTO update(Long id, EstadoDTO dto);

    void delete(Long id);

    List<EstadoResponseDTO> findAll(int page, int pageSize);

    EstadoResponseDTO findById(Long id);

    List<EstadoResponseDTO> findByNome(String nome, int page, int pageSize);

    long count();

    long countByNome(String nome);
}
