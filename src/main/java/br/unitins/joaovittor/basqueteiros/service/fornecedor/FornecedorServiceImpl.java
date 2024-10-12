package br.unitins.joaovittor.basqueteiros.service.fornecedor;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
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
    public FornecedorResponseDTO insert(FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedorRepository.persist(fornecedor);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    public FornecedorResponseDTO findById(Long id) {
        Fornecedor fornecedor = obterFornecedorPorId(id);
        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> findAll() {
        return fornecedorRepository.findAll().stream()
                .map(FornecedorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    private Fornecedor obterFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Fornecedor não encontrado com o ID: " + id));
    }

    @Override
    @Transactional
    public FornecedorResponseDTO update(Long id, @Valid FornecedorDTO fornecedorDTO) {
        Fornecedor fornecedor = obterFornecedorPorId(id);
        fornecedor.setNome(fornecedorDTO.nome());
        fornecedor.setCnpj(fornecedorDTO.cnpj());
        fornecedor.setEndereco(fornecedorDTO.endereco());
        fornecedor.setEmail(fornecedorDTO.email());
        fornecedor.setListaTelefone(fornecedorDTO.telefone());

        // Obtendo os produtos a partir dos IDs
        List<Tenis> produtosFornecidos = tenisRepository.findByIds(fornecedorDTO.produtosFornecidos());
        fornecedor.setProdutosFornecidos(produtosFornecidos);

        fornecedorRepository.persist(fornecedor);

        return FornecedorResponseDTO.valueOf(fornecedor);
    }

    @Override
    public List<FornecedorResponseDTO> findByNome(String nome) {
        return fornecedorRepository.findByNome(nome).stream()
                .map(FornecedorResponseDTO::valueOf)
                .collect(Collectors.toList());
    }
}
