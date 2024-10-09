package br.unitins.joaovittor.basqueteiros.dto.tenis;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TenisDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @Positive(message = "O preço deve ser positivo")
        float preco,
        @NotBlank(message = "A descrição é obrigatória")
        String descricao,
        @NotNull(message = "A marca é obrigatória")
        Long marcaId,
        @NotNull(message = "Pelo menos um tamanho deve ser selecionado")
        Set<Long> tamanhoId) {

}
