package br.unitins.joaovittor.basqueteiros.service.endereco;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.CidadeRepository;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class EnderecoUsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    CidadeRepository cidadeRepository;

    @Transactional
    public UsuarioResponseDTO addEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        List<Endereco> novosEnderecos = enderecosDTO.stream()
                .map(this::createEnderecoFromDTO)
                .collect(Collectors.toList());

        novosEnderecos.forEach(usuario::addEndereco);
        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Transactional
    public UsuarioResponseDTO updateEndereco(Long usuarioId, Long enderecoId, EnderecoDTO dto) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        Endereco endereco = findEnderecoOrThrow(usuario, enderecoId);
        updateEnderecoFromDTO(endereco, dto);

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Transactional
    public UsuarioResponseDTO removeEndereco(Long usuarioId, Long enderecoId) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        boolean removed = usuario.getEnderecos().removeIf(e -> e.getId().equals(enderecoId));
        if (!removed) {
            throw new NotFoundException("Endereço não encontrado");
        }

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    public EnderecoDTO findEnderecoByUsuarioId(Long usuarioId, Long enderecoId) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);
        Endereco endereco = findEnderecoOrThrow(usuario, enderecoId);
        return createEnderecoDTO(endereco);
    }

    // Métodos auxiliares
    private Endereco createEnderecoFromDTO(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        updateEnderecoFromDTO(endereco, dto);
        return endereco;
    }

    private void updateEnderecoFromDTO(Endereco endereco, EnderecoDTO dto) {
        endereco.setCep(dto.cep());
        endereco.setQuadra(dto.quadra());
        endereco.setRua(dto.rua());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setCidade(findCidadeOrThrow(dto.idCidade()));
    }

    private Usuario findUsuarioOrThrow(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private Cidade findCidadeOrThrow(Long id) {
        return cidadeRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Cidade não encontrada"));
    }

    private Endereco findEnderecoOrThrow(Usuario usuario, Long enderecoId) {
        return usuario.getEnderecos().stream()
                .filter(e -> e.getId().equals(enderecoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
    }

    private EnderecoDTO createEnderecoDTO(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getCep(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getCidade().getId()
        );
    }
}
