package br.unitins.joaovittor.basqueteiros.repository;

import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TenisRepository implements PanacheRepository<Tenis> {

    public PanacheQuery<Tenis> findByNome(String nome) {
        if (nome == null) {
            return null;
        }
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public PanacheQuery<Tenis> findByModelo(String modelo) {
        if (modelo == null) {
            return null;
        }
        return find("UPPER(modelo) LIKE ?1 ", "%" + modelo.toUpperCase() + "%");
    }
}
