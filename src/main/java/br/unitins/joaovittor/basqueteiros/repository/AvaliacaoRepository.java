package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.avaliacao.Avaliacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AvaliacaoRepository implements PanacheRepository<Avaliacao> {

    // Método para encontrar avaliações pelo texto
    public List<Avaliacao> findByTexto(String texto) {
        return find("UPPER(texto) LIKE UPPER(?1)", "%" + texto + "%").list();
    }

    // Método para encontrar avaliações pelo ID do usuário
    public List<Avaliacao> findByUsuarioId(Long usuarioId) {
        return find("usuario.id = ?1", usuarioId).list();
    }

    // Método para encontrar uma avaliação pelo ID
    public Avaliacao findById(Long id) {
        return find("id", id).firstResult();
    }

    // Método para listar todas as avaliações
    public List<Avaliacao> findAllAvaliacoes() {
        return listAll(); // Método fornecido pelo Panache
    }
}
