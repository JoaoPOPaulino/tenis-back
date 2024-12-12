package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;
import java.util.Objects;

import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TenisRepository implements PanacheRepository<Tenis> {

    public PanacheQuery<Tenis> findByNome(String nome) {
        Objects.requireNonNull(nome, "Nome não pode ser nulo");
        return find("UPPER(nome) LIKE ?1", "%" + nome.toUpperCase() + "%");
    }

    public List<Tenis> findAtivos() {
        return list("ativo", true);
    }
}
