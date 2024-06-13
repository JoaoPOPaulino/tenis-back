package br.unitins.joaovittor.basqueteiros.Cliente.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteUsernameUpdateDTO(
    @NotBlank
    String newUsername
) {
}
