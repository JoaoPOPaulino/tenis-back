package br.unitins.joaovittor.basqueteiros.Tamanho.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Tamanho.model.Tamanho;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TamanhoRepository implements PanacheRepository<Tamanho>{
    
    public List<Tamanho> findByNumeracao(Integer numeracao){
        return find("numeracao = ?1", numeracao).list();  
    }

    public List<Tamanho> findByTamanhoEmCm(String tamanhoEmCm){
        return find("tamanhoEmCm LIKE ?1", "%"+ tamanhoEmCm + "%").list();  
    }
}
