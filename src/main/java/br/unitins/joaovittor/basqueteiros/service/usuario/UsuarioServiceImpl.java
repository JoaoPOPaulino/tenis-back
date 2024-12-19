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
    
    private void updateUsuarioFromDTO(Usuario usuario, UsuarioDTO dto) {
        // Atualiza os campos básicos do usuário
        usuario.setLogin(dto.login());
    
        // Gera o hash da senha antes de armazenar
        if (dto.senha() != null && !dto.senha().isEmpty()) {
            usuario.setSenha(hashService.getHashSenha(dto.senha()));
        }
    
        // Define o tipo de usuário/perfil usando o método fromId
        TipoUsuario perfil = TipoUsuario.fromId(dto.idPerfil());
        usuario.setTipoUsuario(perfil);
    }

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
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, hashService.getHashSenha(senha));
        if (usuario == null) {
            throw new ValidationException("credenciais", "Login ou senha inválidos");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return UsuarioResponseDTO.valueOf(usuario);
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

    @Override
    public UsuarioResponseDTO login(String username, String senha) {
        Usuario usuario = usuarioRepository.findByLogin(username);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }

        if (!hashService.verificarSenha(senha, usuario.getSenha())) {
            throw new ValidationException("credenciais", "Credenciais inválidas");
        }

        return UsuarioResponseDTO.valueOf(usuario);
    }

    // Métodos auxiliares
    private void validateNewUser(UsuarioDTO dto) {
        Usuario existingUser = usuarioRepository.findByLogin(dto.login());
        if (existingUser != null) {
            throw new ValidationException("login", "Login já existe");
        }
        validateUserFields(dto);
    }

    private void validateExistingUser(Usuario usuario, UsuarioDTO dto) {
        if (!usuario.getLogin().equals(dto.login())
                && usuarioRepository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "Login já existe");
        }
        validateUserFields(dto);
    }

    private void validateUserFields(UsuarioDTO dto) {
        if (dto.idPerfil() == null || dto.idPerfil() == 0) {
            throw new ValidationException("perfil", "Perfil inválido");
        }
    }

    private Usuario findUsuarioOrThrow(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado");
        }
        return usuario;
    }
}
