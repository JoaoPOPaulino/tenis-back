package br.unitins.joaovittor.basqueteiros.dto.avaliacao;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.avaliacao.Avaliacao;

public record AvaliacaoResponseDTO(
        Long id,
        TenisResponseDTO tenis,
        String conteudo) {

    public static AvaliacaoResponseDTO valueOf(Avaliacao avaliacao) {
        return new AvaliacaoResponseDTO(
                avaliacao.getId(),
                TenisResponseDTO.valueOf(avaliacao.getTenis()),
                avaliacao.getConteudo());
    }
}
