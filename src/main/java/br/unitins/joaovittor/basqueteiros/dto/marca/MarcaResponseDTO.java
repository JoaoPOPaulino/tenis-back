package br.unitins.joaovittor.basqueteiros.dto.marca;

import br.unitins.joaovittor.basqueteiros.model.marca.Marca;

public record MarcaResponseDTO(
        Long id,
        String nome) {

    public static MarcaResponseDTO valueOf(Marca marca) {
        return new MarcaResponseDTO(
                marca.getId(),
                marca.getNome()
        );
    }
}
