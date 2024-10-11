package br.unitins.joaovittor.basqueteiros.service.endereco;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.repository.EnderecoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoServiceImpl implements EnderecoService {

    @Inject
    EnderecoRepository enderecoRepository;

    @Override
    public List<EnderecoResponseDTO> findAll() {
        List<Endereco> enderecos = enderecoRepository.listAll();
        return enderecos.stream()
                .map(EnderecoResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public EnderecoResponseDTO findById(Long id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }
        return EnderecoResponseDTO.valueOf(endereco);
    }

    @Override
    @Transactional
    public void create(EnderecoDTO enderecoDTO) {
        Endereco endereco = new Endereco(enderecoDTO);
        enderecoRepository.persist(endereco);
    }

    @Override
    @Transactional
    public void update(Long id, EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco == null) {
            throw new NotFoundException("Endereço não encontrado");
        }
        endereco.setEstado(enderecoDTO.estado());
        endereco.setCidade(enderecoDTO.cidade());
        endereco.setQuadra(enderecoDTO.quadra());
        endereco.setRua(enderecoDTO.rua());
        endereco.setNumero(enderecoDTO.numero());
        enderecoRepository.persist(endereco);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Endereco endereco = enderecoRepository.findById(id);
        if (endereco != null) {
            enderecoRepository.delete(endereco);
        }
    }
}