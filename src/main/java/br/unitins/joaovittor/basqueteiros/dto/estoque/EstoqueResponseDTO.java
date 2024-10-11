package br.unitins.joaovittor.basqueteiros.dto.estoque;

import br.unitins.joaovittor.basqueteiros.model.estoque.Estoque;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;

public record EstoqueResponseDTO(
        Long id,
        Tamanho tamanho,
        Integer quantidade,
        Long tenisId) {

    public static EstoqueResponseDTO valueOf(Estoque estoque) {
        return new EstoqueResponseDTO(
                estoque.getId(),
                estoque.getTamanho(),
                estoque.getQuantidade(),
                estoque.getTenis().getId()
        );
    }
}
