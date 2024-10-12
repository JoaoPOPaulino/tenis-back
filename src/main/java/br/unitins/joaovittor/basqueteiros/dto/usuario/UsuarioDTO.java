package br.unitins.joaovittor.basqueteiros.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O e-mail é obrigatório")
        String email,
        @NotBlank(message = "O login é obrigatório")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        String senha) {

}
