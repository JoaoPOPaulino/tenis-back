package br.unitins.joaovittor.basqueteiros.Basqueteira.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Basqueteira.dto.BasqueteiraDTO;
import br.unitins.joaovittor.basqueteiros.Basqueteira.dto.BasqueteiraResponseDTO;
import br.unitins.joaovittor.basqueteiros.Basqueteira.model.Basqueteira;
import br.unitins.joaovittor.basqueteiros.Basqueteira.repository.BasqueteiraRepository;
import br.unitins.joaovittor.basqueteiros.EnumTamanhoCano.model.TamanhoCano;
import br.unitins.joaovittor.basqueteiros.Fornecedor.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.Marca.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.Tamanho.repository.TamanhoRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class BasqueteiraServiceImp implements BasqueteiraService{

    @Inject
    BasqueteiraRepository repository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    TamanhoRepository tamanhoRepository;

    @Override
    @Transactional
    public BasqueteiraResponseDTO create(@Valid BasqueteiraDTO dto) {
        Basqueteira basqueteira = new Basqueteira();

        verificarNome(dto.nome());

        basqueteira.setNome(dto.nome());
        basqueteira.setTamanhoCano(TamanhoCano.valueOf(dto.idTamanhoCano()));
        basqueteira.setDescricao(dto.descricao());
        basqueteira.setPeso(dto.peso());
        basqueteira.setQuantidade(dto.quantidade());
        basqueteira.setPrecoCompra(dto.precoCompra());
        basqueteira.setPrecoVenda(dto.precoVenda());
        basqueteira.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        basqueteira.setMarca(marcaRepository.findById(dto.idMarca()));
        basqueteira.setTamanho(tamanhoRepository.findById(dto.idTamanho()));

        repository.persist(basqueteira);
        return BasqueteiraResponseDTO.valueof(basqueteira);
    }

    public void verificarNome(String nome){
        Basqueteira basqueteira = repository.findByNomeCompleto(nome);
        if(basqueteira != null)
            throw new ValidationException("nome", "O nome '"+nome+"' ja existe");
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, BasqueteiraDTO dto) {
        Basqueteira basqueteira = repository.findById(id);
        basqueteira.setNome(dto.nome());
        basqueteira.setTamanhoCano(TamanhoCano.valueOf(dto.idTamanhoCano()));
        basqueteira.setDescricao(dto.descricao());
        basqueteira.setPeso(dto.peso());
        basqueteira.setQuantidade(dto.quantidade());
        basqueteira.setPrecoCompra(dto.precoCompra());
        basqueteira.setPrecoVenda(dto.precoVenda());
        basqueteira.setFornecedor(fornecedorRepository.findById(dto.idFornecedor()));
        basqueteira.setMarca(marcaRepository.findById(dto.idMarca()));
        basqueteira.setTamanho(tamanhoRepository.findById(dto.idTamanho()));
    }

    @Override
    public List<BasqueteiraResponseDTO> findAll() {
        return repository.findAll()
        .stream()
        .map(e -> BasqueteiraResponseDTO.valueof(e)).toList();
    }

    @Override
    public BasqueteiraResponseDTO findById(Long id) {
        Basqueteira basqueteira = repository.findById(id);
        if(basqueteira != null)
            return BasqueteiraResponseDTO.valueof(basqueteira);
        return null;
    }

    @Override
    public List<BasqueteiraResponseDTO> findByNome(String nome) {

        List<BasqueteiraResponseDTO> lista = repository.findByNome(nome)
        .stream()
        .map(e -> BasqueteiraResponseDTO.valueof(e)).toList();

        if(lista.size() != 0)
            return lista;
        return null;
    }  
}
