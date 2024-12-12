package br.unitins.joaovittor.basqueteiros.service.estado;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoDTO;
import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.estado.Estado;
import br.unitins.joaovittor.basqueteiros.repository.EstadoRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public EstadoResponseDTO create(@Valid EstadoDTO dto) {
        // Validações
        validateEstado(dto);
        validarEstadoUnico(dto);

        // Criação
        Estado estado = new Estado();
        updateEstadoFromDTO(estado, dto);

        estadoRepository.persist(estado);

        return EstadoResponseDTO.valueOf(estado);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoDTO dto) {
        // Validações
        validateEstado(dto);

        // Atualização
        Estado estado = findEstadoOrThrow(id);

        // Validar se novo nome/sigla não conflita com outro estado
        if (!estado.getNome().equalsIgnoreCase(dto.nome())) {
            validarEstadoUnico(dto);
        }

        updateEstadoFromDTO(estado, dto);
        estadoRepository.persist(estado);

        return EstadoResponseDTO.valueOf(estado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Estado estado = findEstadoOrThrow(id);

        // Verificar se o estado pode ser excluído (não tem cidades vinculadas)
        // Isso seria implementado se houver repository de cidade
        if (!estadoRepository.deleteById(id)) {
            throw new NotFoundException("Estado não encontrado");
        }
    }

    @Override
    public List<EstadoResponseDTO> findAll(int page, int pageSize) {
        return estadoRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(EstadoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        return EstadoResponseDTO.valueOf(findEstadoOrThrow(id));
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome, int page, int pageSize) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        return estadoRepository.findByNome(nome)
                .page(page, pageSize)
                .list()
                .stream()
                .map(EstadoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return estadoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return estadoRepository.findByNome(nome).count();
    }

    // Métodos auxiliares
    private void validateEstado(EstadoDTO dto) {
        Set<ConstraintViolation<EstadoDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void validarEstadoUnico(EstadoDTO dto) {
        // Verifica se já existe estado com mesmo nome ou sigla
        if (estadoRepository.findByNome(dto.nome()).count() > 0) {
            throw new ValidationException("nome", "Estado já cadastrado com este nome");
        }
        // Aqui seria bom ter um método no repository para buscar por sigla
    }

    private Estado findEstadoOrThrow(Long id) {
        return estadoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Estado não encontrado"));
    }

    private void updateEstadoFromDTO(Estado estado, EstadoDTO dto) {
        estado.setNome(dto.nome().trim().toUpperCase());
        estado.setSigla(dto.sigla().trim().toUpperCase());
    }
}
