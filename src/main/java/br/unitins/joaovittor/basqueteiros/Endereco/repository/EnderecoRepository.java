package br.unitins.joaovittor.basqueteiros.Endereco.repository;

import br.unitins.joaovittor.basqueteiros.Endereco.model.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco>{
    
    public Endereco findByCep(String cep){
        return find("cep = ?1", cep).firstResult();  
    }

}
