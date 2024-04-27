package br.unitins.joaovittor.basqueteiros.Fornecedor.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Fornecedor.model.Fornecedor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor>{
    
    public List<Fornecedor> findByNomeEmpresa(String nomeEmpresa){
        return find("UPPER(nomeEmpresa) LIKE ?1", "%"+ nomeEmpresa.toUpperCase() + "%").list();  
    }

    public Fornecedor findByNomeEmpresaCompleto(String nomeEmpresa){
        return find("UPPER(nomeEmpresa) LIKE ?1", "%"+ nomeEmpresa.toUpperCase() + "%").firstResult();  
    }

}
