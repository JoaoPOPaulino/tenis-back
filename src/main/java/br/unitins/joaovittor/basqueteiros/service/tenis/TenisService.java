package br.unitins.joaovittor.basqueteiros.service.tenis;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;

public interface TenisService {

    TenisResponseDTO create(TenisDTO dto);

    TenisResponseDTO update(Long id, TenisDTO dto);

    void delete(Long id);

    TenisResponseDTO findById(Long id);

    List<TenisResponseDTO> findByNome(String nome, int page, int pageSize);

    List<TenisResponseDTO> findByMarca(String marcaNome, int page, int pageSize);

    List<TenisResponseDTO> findByPrecoRange(float minPreco, float maxPreco, int page, int pageSize);

    List<TenisResponseDTO> findAll(int page, int pageSize);
}
