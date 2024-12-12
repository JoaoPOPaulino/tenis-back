package br.unitins.joaovittor.basqueteiros.service.cidade;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeDTO;
import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;
import br.unitins.joaovittor.basqueteiros.repository.CidadeRepository;
import br.unitins.joaovittor.basqueteiros.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    EstadoRepository estadoRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public CidadeResponseDTO create(@Valid CidadeDTO dto) {
        // Validações
        validate(dto);
        validarEstadoExiste(dto.idEstado());

        // Criação da cidade
        Cidade cidade = new Cidade();
        updateCidadeFromDTO(cidade, dto);

        cidadeRepository.persist(cidade);

        return CidadeResponseDTO.valueOf(cidade);
    }

    @Override
    @Transactional
    public CidadeResponseDTO update(Long id, CidadeDTO dto) {
        // Validações
        validate(dto);
        validarEstadoExiste(dto.idEstado());

        // Atualização
        Cidade cidade = findCidadeOrThrow(id);
        updateCidadeFromDTO(cidade, dto);

        cidadeRepository.persist(cidade);

        return CidadeResponseDTO.valueOf(cidade);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!cidadeRepository.deleteById(id)) {
            throw new NotFoundException("Cidade não encontrada");
        }
    }

    @Override
    public List<CidadeResponseDTO> findAll(int page, int pageSize) {
        return cidadeRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(CidadeResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        return CidadeResponseDTO.valueOf(findCidadeOrThrow(id));
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome, int page, int pageSize) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        return cidadeRepository.findByNome(nome)
                .page(page, pageSize)
                .list()
                .stream()
                .map(CidadeResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cidadeRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
        return cidadeRepository.findByNome(nome).count();
    }

    // Métodos auxiliares
    private void validate(CidadeDTO dto) {
        Set<ConstraintViolation<CidadeDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void validarEstadoExiste(Long idEstado) {
        if (!estadoRepository.findByIdOptional(idEstado).isPresent()) {
            throw new NotFoundException("Estado não encontrado");
        }
    }

    private Cidade findCidadeOrThrow(Long id) {
        return cidadeRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
    }

    private void updateCidadeFromDTO(Cidade cidade, CidadeDTO dto) {
        cidade.setNome(dto.nome().trim().toUpperCase());
        cidade.setEstado(estadoRepository.findById(dto.idEstado()));
    }
}
