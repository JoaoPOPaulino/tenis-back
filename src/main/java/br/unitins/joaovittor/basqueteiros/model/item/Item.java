package br.unitins.joaovittor.basqueteiros.model.item;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item extends DefaultEntity {

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "id_tenis", nullable = false)
    private Tenis tenis;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    public Float getSubTotal() {
        return quantidade * preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Tenis getTenis() {
        return tenis;
    }

    public void setTenis(Tenis tenis) {
        this.tenis = tenis;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
