package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;
import java.util.Optional;

import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CartaoRepository implements PanacheRepository<Cartao> {

    public Optional<Cartao> findByNumero(String numero) {
        return find("numero", numero).firstResultOptional();
    }

    public List<Cartao> findByUsuario(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }

    public boolean existsByNumero(String numero) {
        return count("numero", numero) > 0;
    }
}
