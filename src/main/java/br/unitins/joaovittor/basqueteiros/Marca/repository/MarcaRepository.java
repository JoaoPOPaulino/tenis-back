package br.unitins.joaovittor.basqueteiros.Marca.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Marca.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca>{
    
    public List<Marca> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Marca findByNomeCompleto(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").firstResult();  
    }
    
}
