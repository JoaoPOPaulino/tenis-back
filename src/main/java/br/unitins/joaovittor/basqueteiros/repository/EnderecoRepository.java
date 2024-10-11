package br.unitins.joaovittor.basqueteiros.repository;



import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {
    
    // Buscar endereços por cidade e estado
    public List<Endereco> findByCidadeAndEstado(String cidade, String estado) {
        return find("cidade = ?1 and estado = ?2", cidade, estado).list();
    }

    // Buscar endereços por nome da rua (com parte do nome)
    public List<Endereco> findByRua(String rua) {
        return find("rua like ?1", "%" + rua + "%").list();
    }

    // Buscar endereços por número
    public List<Endereco> findByNumero(Integer numero) {
        return find("numero = ?1", numero).list();
    }

    // Buscar endereços por estado
    public List<Endereco> findByEstado(String estado) {
        return find("estado = ?1", estado).list();
    }

    // Buscar um endereço completo por estado, cidade, rua e número
    public Endereco findByFullAddress(String estado, String cidade, String rua, Integer numero) {
        return find("estado = ?1 and cidade = ?2 and rua = ?3 and numero = ?4", estado, cidade, rua, numero).firstResult();
    }

    // Buscar endereços por quadra
    public List<Endereco> findByQuadra(String quadra) {
        return find("quadra = ?1", quadra).list();
    }
}