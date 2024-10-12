package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.repository.EstadoRepository;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
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
    EstadoRepository estadoRepository;

    @Inject
    TenisRepository tenisRepository;

    @Inject
    Validator validator;

    @Override
    @Transactional
    public FornecedorResponseDTO create(FornecedorDTO dto) throws ConstraintViolationException {
        validate(dto);

        Fornecedor entity = new Fornecedor();

        entity.setNome(dto.nome());

        if (dto.enderecos() != null && !dto.enderecos().isEmpty()) {
            entity.setEndereco(new ArrayList<Endereco>());

            for (EnderecoDTO enderecoDTO : dto.enderecos()) {
                Endereco endereco = new Endereco();
                endereco.setNumero(enderecoDTO.numero());
                endereco.setCep(enderecoDTO.cep());
                endereco.setQuadra(enderecoDTO.quadra());
                endereco.setRua(enderecoDTO.rua());
                endereco.setComplemento(enderecoDTO.complemento());
                entity.getEndereco().add(endereco);
            }
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(Long id, FornecedorDTO dto) throws ConstraintViolationException {
        validate(dto);

        Fornecedor entity = fornecedorRepository.findById(id);
        entity.setNome(dto.nome());

        List<Endereco> enderecos = entity.getEndereco();
        List<EnderecoDTO> enderecosDTO = dto.enderecos();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean foundEndereco = false;
            for (Endereco endereco : enderecos) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setCep(enderecoDTO.cep());
                    endereco.setQuadra(enderecoDTO.quadra());
                    endereco.setRua(enderecoDTO.rua());
                    endereco.setComplemento(enderecoDTO.complemento());
                    entity.getEndereco().add(endereco);
                    foundEndereco = true;
                    break;
                }
            }

            if (!foundEndereco) {
                Endereco endereco = new Endereco();
                endereco.setNumero(enderecoDTO.numero());
                endereco.setCep(enderecoDTO.cep());
                endereco.setQuadra(enderecoDTO.quadra());
                endereco.setRua(enderecoDTO.rua());
                endereco.setComplemento(enderecoDTO.complemento());
                entity.getEndereco().add(endereco);
            }
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        fornecedorRepository.deleteById(id);
    }

    @Override
    public List<FornecedorResponseDTO> findAll(int page, int pageSize) {
        List<Fornecedor> list = fornecedorRepository.findAll().page(page, pageSize).list();

        return list.stream().map(e -> FornecedorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrada.");
        }

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Fornecedor> list = fornecedorRepository.findByNome(nome).page(page, pageSize).list();

        return list.stream().map(e -> FornecedorResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FornecedorResponseDTO createEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO) {
        Fornecedor entity = fornecedorRepository.findById(fornecedorId);

        if (entity == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        List<Endereco> enderecos = entity.getEndereco();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            Endereco endereco = new Endereco();
            endereco.setNumero(enderecoDTO.numero());
            endereco.setCep(enderecoDTO.cep());
            endereco.setQuadra(enderecoDTO.quadra());
            endereco.setRua(enderecoDTO.rua());
            endereco.setComplemento(enderecoDTO.complemento());
            enderecos.add(endereco);
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO updateEnderecos(Long fornecedorId, List<EnderecoDTO> enderecosDTO) {
        Fornecedor entity = fornecedorRepository.findById(fornecedorId);

        if (entity == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        List<Endereco> enderecos = entity.getEndereco();

        Iterator<Endereco> iterator = enderecos.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            boolean foundEndereco = false;

            for (EnderecoDTO enderecoDTO : enderecosDTO) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setCep(enderecoDTO.cep());
                    endereco.setQuadra(enderecoDTO.quadra());
                    endereco.setRua(enderecoDTO.rua());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setCep(enderecoDTO.cep());

                    foundEndereco = true;

                    break;
                }
            }

            if (!foundEndereco) {
                iterator.remove();
            }
        }

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            boolean foundEndereco = false;
            for (Endereco endereco : enderecos) {
                if (endereco.getId().equals(enderecoDTO.id())) {
                    endereco.setNumero(enderecoDTO.numero());
                    endereco.setCep(enderecoDTO.cep());
                    endereco.setQuadra(enderecoDTO.quadra());
                    endereco.setRua(enderecoDTO.rua());
                    endereco.setComplemento(enderecoDTO.complemento());
                    endereco.setCep(enderecoDTO.cep());

                    foundEndereco = true;

                    break;
                }
            }

            if (!foundEndereco) {
                Endereco endereco = new Endereco();
                endereco.setNumero(enderecoDTO.numero());
                endereco.setCep(enderecoDTO.cep());
                endereco.setQuadra(enderecoDTO.quadra());
                endereco.setRua(enderecoDTO.rua());
                endereco.setComplemento(enderecoDTO.complemento());
                entity.getEndereco().add(endereco);
            }
        }

        fornecedorRepository.persist(entity);

        return FornecedorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public FornecedorResponseDTO removeEnderecos(Long fornecedorId, Long enderecoId) {
        Fornecedor usuario = fornecedorRepository.findById(fornecedorId);

        if (usuario == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        List<Endereco> enderecos = usuario.getEndereco();

        Iterator<Endereco> iterator = enderecos.iterator();
        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            if (endereco.getId().equals(enderecoId)) {
                iterator.remove();
                fornecedorRepository.persist(usuario);
                return FornecedorResponseDTO.valueOf(usuario);
            }
        }

        throw new NotFoundException("Endereco não encontrado para este usuário.");
    }

    @Override
    public long count() {
        return fornecedorRepository.count();
    }

    @Override
    public long countByNome(String nome) {
        return fornecedorRepository.findByNome(nome).count();
    }

    private void validate(FornecedorDTO fornecedorDTO) throws ConstraintViolationException {
        Set<ConstraintViolation<FornecedorDTO>> violations = validator.validate(fornecedorDTO);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
