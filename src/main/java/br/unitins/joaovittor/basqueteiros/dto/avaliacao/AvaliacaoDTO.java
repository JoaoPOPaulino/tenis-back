package br.unitins.joaovittor.basqueteiros.dto.avaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record AvaliacaoDTO(
        @NotBlank(message = "O título não pode ser vazio")
        @Size(max = 100, message = "O título não pode ultrapassar 100 caracteres.")
        String titulo,
        @NotBlank(message = "O conteúdo não pode ser vazio")
        @Size(max = 500, message = "O conteúdo não pode ultrapassar 500 caracteres.")
        String texto,
        @Min(value = 1, message = "A nota deve ser no mínimo 1")
        @Max(value = 5, message = "A nota deve ser no máximo 5")
        Integer nota) {

}
