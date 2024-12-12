package br.unitins.joaovittor.basqueteiros.service.avaliacao;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.avaliacao.AvaliacaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.avaliacao.Avaliacao;
import br.unitins.joaovittor.basqueteiros.repository.AvaliacaoRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Inject
    AvaliacaoRepository avaliacaoRepository;

    @Inject
    TenisRepository tenisRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public AvaliacaoResponseDTO create(AvaliacaoDTO dto) {
        // Validações
        validate(dto);
        validarTenisExiste(dto.idTenis());

        // Criação da entidade
        Avaliacao avaliacao = new Avaliacao();
        updateAvaliacaoFromDTO(avaliacao, dto);

        // Persistência
        avaliacaoRepository.persist(avaliacao);

        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    @Transactional
    public AvaliacaoResponseDTO update(Long id, AvaliacaoDTO dto) {
        // Validações
        validate(dto);
        validarTenisExiste(dto.idTenis());

        // Busca e atualização
        Avaliacao avaliacao = findAvaliacaoOrThrow(id);
        updateAvaliacaoFromDTO(avaliacao, dto);

        // Persistência
        avaliacaoRepository.persist(avaliacao);

        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    public AvaliacaoResponseDTO findById(Long id) {
        return AvaliacaoResponseDTO.valueOf(findAvaliacaoOrThrow(id));
    }

    @Override
    public List<AvaliacaoResponseDTO> findAll(int page, int pageSize) {
        return avaliacaoRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(AvaliacaoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!avaliacaoRepository.deleteById(id)) {
            throw new NotFoundException("Avaliação não encontrada");
        }
    }

    private void validate(AvaliacaoDTO dto) {
        Set<ConstraintViolation<AvaliacaoDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void validarTenisExiste(Long idTenis) {
        if (!tenisRepository.findByIdOptional(idTenis).isPresent()) {
            throw new NotFoundException("Tênis não encontrado");
        }
    }

    private Avaliacao findAvaliacaoOrThrow(Long id) {
        return avaliacaoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Avaliação não encontrada"));
    }

    private void updateAvaliacaoFromDTO(Avaliacao avaliacao, AvaliacaoDTO dto) {
        avaliacao.setTenis(tenisRepository.findById(dto.idTenis()));
        avaliacao.setConteudo(dto.conteudo());
    }
}
