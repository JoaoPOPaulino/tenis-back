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
    public AvaliacaoResponseDTO create(AvaliacaoDTO dto) throws ConstraintViolationException {
        validate(dto);

        Avaliacao entity = new Avaliacao();

        entity.setTenis(tenisRepository.findById(dto.idTenis()));
        entity.setConteudo(dto.conteudo());

        avaliacaoRepository.persist(entity);

        return AvaliacaoResponseDTO.valueOf(entity);
    }

    private void validate(AvaliacaoDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<AvaliacaoDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Override
    public AvaliacaoResponseDTO update(Long id, AvaliacaoDTO dto) throws ConstraintViolationException {
        validate(dto);

        Avaliacao entity = avaliacaoRepository.findById(id);

        entity.setTenis(tenisRepository.findById(dto.idTenis()));
        entity.setConteudo(dto.conteudo());

        return AvaliacaoResponseDTO.valueOf(entity);
    }

    @Override
    public AvaliacaoResponseDTO findById(Long id) {
        Avaliacao avaliacao = avaliacaoRepository.findById(id);
        if (avaliacao == null) {
            throw new NotFoundException("Avaliacao não encontrada.");
        }

        return AvaliacaoResponseDTO.valueOf(avaliacao);
    }

    @Override
    public List<AvaliacaoResponseDTO> findAll(int page, int pageSize) {
        List<Avaliacao> list = avaliacaoRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> AvaliacaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        avaliacaoRepository.deleteById(id);
    }
}
