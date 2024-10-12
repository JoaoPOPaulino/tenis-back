package br.unitins.joaovittor.basqueteiros.service.avaliacao;

import java.util.List;
import java.util.Set;

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
    }

    @Override
    public List<AvaliacaoResponseDTO> findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<AvaliacaoResponseDTO> findAll(int page, int pageSize) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
