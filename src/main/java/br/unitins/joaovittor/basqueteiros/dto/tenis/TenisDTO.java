package br.unitins.joaovittor.basqueteiros.dto.tenis;

import java.math.BigDecimal;

import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;

public record TenisDTO(
        String descricao,
        Long idMarca,
        String modelo,
        Tamanho tamanho,
        BigDecimal preco // assumindo que vem da classe Produto
        ) {

}
