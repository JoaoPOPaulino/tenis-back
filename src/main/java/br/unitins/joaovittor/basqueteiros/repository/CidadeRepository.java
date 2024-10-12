package br.unitins.joaovittor.basqueteiros.repository;

import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class CidadeRepository implements PanacheRepository<Cidade> {

    public PanacheQuery<Cidade> findByNome(String nome) {
        if (nome == null) {
            return null;
        }
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }
}
