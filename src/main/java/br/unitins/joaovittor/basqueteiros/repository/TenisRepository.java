package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class TenisRepository implements PanacheRepository<Tenis> {

    // Método para buscar Tênis pelo nome
    public List<Tenis> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%").list();
    }

    // Método para buscar Tênis pela marca
    public List<Tenis> findByMarca(String marcaNome) {
        return find("UPPER(marca.nome) LIKE UPPER(?1)", "%" + marcaNome + "%").list();
    }

    // Método para buscar Tênis pelo intervalo de preço
    public List<Tenis> findByPrecoRange(float minPreco, float maxPreco) {
        return find("preco BETWEEN ?1 AND ?2", minPreco, maxPreco).list();
    }

    // Método para buscar um Tênis pelo ID
    public Tenis findById(Long id) {
        try {
            return find("id", id).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}
