package br.unitins.joaovittor.basqueteiros.Cliente.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientePasswordUpdateDTO(
    
    @NotBlank
    String oldPassword,

    @NotBlank
    String newPassword
) {

}
