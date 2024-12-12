package br.unitins.joaovittor.basqueteiros.model.status_pagamento;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusPagamento {
    PENDENTE(1, "Pagamento Pendente", "Aguardando processamento"),
    PROCESSANDO(2, "Processando", "Pagamento em processamento"),
    APROVADO(3, "Pagamento Aprovado", "Pagamento confirmado com sucesso"),
    RECUSADO(4, "Pagamento Recusado", "Pagamento não autorizado"),
    CANCELADO(5, "Pagamento Cancelado", "Pagamento cancelado pelo usuário ou sistema"),
    ESTORNADO(6, "Pagamento Estornado", "Valor devolvido ao usuário");

    private final int id;
    private final String nome;
    private final String descricao;

    StatusPagamento(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public static StatusPagamento fromId(Integer id) {
        if (id == null) {
            return null;
        }

        for (StatusPagamento status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }

        throw new IllegalArgumentException("ID de status de pagamento inválido: " + id);
    }
}
