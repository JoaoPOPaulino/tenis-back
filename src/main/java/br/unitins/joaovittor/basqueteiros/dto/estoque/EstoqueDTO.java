package br.unitins.joaovittor.basqueteiros.dto.estoque;

import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EstoqueDTO(
        @NotNull(message = "O tamanho é obrigatório")
        Tamanho tamanho,
        @PositiveOrZero(message = "A quantidade não pode ser negativa")
        Integer quantidade,
        @NotNull(message = "O ID do tênis é obrigatório")
        Long tenisId) {

}
