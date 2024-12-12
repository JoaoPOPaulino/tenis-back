package br.unitins.joaovittor.basqueteiros.dto.estado;

import br.unitins.joaovittor.basqueteiros.model.estado.Estado;

public record EstadoResponseDTO(
        Long id,
        String nome,
        String sigla,
        boolean ativo) {

    public static EstadoResponseDTO valueOf(Estado estado) {
        return new EstadoResponseDTO(
                estado.getId(),
                estado.getNome(),
                estado.getSigla(),
                estado.isAtivo()
        );
    }
}
