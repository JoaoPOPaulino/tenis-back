package br.unitins.joaovittor.basqueteiros.dto.pagamento;

import java.math.BigDecimal;

import br.unitins.joaovittor.basqueteiros.model.tipo_pagamento.TipoPagamento;

public record PagamentoDTO(
        TipoPagamento tipoPagamento,
        BigDecimal valor,
        Long idPedido,
        Long idCartao,
        String chavePix,
        String numeroBoleto) {

}
