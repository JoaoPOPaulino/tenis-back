package br.unitins.joaovittor.basqueteiros.Pedido.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Pedido.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido>{
    
    public List<Pedido> findByCliente(Long idCliente) {
        return find("cliente.id = ?1", idCliente).list();
    }
    
}
