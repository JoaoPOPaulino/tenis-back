package br.unitins.joaovittor.basqueteiros.Funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioPasswordUpdateDTO(
    
    @NotBlank
    String oldPassword,

    @NotBlank
    String newPassword
) {

}
