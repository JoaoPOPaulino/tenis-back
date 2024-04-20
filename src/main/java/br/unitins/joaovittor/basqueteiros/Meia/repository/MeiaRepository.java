package br.unitins.joaovittor.basqueteiros.Meia.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Meia.model.Meia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MeiaRepository implements PanacheRepository<Meia>{
    
    public List<Meia> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }
}
