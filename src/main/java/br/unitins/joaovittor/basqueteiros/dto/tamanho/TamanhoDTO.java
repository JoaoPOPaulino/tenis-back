package br.unitins.joaovittor.basqueteiros.dto.tamanho;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record TamanhoDTO(
        @NotBlank(message = "O número é obrigatório.")
        Integer numero,
        @PositiveOrZero(message = "A quantidade em estoque não pode ser negativa.")
        Integer qntEstoque) {

}
