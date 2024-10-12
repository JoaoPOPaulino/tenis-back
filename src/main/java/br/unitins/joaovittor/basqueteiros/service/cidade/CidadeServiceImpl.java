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
    public CidadeResponseDTO create(@Valid CidadeDTO dto) throws ConstraintViolationException {
        validate(dto);

        Cidade entity = new Cidade();

        entity.setNome(dto.nome());
        entity.setEstado(estadoRepository.findById(dto.idEstado()));

        cidadeRepository.persist(entity);

        return CidadeResponseDTO.valueOf(entity);

    }

    private void validate(CidadeDTO cidadeDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<CidadeDTO>> violations = validator.validate(cidadeDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    @Override
    @Transactional
    public CidadeResponseDTO update(Long id, CidadeDTO cidadeDTO) throws ConstraintViolationException {
        validate(cidadeDTO);

        Cidade entity = cidadeRepository.findById(id);

        entity.setNome(cidadeDTO.nome());
        entity.setEstado(estadoRepository.findById(cidadeDTO.idEstado()));

        return CidadeResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        cidadeRepository.deleteById(id);
    }

    @Override
    public List<CidadeResponseDTO> findAll(int page, int pageSize) {
        List<Cidade> list = cidadeRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> CidadeResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null) {
            throw new NotFoundException("Cidade nÃ£o encontrada.");
        }

        return CidadeResponseDTO.valueOf(cidade);
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Cidade> list = cidadeRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> CidadeResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return cidadeRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return cidadeRepository.findByNome(nome).count();
    }
}
