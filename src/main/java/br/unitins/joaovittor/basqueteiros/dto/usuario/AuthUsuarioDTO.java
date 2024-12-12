package br.unitins.joaovittor.basqueteiros.dto.usuario;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AuthUsuarioDTO(
        @NotBlank(message = "Username é obrigatório")
        String username,
        @NotBlank(message = "Senha é obrigatória")
        String senha,
        @Min(value = 1, message = "Perfil inválido")
        int perfil) {

}
