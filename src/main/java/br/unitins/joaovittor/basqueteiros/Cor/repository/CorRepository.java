package br.unitins.joaovittor.basqueteiros.Cor.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cor.model.Cor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CorRepository implements PanacheRepository<Cor> {
    
    public List<Cor> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Cor findByNomeCompleto(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").firstResult();  
    }
}
