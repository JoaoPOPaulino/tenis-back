package br.unitins.joaovittor.basqueteiros.service.estoque;

import java.util.ArrayList;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.estoque.EstoqueDTO;
import br.unitins.joaovittor.basqueteiros.dto.estoque.EstoqueResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.estoque.Estoque;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.repository.EstoqueRepository;
import br.unitins.joaovittor.basqueteiros.repository.TenisRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EstoqueServiceImpl implements EstoqueService {

    @Inject
    EstoqueRepository estoqueRepository;

    @Inject
    TenisRepository tenisRepository;

    @Override
    @Transactional
    public EstoqueResponseDTO insert(@Valid EstoqueDTO dto) {
        // Verifica se o tênis existe
        Tenis tenis = tenisRepository.findById(dto.tenisId());
        if (tenis == null) {
            throw new ValidationException("tenisId", "Tênis não encontrado.");
        }

        // Cria e persiste o novo estoque
        Estoque novoEstoque = new Estoque();
        novoEstoque.setTamanho(dto.tamanho());
        novoEstoque.setQuantidade(dto.quantidade());
        novoEstoque.setTenis(tenis);

        estoqueRepository.persist(novoEstoque);
        return EstoqueResponseDTO.valueOf(novoEstoque);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // Verifica se o estoque existe antes de tentar deletar
        if (!estoqueRepository.deleteById(id)) {
            throw new NotFoundException("Estoque não encontrado.");
        }
    }

    @Override
    public EstoqueResponseDTO findById(Long id) {
        Estoque estoque = estoqueRepository.findById(id);
        if (estoque == null) {
            throw new NotFoundException("Estoque não encontrado.");
        }
        return EstoqueResponseDTO.valueOf(estoque);
    }

    @Override
    public List<EstoqueResponseDTO> findByTenisId(Long tenisId) {
        List<Estoque> estoques = estoqueRepository.findByTenisId(tenisId);
        List<EstoqueResponseDTO> dtos = new ArrayList<>();
        for (Estoque estoque : estoques) {
            dtos.add(EstoqueResponseDTO.valueOf(estoque));
        }
        return dtos;
    }

    @Override
    public List<EstoqueResponseDTO> findAll() {
        List<Estoque> estoques = estoqueRepository.listAll();
        List<EstoqueResponseDTO> dtos = new ArrayList<>();
        for (Estoque estoque : estoques) {
            dtos.add(EstoqueResponseDTO.valueOf(estoque));
        }
        return dtos;
    }

    @Override
    @Transactional
    public EstoqueResponseDTO updateEstoque(Long id, Integer quantidade) {
        Estoque estoque = estoqueRepository.findById(id);
        if (estoque == null) {
            throw new NotFoundException("Estoque não encontrado.");
        }

        // Atualiza a quantidade do estoque
        estoque.setQuantidade(quantidade);
        estoqueRepository.persist(estoque);
        return EstoqueResponseDTO.valueOf(estoque);
    }
}
