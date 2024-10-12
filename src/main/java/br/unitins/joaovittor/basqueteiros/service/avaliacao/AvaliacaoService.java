package br.unitins.joaovittor.basqueteiros.service.avaliacao;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoResponseDTO;

public interface AvaliacaoService {

    AvaliacaoResponseDTO create(AvaliacaoDTO dto);

    AvaliacaoResponseDTO update(Long id, AvaliacaoDTO dto);

    AvaliacaoResponseDTO findById(Long id);

    List<AvaliacaoResponseDTO> findAll(int page, int pageSize);

    void delete(Long id);
}
