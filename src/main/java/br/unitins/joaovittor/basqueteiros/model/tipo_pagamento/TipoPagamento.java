package br.unitins.joaovittor.basqueteiros.model.tipo_pagamento;

public enum TipoPagamento {
    CARTAO_CREDITO(1, "Cartão de Crédito"),
    CARTAO_DEBITO(2, "Cartão de Débito"),
    PIX(3, "Pix"),
    BOLETO(4, "Boleto");

    private final int id;
    private final String descricao;

    TipoPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
