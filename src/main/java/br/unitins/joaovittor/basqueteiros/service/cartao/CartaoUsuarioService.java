package br.unitins.joaovittor.basqueteiros.service.cartao;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.tipo_cartao.TipoCartao;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class CartaoUsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Transactional
    public UsuarioResponseDTO addCartoes(Long usuarioId, List<CartaoDTO> cartoesDTO) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        List<Cartao> novosCartoes = cartoesDTO.stream()
                .peek(this::validateCartao)
                .map(this::createCartaoFromDTO)
                .collect(Collectors.toList());

        novosCartoes.forEach(usuario::addCartao);
        usuarioRepository.persist(usuario);

        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Transactional
    public UsuarioResponseDTO updateCartao(Long usuarioId, Long cartaoId, CartaoDTO dto) {
        validateCartao(dto);
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        Cartao cartao = findCartaoOrThrow(usuario, cartaoId);
        updateCartaoFromDTO(cartao, dto);

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    @Transactional
    public UsuarioResponseDTO removeCartao(Long usuarioId, Long cartaoId) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);

        boolean removed = usuario.getCartoes().removeIf(c -> c.getId().equals(cartaoId));
        if (!removed) {
            throw new NotFoundException("Cartão não encontrado");
        }

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.valueOf(usuario);
    }

    public CartaoResponseDTO findCartaoByUsuarioId(Long usuarioId, Long cartaoId) {
        Usuario usuario = findUsuarioOrThrow(usuarioId);
        Cartao cartao = findCartaoOrThrow(usuario, cartaoId);
        return CartaoResponseDTO.valueOf(cartao);
    }

    // Métodos auxiliares
    private Cartao createCartaoFromDTO(CartaoDTO dto) {
        Cartao cartao = new Cartao();
        updateCartaoFromDTO(cartao, dto);
        return cartao;
    }

    private void updateCartaoFromDTO(Cartao cartao, CartaoDTO dto) {
        cartao.setTipoCartao(TipoCartao.fromId(dto.tipoCartao()));
        cartao.setNumero(dto.numero());
        cartao.setCvv(dto.cvv());
        cartao.setValidade(dto.validade());
        cartao.setTitular(dto.titular());
        cartao.setCpf(dto.cpf());
    }

    private void validateCartao(CartaoDTO dto) {
        if (dto.numero().length() != 16) {
            throw new ValidationException("numero", "Número do cartão deve ter 16 dígitos");
        }
        if (dto.cvv().length() != 3) {
            throw new ValidationException("cvv", "CVV deve ter 3 dígitos");
        }
        if (dto.validade().isBefore(LocalDate.now())) {
            throw new ValidationException("validade", "Data de validade não pode ser no passado");
        }
        if (dto.tipoCartao() == null) {
            throw new ValidationException("tipoCartao", "Tipo de cartão é obrigatório");
        }
    }

    private Usuario findUsuarioOrThrow(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
    }

    private Cartao findCartaoOrThrow(Usuario usuario, Long cartaoId) {
        return usuario.getCartoes().stream()
                .filter(c -> c.getId().equals(cartaoId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Cartão não encontrado"));
    }
}
