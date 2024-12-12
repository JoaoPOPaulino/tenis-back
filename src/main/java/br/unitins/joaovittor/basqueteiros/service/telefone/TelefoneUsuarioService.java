package br.unitins.joaovittor.basqueteiros.service.telefone;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TelefoneUsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO addTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        List<Telefone> novosTelefones = telefonesDTO.stream()
                .peek(this::validateTelefone)
                .map(this::createTelefoneFromDTO)
                .collect(Collectors.toList());

        novosTelefones.forEach(usuario::addTelefone);
        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Transactional
    public UsuarioResponseDTO updateTelefone(Long usuarioId, Long telefoneId, TelefoneDTO dto) {
        validateTelefone(dto);
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        Telefone telefone = findTelefoneOrThrow(usuario, telefoneId);
        updateTelefoneFromDTO(telefone, dto);

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Transactional
    public UsuarioResponseDTO removeTelefone(Long usuarioId, Long telefoneId) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        boolean removed = usuario.getTelefones().removeIf(t -> t.getId().equals(telefoneId));
        if (!removed) {
            throw new NotFoundException("Telefone não encontrado");
        }

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    public List<TelefoneDTO> findTelefonesByUsuarioId(Long usuarioId) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);
        return usuario.getTelefones().stream()
                .map(this::createTelefoneDTO)
                .collect(Collectors.toList());
    }

    // Métodos auxiliares
    private Telefone createTelefoneFromDTO(TelefoneDTO dto) {
        Telefone telefone = new Telefone();
        updateTelefoneFromDTO(telefone, dto);
        return telefone;
    }

    private void updateTelefoneFromDTO(Telefone telefone, TelefoneDTO dto) {
        telefone.setDdd(dto.ddd());
        telefone.setNumero(dto.numero());
    }

    private void validateTelefone(TelefoneDTO dto) {
        if (dto.ddd() == null || !dto.ddd().matches("\\d{2}")) {
            throw new ValidationException("ddd", "DDD deve ter 2 dígitos");
        }

        if (dto.numero() == null || !dto.numero().matches("\\d{8,9}")) {
            throw new ValidationException("numero", "Número deve ter 8 ou 9 dígitos");
        }
    }

    private Usuario findUsuarioOrThrow(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private Telefone findTelefoneOrThrow(Usuario usuario, Long telefoneId) {
        return usuario.getTelefones().stream()
                .filter(t -> t.getId().equals(telefoneId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Telefone não encontrado"));
    }

    private TelefoneDTO createTelefoneDTO(Telefone telefone) {
        return new TelefoneDTO(
                telefone.getDdd(),
                telefone.getNumero()
        );
    }
}
