package br.unitins.joaovittor.basqueteiros.service.marca;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;

public interface MarcaService {

    MarcaResponseDTO createMarca(MarcaDTO marcaDTO);

    MarcaResponseDTO getMarcaById(Long id);

    MarcaResponseDTO getMarcaByNome(String nome);
}
