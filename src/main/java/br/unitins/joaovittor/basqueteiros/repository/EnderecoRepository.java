package br.unitins.joaovittor.basqueteiros.repository;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {

    public List<Endereco> findByUsuario(Long usuarioId) {
        return list("usuario.id", usuarioId);
    }

    public List<Endereco> findByCidade(Long cidadeId) {
        return list("cidade.id", cidadeId);
    }
}
