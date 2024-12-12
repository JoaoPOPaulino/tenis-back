package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.List;
import java.util.Set;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    Validator validator;

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

        fornecedorRepository.persist(fornecedor);

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO createEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO) {
        Fornecedor fornecedor = findFornecedorOrThrow(fornecedorId);

        enderecosDTO.stream()
                .map(this::createEnderecoFromDTO)
                .forEach(fornecedor::addEndereco);

        fornecedorRepository.persist(fornecedor);

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO updateEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO) {
        Fornecedor fornecedor = findFornecedorOrThrow(fornecedorId);

        // Limpa a lista atual e adiciona os novos endereços
        fornecedor.getEnderecos().clear();
        enderecosDTO.stream()
                .map(this::createEnderecoFromDTO)
                .forEach(fornecedor::addEndereco);

        fornecedorRepository.persist(fornecedor);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO removeEnderecos(Long fornecedorId, Long enderecoId) {
        Fornecedor fornecedor = findFornecedorOrThrow(fornecedorId);

        boolean removed = fornecedor.getEnderecos().removeIf(
                endereco -> endereco.getId().equals(enderecoId)
        );

        if (!removed) {
            throw new NotFoundException("Endereço não encontrado");
        }

        fornecedorRepository.persist(fornecedor);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    private void updateFornecedorFromDTO(Fornecedor fornecedor, FornecedorDTO dto) {
        fornecedor.setNome(dto.nome());
        fornecedor.setCnpj(dto.cnpj());

        if (dto.enderecos() != null) {
            dto.enderecos().stream()
                    .map(this::createEnderecoFromDTO)
                    .forEach(fornecedor::addEndereco);
        }
    }

    // Métodos auxiliares
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

    private Endereco createEnderecoFromDTO(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setNumero(dto.numero());
        endereco.setCep(dto.cep());
        endereco.setQuadra(dto.quadra());
        endereco.setRua(dto.rua());
        endereco.setComplemento(dto.complemento());
        // Adicionar cidade se necessário
        return endereco;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<FornecedorResponseDTO> findAll(int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByNome'");
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }

    @Override
    public long countByNome(String nome) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countByNome'");
    }
}
