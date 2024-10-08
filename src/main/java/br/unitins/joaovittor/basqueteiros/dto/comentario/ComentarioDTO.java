package br.unitins.joaovittor.basqueteiros.dto.comentario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ComentarioDTO(
        @NotBlank(message = "O conteúdo não pode ser vazio")
        @Size(max = 500, message = "O conteúdo não pode ultrapassar 500 caracteres.")
        String conteudo) {

}
