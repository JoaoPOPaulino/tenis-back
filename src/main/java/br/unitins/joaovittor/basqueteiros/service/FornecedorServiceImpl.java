package br.unitins.joaovittor.basqueteiros.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.Fornecedor;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
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
        fornecedor.setNomeEmpresa(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setDataCadastro(LocalDate.now());

        repository.persist(fornecedor);

        return FornecedorResponseDTO.parse(fornecedor);
    }

    @Override
    @Transactional
    public void update(Long id, FornecedorDTO dto) {
        Fornecedor fornecedor = repository.findById(id);

        fornecedor.setNomeEmpresa(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        // nao alterar data de cadastro
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<FornecedorResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(e -> FornecedorResponseDTO.parse(e)).toList();
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        return FornecedorResponseDTO.parse(repository.findById(id));
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
        .stream()
        .map(e -> FornecedorResponseDTO.parse(e)).toList();
    }
    
}
