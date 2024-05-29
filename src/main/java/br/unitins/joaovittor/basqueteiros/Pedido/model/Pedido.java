package br.unitins.joaovittor.basqueteiros.Pedido.model;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cliente.model.Cliente;
import br.unitins.joaovittor.basqueteiros.DefaultEntity.model.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.ItemPedido.model.ItemPedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido extends DefaultEntity{
    
    // Implementar pagamento

    private LocalDateTime data;

    private Double valorTotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> itens;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    
}
