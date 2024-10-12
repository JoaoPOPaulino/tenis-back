package br.unitins.joaovittor.basqueteiros.service.tenis;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.repository.FornecedorRepository;
import br.unitins.joaovittor.basqueteiros.repository.MarcaRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TenisServiceImpl implements TenisService {

    @Inject
    TenisRepository tenisRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    MarcaRepository marcaRepository;

    // Construtor para injeção de dependências
    @Inject
    public TenisServiceImpl(TenisRepository tenisRepository,
            FornecedorRepository fornecedorRepository,
            MarcaRepository marcaRepository) {
        this.tenisRepository = tenisRepository;
        this.fornecedorRepository = fornecedorRepository;
        this.marcaRepository = marcaRepository;
    }

    @Override
    @Transactional
    public Tenis criarTenis(TenisDTO tenisDTO) {
        Tenis tenis = new Tenis();
        tenis.setNome(tenisDTO.nome());
        tenis.setPreco(tenisDTO.preco());
        tenis.setDescricao(tenisDTO.descricao());

        Marca marca = marcaRepository.findById(tenisDTO.idMarca());
        tenis.setMarca(marca);

        tenisRepository.persist(tenis);
        return tenis;
    }

    @Override
    public Tenis buscarTenisPorId(Long id) {
        return tenisRepository.findById(id);
    }

    @Override
    public List<Tenis> buscarTenisPorNome(String nome) {
        return tenisRepository.findByNome(nome).list();
    }

    @Override
    public List<TenisResponseDTO> listarTodosTenis() {
        return tenisRepository.listAll()
                .stream()
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

    @Override
    public List<Tenis> buscarTenisPorMarca(String marcaNome) {
        String jpql = "SELECT t FROM Tenis t WHERE t.marca.nome = :marcaNome";
        return getEntityManager()
                .createQuery(jpql, Tenis.class)
                .setParameter("marcaNome", marcaNome)
                .getResultList();
    }

    @Override
    public List<Tenis> buscarTenisPorPrecoRange(float minPreco, float maxPreco) {
        String jpql = "SELECT t FROM Tenis t WHERE t.preco BETWEEN :minPreco AND :maxPreco";
        return getEntityManager()
                .createQuery(jpql, Tenis.class)
                .setParameter("minPreco", minPreco)
                .setParameter("maxPreco", maxPreco)
                .getResultList();
    }

}
