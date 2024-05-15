package br.unitins.joaovittor.basqueteiros.Usuario.repository;

import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    
    public Usuario findByUsername(String username){
        return find("UPPER(username) = ?1", username).firstResult();  
    }

}
