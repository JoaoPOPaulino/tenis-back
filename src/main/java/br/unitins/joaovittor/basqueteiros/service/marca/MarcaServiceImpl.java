package br.unitins.joaovittor.basqueteiros.service.marca;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MarcaServiceImpl implements MarcaService {

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public MarcaResponseDTO create(MarcaDTO dto) {
        // Validações
        validate(dto);
        validateNomeUnico(dto.nome());

        // Criação
        Marca marca = new Marca();
        updateMarcaFromDTO(marca, dto);

        marcaRepository.persist(marca);

        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public MarcaResponseDTO update(Long id, MarcaDTO dto) {
        validate(dto);

        Marca marca = findMarcaOrThrow(id);

        // Validar se o novo nome não conflita com outra marca
        if (!marca.getNome().equalsIgnoreCase(dto.nome())) {
            validateNomeUnico(dto.nome());
        }

        updateMarcaFromDTO(marca, dto);
        marcaRepository.persist(marca);

        return MarcaResponseDTO.valueOf(marca);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!marcaRepository.deleteById(id)) {
            throw new NotFoundException("Marca não encontrada");
        }
    }

    @Override
    public MarcaResponseDTO findById(Long id) {
        return MarcaResponseDTO.valueOf(findMarcaOrThrow(id));
    }

    @Override
    public List<MarcaResponseDTO> findAll(int page, int pageSize) {
        return marcaRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(MarcaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<MarcaResponseDTO> findByNome(String nome, int page, int pageSize) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        return marcaRepository.findByNome(nome)
                .page(page, pageSize)
                .list()
                .stream()
                .map(MarcaResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return marcaRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return marcaRepository.findByNome(nome).count();
    }

    // Métodos auxiliares
    private void validate(MarcaDTO dto) {
        Set<ConstraintViolation<MarcaDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void validateNomeUnico(String nome) {
        if (marcaRepository.findByNome(nome).count() > 0) {
            throw new ValidationException("nome", "Marca já existe com este nome");
        }
    }

    private Marca findMarcaOrThrow(Long id) {
        return marcaRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Marca não encontrada"));
    }

    private void updateMarcaFromDTO(Marca marca, MarcaDTO dto) {
        marca.setNome(dto.nome().trim().toUpperCase());
    }
}
