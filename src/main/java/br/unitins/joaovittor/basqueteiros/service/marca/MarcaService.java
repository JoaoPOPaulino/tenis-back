package br.unitins.joaovittor.basqueteiros.service.marca;

import br.unitins.joaovittor.basqueteiros.dto.marcaDTO.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marcaDTO.MarcaResponseDTO;

public interface  MarcaService {

     MarcaResponseDTO createMarca(MarcaDTO marcaDTO);
    
    MarcaResponseDTO getMarcaById(Long id);
    
    MarcaResponseDTO getMarcaByNome(String nome);
}
