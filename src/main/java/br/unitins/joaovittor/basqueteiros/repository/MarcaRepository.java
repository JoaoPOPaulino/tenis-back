package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {
    
    // Método para encontrar Marca pelo ID
    public Marca findById(Long id) {
        return find("id", id).firstResult();  // Panache já oferece find() para consultas
    }

    public List<Marca> findByNome(String nome){
        return find("UPPER(nome) LIKE UPPER(?1)" , "%" + nome +  "%").list();

    }



}