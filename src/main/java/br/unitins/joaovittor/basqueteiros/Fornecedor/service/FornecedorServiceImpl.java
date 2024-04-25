package br.unitins.joaovittor.basqueteiros.Fornecedor.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Fornecedor.model.Fornecedor;
import br.unitins.joaovittor.basqueteiros.Fornecedor.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService{

    @Inject
    FornecedorRepository repository;

    @Override
    @Transactional
    public FornecedorResponseDTO create(@Valid FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();

        // ta dando erro ao cadastrar

        verificarNome(dto.nome());

        fornecedor.setNomeEmpresa(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());

        repository.persist(fornecedor);

        return FornecedorResponseDTO.valueof(fornecedor);
    }

    public void verificarNome(String nome){
        Fornecedor fornecedor = repository.findByNomeCompleto(nome);
        if(fornecedor != null)
            throw new ValidationException("nome", "O nome '"+nome+"' ja existe");
    }

    @Override
    @Transactional
    public void update(Long id, FornecedorDTO dto) {
        Fornecedor fornecedor = repository.findById(id);

        fornecedor.setNomeEmpresa(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    public List<FornecedorResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(e -> FornecedorResponseDTO.valueof(e)).toList();
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        return FornecedorResponseDTO.valueof(repository.findById(id));
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
        .stream()
        .map(e -> FornecedorResponseDTO.valueof(e)).toList();
    }
    
}
