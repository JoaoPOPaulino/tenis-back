package br.unitins.joaovittor.basqueteiros.Endereco.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.Endereco.model.Endereco;
import br.unitins.joaovittor.basqueteiros.Endereco.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService{

    @Inject
    EnderecoRepository repository;

    @Override
    @Transactional
    public EnderecoResponseDTO create(@Valid EnderecoDTO dto) {
        Endereco endereco = new Endereco();

        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setComplemento(dto.complemento());

        repository.persist(endereco);

        return EnderecoResponseDTO.valueof(endereco);
    }

    @Override
    @Transactional
    public void update(Long id, EnderecoDTO dto) {
        Endereco endereco = repository.findById(id);

        endereco.setCep(dto.cep());
        endereco.setRua(dto.rua());
        endereco.setComplemento(dto.complemento());
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<EnderecoResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(e -> EnderecoResponseDTO.valueof(e)).toList();
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        Endereco fornecedor = repository.findById(id);
        if(fornecedor != null)
            return EnderecoResponseDTO.valueof(fornecedor);
        return null;
    }

    @Override
    public EnderecoResponseDTO findByCep(String cep) {

        Endereco endereco = repository.findByCep(cep);

        if(endereco != null)
            return EnderecoResponseDTO.valueof(endereco);
        return null;
    }
    
}
