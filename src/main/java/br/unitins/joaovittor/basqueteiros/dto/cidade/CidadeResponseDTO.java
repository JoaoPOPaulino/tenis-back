package br.unitins.joaovittor.basqueteiros.dto.cidade;

import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;

public record CidadeResponseDTO(
        Long id,
        String nome,
        EstadoResponseDTO estado) {

    public static CidadeResponseDTO valueOf(Cidade cidade) {
        return new CidadeResponseDTO(
                cidade.getId(),
                cidade.getNome(),
                EstadoResponseDTO.valueOf(cidade.getEstado())
        );
    }
}
