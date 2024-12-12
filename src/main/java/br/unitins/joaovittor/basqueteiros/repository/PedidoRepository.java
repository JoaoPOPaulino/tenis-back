package br.unitins.joaovittor.basqueteiros.repository;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {

    public List<Pedido> findByUsuario(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }

    public List<Pedido> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return find("data BETWEEN ?1 AND ?2", inicio, fim).list();
    }

    public List<Pedido> findPedidosAtivos() {
        return list("ativo", true);
    }
}
