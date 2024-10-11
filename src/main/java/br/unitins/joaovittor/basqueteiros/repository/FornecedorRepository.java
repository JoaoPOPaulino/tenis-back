package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;
import java.util.Set;

import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    // Método para encontrar fornecedor pelo nome
    public List<Fornecedor> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%").list();
    }

    // Método para encontrar fornecedor pelo CNPJ
    public Fornecedor findByCnpj(String cnpj) {
        try {
            return find("cnpj = ?1", cnpj).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para encontrar fornecedor pelo ID
    public Fornecedor findById(Long id) {
        return find("id", id).firstResult();
    }

    // Método para listar todos os fornecedores
    public List<Fornecedor> findAllFornecedores() {
        return listAll(); // Método fornecido pelo Panache
    }

    // Novo método para encontrar fornecedores por uma coleção de IDs
    public List<Fornecedor> findAllByIds(Set<Long> ids) {
        return find("id IN (?1)", ids).list();
    }
}
