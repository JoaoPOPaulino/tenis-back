package br.unitins.joaovittor.basqueteiros.Funcionario.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Funcionario.model.Funcionario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FuncionarioRepository implements PanacheRepository<Funcionario> {
    
    public List<Funcionario> findByNome(String nome){
        return find("UPPER(pessoaFisica.nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();  
    }

    public Funcionario findByCpf(String cpf){
        return find("pessoaFisica.cpf = ?1", cpf).firstResult();
    }

    public Funcionario findByUsername(String username){
        return find("pessoaFisica.usuario.username = ?1", username).firstResult();
    }

    public Funcionario findByUsernameAndSenha(String username, String senha){
        return find("pessoaFisica.usuario.username = ?1 AND pessoaFisica.usuario.password = ?2", username, senha).firstResult();
    }

    public Funcionario findByIdUsuario(Long idUsuario) {
        return find("pessoaFisica.usuario.id = ?1", idUsuario).firstResult();
    }
    
}
