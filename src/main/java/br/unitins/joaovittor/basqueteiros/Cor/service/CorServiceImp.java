package br.unitins.joaovittor.basqueteiros.Cor.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cor.dto.CorDTO;
import br.unitins.joaovittor.basqueteiros.Cor.dto.CorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Cor.model.Cor;
import br.unitins.joaovittor.basqueteiros.Cor.repository.CorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class CorServiceImp implements CorService {
    
    @Inject
    CorRepository repository;

    @Override
    @Transactional
    public CorResponseDTO create(@Valid CorDTO dto) {
        Cor Cor = new Cor();
        Cor.setNome(dto.nome());

        repository.persist(Cor);
        return CorResponseDTO.valueof(Cor);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, CorDTO dto) {
        Cor Cor = repository.findById(id);
        Cor.setNome(dto.nome());
        
    }

    @Override
    public List<CorResponseDTO> findAll() {
        return repository.findAll()
                                .stream()
                                .map(e -> CorResponseDTO.valueof(e)).toList();
    }

    @Override
    public CorResponseDTO findById(Long id) {
        return CorResponseDTO.valueof(repository.findById(id));
    }

    @Override
    public List<CorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                                        .stream()
                                        .map(e -> CorResponseDTO.valueof(e)).toList();
    }
}
