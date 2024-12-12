package br.unitins.joaovittor.basqueteiros.model.pagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;
import br.unitins.joaovittor.basqueteiros.model.status_pagamento.StatusPagamento;
import br.unitins.joaovittor.basqueteiros.model.tipo_pagamento.TipoPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento extends DefaultEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento statusPagamento;

    @Column(nullable = false)
    private BigDecimal valor;

    @OneToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;

    @ManyToOne
    private Cartao cartao;

    private String chavePix;
    private String numeroBoleto;

    @Column(nullable = false)
    private LocalDateTime dataPagamento = LocalDateTime.now();

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(String numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
