package br.unitins.joaovittor.basqueteiros.model.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.item.Item;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido extends DefaultEntity {

    @Column(nullable = false)
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<Item> itens = new ArrayList<>();

    @Column(nullable = false)
    private Float total;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Endereco endereco;

    @ManyToOne
    private Cartao cartao;

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    public Float calcularTotal() {
        return itens.stream()
                .map(Item::getSubTotal)
                .reduce(0f, Float::sum);
    }

}
