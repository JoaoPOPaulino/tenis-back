package br.unitins.joaovittor.basqueteiros.dto.marca;

import jakarta.validation.constraints.NotBlank;

public record MarcaDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome) {

}
