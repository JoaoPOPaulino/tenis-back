package br.unitins.joaovittor.basqueteiros.dto.comentario;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.model.comentario.Comentario;

public record ComentarioResponseDTO(Long id,
        String conteudo,
        LocalDateTime dataCriacao,
        Long idUsuario) {

    public static ComentarioResponseDTO valueOf(Comentario comentario) {
        return new ComentarioResponseDTO(
                comentario.getId(),
                comentario.getConteudo(),
                comentario.getDataCriacao(),
                comentario.getUsuario() != null ? comentario.getUsuario().getId() : null);
    }
}
