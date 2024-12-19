package br.unitins.joaovittor.basqueteiros.repository;

import java.util.Optional;

import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public PanacheQuery<Usuario> findByNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            return null;
        }
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

    public Usuario findByLogin(String login) {
        try {
            return find("login = ?1 ", login).singleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        try {
            return find("login = ?1 AND senha = ?2", login, senha).firstResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
