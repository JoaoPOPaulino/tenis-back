package br.unitins.joaovittor.basqueteiros.dto.avaliacao;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.model.avaliacao.Avaliacao;

public record AvaliacaoResponseDTO(Long id,
        String titulo,
        String texto,
        Integer nota,
        LocalDateTime dataCriacao,
        Long idUsuario) {

    public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao) {
        return new AvaliacaoResponseDTO(
                avaliacao.getId(),
                avaliacao.getTitulo(),
                avaliacao.getTexto(),
                avaliacao.getNota(),
                avaliacao.getDataCriacao(),
                avaliacao.getUsuario() != null ? avaliacao.getUsuario().getId() : null);
    }
}
