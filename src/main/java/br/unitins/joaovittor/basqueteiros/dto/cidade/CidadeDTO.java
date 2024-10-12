package br.unitins.joaovittor.basqueteiros.dto.cidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CidadeDTO(
        @NotBlank(message = "O campo Nome deve ser informado.")
        String nome,
        @NotNull(message = "O campo Estado deve ser informado.")
        Long idEstado) {

}
