package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.ArrayList;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService {

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    @Transactional
    public FornecedorResponseDTO insert(@Valid FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedor.setEndereco(fornecedorDTO.endereco());
        fornecedor.setEmail(fornecedorDTO.email());
        fornecedor.setListaTelefone(fornecedorDTO.telefone());
        fornecedorRepository.persist(fornecedor);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!fornecedorRepository.deleteById(id)) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome) {
        List<Fornecedor> fornecedores = fornecedorRepository.findByNome(nome);
        List<FornecedorResponseDTO> dtos = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedores) {
            dtos.add(FornecedorResponseDTO.valueOf(fornecedor));
        }
        return dtos;
    }

    @Override
    public List<FornecedorResponseDTO> findAll() {
        List<Fornecedor> fornecedores = fornecedorRepository.findAllFornecedores();
        List<FornecedorResponseDTO> dtos = new ArrayList<>();
        for (Fornecedor fornecedor : fornecedores) {
            dtos.add(FornecedorResponseDTO.valueOf(fornecedor));
        }
        return dtos;
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(Long id, @Valid FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = fornecedorRepository.findById(id);
        if (fornecedor == null) {
            throw new NotFoundException("Fornecedor não encontrado.");
        }

        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedor.setEndereco(fornecedorDTO.endereco());
        fornecedor.setEmail(fornecedorDTO.email());
        fornecedor.setListaTelefone(fornecedorDTO.telefone());
        fornecedorRepository.persist(fornecedor);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }
}
