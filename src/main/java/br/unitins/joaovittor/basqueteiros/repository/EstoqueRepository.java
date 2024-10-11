package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;
import java.util.Set;

import br.unitins.joaovittor.basqueteiros.model.estoque.Estoque;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class EstoqueRepository implements PanacheRepository<Estoque> {

    public List<Estoque> findByTenisId(Long tenisId) {
        return find("tenis.id = ?1", tenisId).list();
    }

    public Estoque findByTamanhoAndTenisId(Tamanho tamanho, Long tenisId) {
        try {
            return find("tamanho = ?1 AND tenis.id = ?2", tamanho, tenisId).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Novo método para encontrar estoques por uma coleção de IDs
    public List<Estoque> findAllByIds(Set<Long> ids) {
        return find("id IN (?1)", ids).list();
    }
}
