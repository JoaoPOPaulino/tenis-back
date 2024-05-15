package br.unitins.joaovittor.basqueteiros.PessoaFisica.repository;

import br.unitins.joaovittor.basqueteiros.PessoaFisica.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica>{
    
}
