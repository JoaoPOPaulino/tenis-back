package br.unitins.joaovittor.basqueteiros.Funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioUsernameUpdateDTO(
    @NotBlank
    String newUsername
) {
}
