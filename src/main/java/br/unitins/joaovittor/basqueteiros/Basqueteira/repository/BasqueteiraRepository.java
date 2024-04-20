package br.unitins.joaovittor.basqueteiros.Basqueteira.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Basqueteira.model.Basqueteira;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BasqueteiraRepository implements PanacheRepository<Basqueteira> {
    
    public List<Basqueteira> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }
}
