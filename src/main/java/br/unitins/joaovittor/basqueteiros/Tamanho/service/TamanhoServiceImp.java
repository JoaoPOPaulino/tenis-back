package br.unitins.joaovittor.basqueteiros.Tamanho.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.model.Tamanho;
import br.unitins.joaovittor.basqueteiros.Tamanho.repository.TamanhoRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class TamanhoServiceImp implements TamanhoService{

    @Inject
    TamanhoRepository repository;

    @Override
    @Transactional
    public TamanhoResponseDTO create(@Valid TamanhoDTO dto) {
        Tamanho tamanho = new Tamanho();

        verificarNumeracao(dto.numeracao());

        tamanho.setNumeracao(dto.numeracao());
        tamanho.setTamanhoEmCm(dto.tamanhoEmCm());

        repository.persist(tamanho);
        return TamanhoResponseDTO.valueof(tamanho);
    }

    public void verificarNumeracao(Integer numeracao){
        Tamanho tamanho = repository.findByNumeracaoFirstResult(numeracao);
        if(tamanho != null)
            throw new ValidationException("numeracao", "A numeracao '"+numeracao+"' ja existe");
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, TamanhoDTO dto) {
        Tamanho tamanho = repository.findById(id);
        tamanho.setNumeracao(dto.numeracao());
        tamanho.setTamanhoEmCm(dto.tamanhoEmCm());
    }

    @Override
    public List<TamanhoResponseDTO> findAll() {
        return repository.findAll()
        .stream()
        .map(e -> TamanhoResponseDTO.valueof(e)).toList();
    }

    @Override
    public TamanhoResponseDTO findById(Long id) {
        return TamanhoResponseDTO.valueof(repository.findById(id));
    }

    @Override
    public List<TamanhoResponseDTO> findByNumeracao(Integer numeracao) {
            return repository.findByNumeracao(numeracao)
            .stream()
            .map(e -> TamanhoResponseDTO.valueof(e)).toList();
    }
    
}
