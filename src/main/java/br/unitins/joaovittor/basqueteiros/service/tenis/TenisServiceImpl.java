package br.unitins.joaovittor.basqueteiros.service.tenis;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.estoque.Estoque;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.repository.EstoqueRepository;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TenisServiceImpl implements TenisService {

    private final TenisRepository tenisRepository;
    private final FornecedorRepository fornecedorRepository;
    private final EstoqueRepository estoqueRepository;
    private final MarcaRepository marcaRepository;

    public TenisServiceImpl(TenisRepository tenisRepository, FornecedorRepository fornecedorRepository,
            EstoqueRepository estoqueRepository, MarcaRepository marcaRepository) {
        this.tenisRepository = tenisRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.estoqueRepository = estoqueRepository;
        this.marcaRepository = marcaRepository;
    }

    @Override
    @Transactional
    public Tenis criarTenis(TenisDTO tenisDTO) {
        Tenis tenis = new Tenis();
        tenis.setNome(tenisDTO.nome());
        tenis.setPreco(tenisDTO.preco());
        tenis.setDescricao(tenisDTO.descricao());

        // Buscar marca
        Marca marca = marcaRepository.findById(tenisDTO.marcaId());
        tenis.setMarca(marca);

        // Buscar fornecedores e converter List<Fornecedor> para Set<Fornecedor>
        Set<Fornecedor> fornecedores = new HashSet<>(fornecedorRepository.findAllByIds(tenisDTO.fornecedorIds()));
        tenis.setFornecedor(fornecedores);

        // Buscar estoques e converter List<Estoque> para Set<Estoque>
        Set<Estoque> estoques = new HashSet<>(estoqueRepository.findAllByIds(tenisDTO.estoqueIds()));
        tenis.setEstoque(estoques);

        tenisRepository.persist(tenis);
        return tenis;
    }

    @Override
    public Tenis buscarTenisPorId(Long id) {
        return tenisRepository.findById(id);
    }

    @Override
    public List<Tenis> buscarTenisPorNome(String nome) {
        return tenisRepository.findByNome(nome);
    }

    @Override
    public List<Tenis> buscarTenisPorMarca(String marcaNome) {
        return tenisRepository.findByMarca(marcaNome);
    }

    @Override
    public List<Tenis> buscarTenisPorPrecoRange(float minPreco, float maxPreco) {
        return tenisRepository.findByPrecoRange(minPreco, maxPreco);
    }

    @Override
    public List<TenisResponseDTO> listarTodosTenis() {
        return tenisRepository.listAll().stream()
                .map(TenisResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void excluirTenis(Long id) {
        Tenis tenis = buscarTenisPorId(id);
        if (tenis != null) {
            tenisRepository.delete(tenis);
        }
    }
}
