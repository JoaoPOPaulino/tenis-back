package br.unitins.topicos2.basqueteiros.PessoaFisica.repository;

import br.unitins.topicos2.basqueteiros.PessoaFisica.model.PessoaFisica;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica>{
    
}
