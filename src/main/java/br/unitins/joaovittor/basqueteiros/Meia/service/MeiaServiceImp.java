package br.unitins.joaovittor.basqueteiros.Meia.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cor.repository.CorRepository;
import br.unitins.joaovittor.basqueteiros.Fornecedor.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.Marca.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaDTO;
import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Meia.model.Meia;
import br.unitins.joaovittor.basqueteiros.Meia.repository.MeiaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class MeiaServiceImp implements MeiaService {

    @Inject
    MeiaRepository repository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    CorRepository corRepository;

    @Override
    @Transactional
    public MeiaResponseDTO create(@Valid MeiaDTO dto) {
        Meia meia = new Meia();
        meia.setNome(dto.nome());
        meia.setDescricao(dto.descricao());
        meia.setQtdPares(dto.qtdPares());
        meia.setQuantidade(dto.quantidade());
        meia.setPrecoCompra(dto.precoCompra());
        meia.setPrecoVenda(dto.precoVenda());
        meia.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        meia.setMarca(marcaRepository.findById(dto.idMarca()));
        meia.setCor(corRepository.findById(dto.idCor()));

        repository.persist(meia);
        return MeiaResponseDTO.parse(meia);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, MeiaDTO dto) {
        Meia meia = repository.findById(id);
        meia.setNome(dto.nome());
        meia.setDescricao(dto.descricao());
        meia.setQtdPares(dto.qtdPares());
        meia.setQuantidade(dto.quantidade());
        meia.setPrecoCompra(dto.precoCompra());
        meia.setPrecoVenda(dto.precoVenda());
        meia.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        meia.setMarca(marcaRepository.findById(dto.idMarca()));
        meia.setCor(corRepository.findById(dto.idCor()));
    }

    @Override
    public List<MeiaResponseDTO> findAll() {
        return repository.findAll()
        .stream()
        .map(e -> MeiaResponseDTO.parse(e)).toList();
    }

    @Override
    public MeiaResponseDTO findById(Long id) {
        return MeiaResponseDTO.parse(repository.findById(id));
    }

    @Override
    public List<MeiaResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
        .stream()
        .map(e -> MeiaResponseDTO.parse(e)).toList();
    }
}
