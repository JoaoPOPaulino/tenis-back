package br.unitins.joaovittor.basqueteiros.service.estado;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoDTO;
import br.unitins.joaovittor.basqueteiros.dto.estado.EstadoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.estado.Estado;
import br.unitins.joaovittor.basqueteiros.repository.EstadoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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
    public EstadoResponseDTO create(@Valid EstadoDTO estadoDTO) throws ConstraintViolationException {
        Estado entity = new Estado();

        entity.setNome(estadoDTO.nome());
        entity.setSigla(estadoDTO.sigla());

        estadoRepository.persist(entity);

        return EstadoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public EstadoResponseDTO update(Long id, EstadoDTO estadoDTO) throws ConstraintViolationException {
        Estado entity = estadoRepository.findById(id);

        entity.setNome(estadoDTO.nome());
        entity.setSigla(estadoDTO.sigla());

        return EstadoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public List<EstadoResponseDTO> findAll(int page, int pageSize) {
        List<Estado> list = estadoRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> EstadoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null) {
            throw new NotFoundException("Estado nÃ£o encontrado.");
        }

        return EstadoResponseDTO.valueOf(estado);
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Estado> list = estadoRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> EstadoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return estadoRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return estadoRepository.findByNome(nome).count();
    }
}
