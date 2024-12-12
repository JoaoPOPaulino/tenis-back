package br.unitins.joaovittor.basqueteiros.service.tenis;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TenisServiceImpl implements TenisService {

    @Inject
    TenisRepository tenisRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public TenisResponseDTO create(TenisDTO dto) {
        validate(dto);
        validarMarcaExiste(dto.idMarca());

        Tenis tenis = new Tenis();
        updateTenisFromDTO(tenis, dto);

        tenisRepository.persist(tenis);

        return TenisResponseDTO.valueOf(tenis);
    }

    @Override
    @Transactional
    public TenisResponseDTO update(Long id, TenisDTO dto) {
        validate(dto);
        validarMarcaExiste(dto.idMarca());

        Tenis tenis = findTenisOrThrow(id);
        updateTenisFromDTO(tenis, dto);

        tenisRepository.persist(tenis);

        return TenisResponseDTO.valueOf(tenis);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!tenisRepository.deleteById(id)) {
            throw new NotFoundException("Tênis não encontrado");
        }
    }

    @Override
    public TenisResponseDTO findById(Long id) {
        return TenisResponseDTO.valueOf(findTenisOrThrow(id));
    }

    @Override
    public List<TenisResponseDTO> findByNome(String nome, int page, int pageSize) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        return tenisRepository.findByNome(nome)
                .page(page, pageSize)
                .list()
                .stream()
                .map(TenisResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenisResponseDTO> findByMarca(String marcaNome, int page, int pageSize) {
        if (marcaNome == null || marcaNome.isBlank()) {
            throw new IllegalArgumentException("Nome da marca não pode ser vazio");
        }

        return tenisRepository.find("marca.nome", marcaNome)
                .page(page, pageSize)
                .list()
                .stream()
                .map(TenisResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenisResponseDTO> findByPrecoRange(float minPreco, float maxPreco, int page, int pageSize) {
        if (minPreco < 0 || maxPreco < minPreco) {
            throw new IllegalArgumentException("Faixa de preço inválida");
        }

        return tenisRepository.find("preco BETWEEN ?1 AND ?2", minPreco, maxPreco)
                .page(page, pageSize)
                .list()
                .stream()
                .map(TenisResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<TenisResponseDTO> findAll(int page, int pageSize) {
        return tenisRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(TenisResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares
    private void validate(TenisDTO dto) {
        Set<ConstraintViolation<TenisDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void validarMarcaExiste(Long idMarca) {
        if (!marcaRepository.findByIdOptional(idMarca).isPresent()) {
            throw new NotFoundException("Marca não encontrada");
        }
    }

    private Tenis findTenisOrThrow(Long id) {
        return tenisRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Tênis não encontrado"));
    }

    private void updateTenisFromDTO(Tenis tenis, TenisDTO dto) {
        tenis.setDescricao(dto.descricao());
        tenis.setModelo(dto.modelo());
        tenis.setTamanho(dto.tamanho());
        tenis.setPreco(dto.preco().floatValue());
        tenis.setMarca(marcaRepository.findById(dto.idMarca()));
    }
}
