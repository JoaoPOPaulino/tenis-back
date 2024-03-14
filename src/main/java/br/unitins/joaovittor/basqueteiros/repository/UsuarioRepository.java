package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    
    public List<Usuario> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

}
