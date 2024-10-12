package br.unitins.joaovittor.basqueteiros.dto.avaliacao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AvaliacaoDTO(
        @NotNull(message = "O campo Tenis deve ser informado.")
        Long idTenis,
        @NotBlank(message = "O campo Conteúdo deve ser informado.")
        String conteudo) {

}
