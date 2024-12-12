package br.unitins.joaovittor.basqueteiros.service.usuario;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tipo_usuario.TipoUsuario;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.service.cartao.CartaoUsuarioService;
import br.unitins.joaovittor.basqueteiros.service.endereco.EnderecoUsuarioService;
import br.unitins.joaovittor.basqueteiros.service.hash.HashServiceImpl;
import br.unitins.joaovittor.basqueteiros.service.telefone.TelefoneUsuarioService;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashServiceImpl hashService;

    @Inject
    EnderecoUsuarioService enderecoService;

    @Inject
    CartaoUsuarioService cartaoService;

    @Inject
    TelefoneUsuarioService telefoneService;

    @Override
    @Transactional
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto) {
        validateNewUser(dto);

        Usuario usuario = new Usuario();
        updateUsuarioFromDTO(usuario, dto);
        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = findUsuarioOrThrow(id);
        validateExistingUser(usuario, dto);
        updateUsuarioFromDTO(usuario, dto);

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!usuarioRepository.deleteById(id)) {
            throw new NotFoundException("Usuário não encontrado");
        }
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        return UsuarioResponseDTO.valueOf(findUsuarioOrThrow(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize) {
        PanacheQuery<Usuario> query = usuarioRepository.findByNome(nome);
        if (query == null) {
            return Collections.emptyList();
        }

        return query
                .page(page, pageSize)
                .list()
                .stream()
                .map(UsuarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioResponseDTO> findAll(int page, int pageSize) {
        return usuarioRepository.findAll()
                .page(page, pageSize)
                .list()
                .stream()
                .map(UsuarioResponseDTO::valueOf)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        return usuarioRepository.findByLoginAndSenha(login, hashService.getHashSenha(senha))
                .map(UsuarioResponseDTO::valueOf)
                .orElseThrow(() -> new ValidationException("credenciais", "Login ou senha inválidos"));
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        return UsuarioResponseDTO.valueOf(
                usuarioRepository.findByLogin(login)
                        .orElseThrow(() -> new NotFoundException("Usuário não encontrado"))
        );
    }

    @Override
    public EnderecoDTO findEnderecoByUsuarioId(Long id, Long enderecoId) {
        return enderecoService.findEnderecoByUsuarioId(id, enderecoId);
    }

    @Override
    public UsuarioResponseDTO createEnderecos(Long id, List<EnderecoDTO> enderecosDTO) {
        return enderecoService.addEnderecos(id, enderecosDTO);
    }

    @Override
    public UsuarioResponseDTO updateEndereco(Long id, Long enderecoId, EnderecoDTO enderecoDTO) {
        return enderecoService.updateEndereco(id, enderecoId, enderecoDTO);
    }

    @Override
    public UsuarioResponseDTO removeEnderecos(Long id, Long enderecoId) {
        return enderecoService.removeEndereco(id, enderecoId);
    }

    @Override
    public CartaoResponseDTO findCartaoByUsuarioId(Long id, Long cartaoId) {
        return cartaoService.findCartaoByUsuarioId(id, cartaoId);
    }

    @Override
    public UsuarioResponseDTO createCartao(Long id, List<CartaoDTO> cartaoDTO) {
        return cartaoService.addCartoes(id, cartaoDTO);
    }

    @Override
    public UsuarioResponseDTO updateCartao(Long id, Long cartaoId, CartaoDTO cartaoDTO) {
        return cartaoService.updateCartao(id, cartaoId, cartaoDTO);
    }

    @Override
    public UsuarioResponseDTO deleteCartao(Long id, Long cartaoId) {
        return cartaoService.removeCartao(id, cartaoId);
    }

    @Override
    public UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
        return telefoneService.addTelefones(usuarioId, telefonesDTO);
    }

    // Métodos auxiliares
    private void validateNewUser(UsuarioDTO dto) {
        if (usuarioRepository.findByLogin(dto.login()).isPresent()) {
            throw new ValidationException("login", "Login já existe");
        }
        validateUserFields(dto);
    }

    private void validateExistingUser(Usuario usuario, UsuarioDTO dto) {
        if (!usuario.getLogin().equals(dto.login())
                && usuarioRepository.findByLogin(dto.login()).isPresent()) {
            throw new ValidationException("login", "Login já existe");
        }
        validateUserFields(dto);
    }

    private void validateUserFields(UsuarioDTO dto) {
        if (dto.idPerfil() == 0) {
            throw new ValidationException("perfil", "Perfil inválido");
        }
    }

    private void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO dto) {
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(hashService.getHashSenha(dto.senha()));
        usuario.setTipoUsuario(TipoUsuario.fromId(dto.idPerfil()));
    }

    private Usuario findUsuarioOrThrow(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    @Override
    public UsuarioResponseDTO updateTelefone(Long id, Long telefoneId, TelefoneDTO dto) {
        return telefoneService.updateTelefone(id, telefoneId, dto);
    }

    @Override
    public UsuarioResponseDTO removeTelefone(Long id, Long telefoneId) {
        return telefoneService.removeTelefone(id, telefoneId);
    }

    @Override
    public List<TelefoneDTO> findTelefonesByUsuarioId(Long id) {
        return telefoneService.findTelefonesByUsuarioId(id);
    }
}
