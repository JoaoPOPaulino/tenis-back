package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.repository.CidadeRepository;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    Validator validator;

    @Inject
    CidadeRepository cidadeRepository;

    @Override
    @Transactional
    public FornecedorResponseDTO create(FornecedorDTO dto) {
        validate(dto);

        Fornecedor fornecedor = new Fornecedor();
        updateFornecedorFromDTO(fornecedor, dto);

        fornecedorRepository.persist(fornecedor);

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(Long id, FornecedorDTO dto) {
        validate(dto);

        Fornecedor fornecedor = findFornecedorOrThrow(id);
        updateFornecedorFromDTO(fornecedor, dto);

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO updateEndereco(Long id, EnderecoDTO enderecoDTO) {
        Fornecedor fornecedor = findFornecedorOrThrow(id);
        fornecedor.setEndereco(createEnderecoFromDTO(enderecoDTO));

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Fornecedor fornecedor = findFornecedorOrThrow(id);
        fornecedorRepository.delete(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> findAll(int page, int pageSize) {
        return fornecedorRepository.findAll()
                .page(page, pageSize)
                .stream()
                .map(FornecedorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        return FornecedorResponseDTO.valueOf(findFornecedorOrThrow(id));
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize) {
        return fornecedorRepository.find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%")
                .page(page, pageSize)
                .stream()
                .map(FornecedorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return fornecedorRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return fornecedorRepository.count("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%");
    }

    private Endereco createEnderecoFromDTO(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setCep(dto.cep());
        endereco.setQuadra(dto.quadra());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());

        // Você precisa buscar a cidade pelo ID
        if (dto.idCidade() != null) {
            Cidade cidade = cidadeRepository.findById(dto.idCidade());
            endereco.setCidade(cidade);
        }

        // Como é um endereço de fornecedor, podemos assumir que é principal e ativo
        endereco.setPrincipal(true);
        endereco.setAtivo(true);

        return endereco;
    }

    private void validate(FornecedorDTO dto) {
        Set<ConstraintViolation<FornecedorDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private Fornecedor findFornecedorOrThrow(Long id) {
        return fornecedorRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
    }

    private void updateFornecedorFromDTO(Fornecedor fornecedor, FornecedorDTO dto) {
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());

        if (dto.endereco() != null) {
            Endereco endereco = createEnderecoFromDTO(dto.endereco());
            fornecedor.setEndereco(endereco);
        }
    }

}
