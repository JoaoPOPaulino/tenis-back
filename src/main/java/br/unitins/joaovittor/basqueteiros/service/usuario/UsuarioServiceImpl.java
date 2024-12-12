package br.unitins.joaovittor.basqueteiros.service.usuario;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import br.unitins.joaovittor.basqueteiros.model.tipo_cartao.TipoCartao;
import br.unitins.joaovittor.basqueteiros.model.tipo_usuario.TipoUsuario;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.CartaoRepository;
import br.unitins.joaovittor.basqueteiros.repository.CidadeRepository;
import br.unitins.joaovittor.basqueteiros.repository.EnderecoRepository;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.service.hash.HashServiceImpl;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    HashServiceImpl hashServiceImpl;

    @Inject
    CidadeRepository cidadeRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    CartaoRepository cartaoRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto) throws ConstraintViolationException {
        // Verifica se o login já existe
        if (usuarioRepository.findByLogin(dto.login()) != null) {
            throw new ValidationException("login", "O login informado já existe, informe outro.");
        }

        // Valida se o ID do perfil (tipo de usuário) é inválido
        if (dto.idPerfil() == 0) {
            throw new IllegalArgumentException("ID do Tipo de Usuário não pode ser 0.");
        }

        // Criação do usuário
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setLogin(dto.login());
        usuario.setSenha(hashServiceImpl.getHashSenha(dto.senha()));

        // Define o tipo de usuário, já com a validação feita
        usuario.setTipoUsuario(TipoUsuario.fromId(dto.idPerfil()));

        // Persiste o usuário
        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Long id, UsuarioDTO dto) throws ConstraintViolationException {
        Usuario usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        usuarioExistente.setNome(dto.nome());
        usuarioExistente.setEmail(dto.email());
        usuarioExistente.setLogin(dto.login());
        usuarioExistente.setSenha(hashServiceImpl.getHashSenha(dto.senha()));
        usuarioExistente.setTipoUsuario(TipoUsuario.fromId(dto.idPerfil()));

        usuarioRepository.persist(usuarioExistente);

        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public List<UsuarioResponseDTO> findAll(int page, int pageSize) {
        List<Usuario> list = usuarioRepository.findAll().page(page, pageSize).list();
        return list.stream().map(UsuarioResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLogin(String login) {
        Usuario usuario = usuarioRepository.findByLogin(login);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha) {
        Usuario usuario = usuarioRepository.findByLoginAndSenha(login, senha);
        if (usuario == null) {
            throw new ValidationException("login", "Login ou senha inválido");
        }
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Usuario> list = usuarioRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(UsuarioResponseDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UsuarioResponseDTO createEnderecos(Long usuarioId, List<EnderecoDTO> enderecosDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId);
        if (usuarioExistente == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        List<Endereco> enderecosExistente = usuarioExistente.getEndereco();

        for (EnderecoDTO enderecoDTO : enderecosDTO) {
            Endereco novoEndereco = new Endereco();
            novoEndereco.setCep(enderecoDTO.cep());
            novoEndereco.setQuadra(enderecoDTO.quadra());
            novoEndereco.setRua(enderecoDTO.rua());
            novoEndereco.setNumero(enderecoDTO.numero());
            novoEndereco.setComplemento(enderecoDTO.complemento());

            Cidade cidade = cidadeRepository.findById(enderecoDTO.cidade().id());
            if (cidade == null) {
                throw new NotFoundException("Cidade não encontrada");
            }
            novoEndereco.setCidade(cidade);
            enderecosExistente.add(novoEndereco);
        }

        usuarioRepository.persist(usuarioExistente);
        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateEndereco(Long usuarioId, Long enderecoId, EnderecoDTO enderecoDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId);
        if (usuarioExistente == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Optional<Endereco> enderecoOptional = usuarioExistente.getEndereco().stream()
                .filter(endereco -> endereco.getId().equals(enderecoId))
                .findFirst();

        if (enderecoOptional.isPresent()) {
            Endereco enderecoExistente = enderecoOptional.get();
            enderecoExistente.setCep(enderecoDTO.cep());
            enderecoExistente.setQuadra(enderecoDTO.quadra());
            enderecoExistente.setRua(enderecoDTO.rua());
            enderecoExistente.setNumero(enderecoDTO.numero());
            enderecoExistente.setComplemento(enderecoDTO.complemento());

            Cidade cidade = cidadeRepository.findById(enderecoDTO.cidade().id());
            if (cidade == null) {
                throw new NotFoundException("Cidade não encontrada");
            }
            enderecoExistente.setCidade(cidade);
        } else {
            throw new NotFoundException("Endereço não encontrado para o usuário especificado.");
        }

        usuarioRepository.persist(usuarioExistente);
        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO removeEnderecos(Long usuarioId, Long enderecoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        List<Endereco> enderecos = usuario.getEndereco();
        Iterator<Endereco> iterator = enderecos.iterator();

        while (iterator.hasNext()) {
            Endereco endereco = iterator.next();
            if (endereco.getId().equals(enderecoId)) {
                iterator.remove();
                usuarioRepository.persist(usuario);
                return UsuarioResponseDTO.valueOf(usuario);
            }
        }

        throw new NotFoundException("Endereço não encontrado para o usuário especificado.");
    }

    @Override
    @Transactional
    public UsuarioResponseDTO createCartao(Long usuarioId, List<CartaoDTO> cartaoDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        List<Cartao> existingCartao = usuario.getCartoes();

        for (CartaoDTO dto : cartaoDTO) {
            Cartao cartao = new Cartao();

            cartao.setTipo(TipoCartao.valueOf(dto.idTipo()));
            cartao.setNumero(dto.numero());
            cartao.setCvv(dto.cvv());
            cartao.setValidade(dto.validade());
            cartao.setTitular(dto.titular());
            cartao.setCpf(dto.cpf());

            existingCartao.add(cartao);
        }
        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO updateCartao(Long usuarioId, Long cartaoId, CartaoDTO cartaoDTO) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        Optional<Cartao> cartaoOptional = usuario.getCartoes().stream()
                .filter(cartao -> cartao.getId().equals(cartaoId))
                .findFirst();

        if (cartaoOptional.isPresent()) {
            Cartao cartaoExistente = cartaoOptional.get();
            cartaoExistente.setNumero(cartaoDTO.numero());
            cartaoExistente.setCvv(cartaoDTO.cvv());
            cartaoExistente.setValidade(cartaoDTO.validade());
            cartaoExistente.setTitular(cartaoDTO.titular());
            cartaoExistente.setCpf(cartaoDTO.cpf());
            cartaoExistente.setTipo(TipoCartao.valueOf(cartaoDTO.idTipo()));
        } else {
            throw new NotFoundException("Cartão não encontrado para o usuário especificado.");
        }

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO deleteCartao(Long usuarioId, Long cartaoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId);
        if (usuario == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        List<Cartao> cartoes = usuario.getCartoes();
        Iterator<Cartao> iterator = cartoes.iterator();

        while (iterator.hasNext()) {
            Cartao cartao = iterator.next();
            if (cartao.getId().equals(cartaoId)) {
                iterator.remove();
                usuarioRepository.persist(usuario);
                return UsuarioResponseDTO.valueOf(usuario);
            }
        }

        throw new NotFoundException("Cartão não encontrado para o usuário especificado.");
    }

    @Override
    public EnderecoDTO findEnderecoByUsuarioId(Long id, Long enderecoId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public CartaoResponseDTO findCartaoByUsuarioId(Long id, Long cartaoId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    @Transactional
    public UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO) {
        // Busca o usuário pelo ID
        Usuario usuarioExistente = usuarioRepository.findById(usuarioId);
        if (usuarioExistente == null) {
            throw new NotFoundException("Usuário não encontrado.");
        }

        // Recupera a lista existente de telefones do usuário
        List<Telefone> telefonesExistentes = usuarioExistente.getTelefone();

        // Itera sobre a lista de DTOs de telefone e adiciona os novos telefones ao usuário
        for (TelefoneDTO telefoneDTO : telefonesDTO) {
            Telefone novoTelefone = new Telefone();
            novoTelefone.setCodigoArea(telefoneDTO.codigoArea());
            novoTelefone.setNumero(telefoneDTO.numero());

            telefonesExistentes.add(novoTelefone);
        }

        // Persiste o usuário com os novos telefones
        usuarioRepository.persist(usuarioExistente);

        // Retorna o DTO de resposta atualizado
        return UsuarioResponseDTO.valueOf(usuarioExistente);
    }
}
