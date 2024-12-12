package br.unitins.joaovittor.basqueteiros.dto.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.model.pagamento.Pagamento;
import br.unitins.joaovittor.basqueteiros.model.status_pagamento.StatusPagamento;
import br.unitins.joaovittor.basqueteiros.model.tipo_pagamento.TipoPagamento;

public record PagamentoResponseDTO(
        Long id,
        TipoPagamento tipoPagamento,
        StatusPagamento statusPagamento,
        BigDecimal valor,
        LocalDateTime dataPagamento,
        String chavePix,
        String numeroBoleto) {

    public static PagamentoResponseDTO valueOf(Pagamento pagamento) {
        return new PagamentoResponseDTO(
                pagamento.getId(),
                pagamento.getTipoPagamento(),
                pagamento.getStatusPagamento(),
                pagamento.getValor(),
                pagamento.getDataPagamento(),
                pagamento.getChavePix(),
                pagamento.getNumeroBoleto()
        );
    }
}
