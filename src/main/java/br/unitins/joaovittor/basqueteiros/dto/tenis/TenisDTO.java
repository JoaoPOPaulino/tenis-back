package br.unitins.joaovittor.basqueteiros.dto.tenis;

import java.util.Set;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TenisDTO(
        @NotNull(message = "A marca é obrigatória")
        Long idMarca,
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @Positive(message = "O preço deve ser positivo")
        float preco,
        @NotNull(message = "O estoque é obrigatório")
        Integer estoque,
        @NotBlank(message = "O modelo é obrigatório")
        String modelo,
        @NotBlank(message = "A descrição é obrigatória")
        String descricao) {

}
