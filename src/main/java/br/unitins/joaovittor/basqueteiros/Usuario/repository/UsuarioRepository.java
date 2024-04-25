package br.unitins.joaovittor.basqueteiros.Usuario.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario>{
    
    public List<Usuario> findByNome(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Usuario findByNomeCompleto(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").firstResult();
    }

    public List<Usuario> findByLogin(String login){
        return find("UPPER(login) LIKE ?1", "%"+ login.toUpperCase() + "%").list();  
    }

    public Usuario findByLoginCompleto(String login){
        return find("UPPER(login) LIKE ?1", "%"+ login.toUpperCase() + "%").firstResult();
    }

    public Usuario findByCpf(String cpf){
        return find("UPPER(cpf) LIKE ?1", "%"+ cpf + "%").singleResult(); 
    }

}
