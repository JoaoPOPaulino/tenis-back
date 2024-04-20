package br.unitins.joaovittor.basqueteiros.Fornecedor.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Fornecedor.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor>{
    
    public List<Fornecedor> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

}
