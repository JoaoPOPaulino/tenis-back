package br.unitins.joaovittor.basqueteiros.service.estoque;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.estoque.EstoqueDTO;
import br.unitins.joaovittor.basqueteiros.dto.estoque.EstoqueResponseDTO;
import jakarta.validation.Valid;

public interface EstoqueService {

    EstoqueResponseDTO insert(@Valid EstoqueDTO dto);

    void delete(Long id);

    EstoqueResponseDTO findById(Long id);

    List<EstoqueResponseDTO> findByTenisId(Long tenisId);

    List<EstoqueResponseDTO> findAll();

    EstoqueResponseDTO updateEstoque(Long id, Integer quantidade);
}
