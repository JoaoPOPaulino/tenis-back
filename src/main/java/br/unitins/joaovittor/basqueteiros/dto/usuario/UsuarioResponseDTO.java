package br.unitins.joaovittor.basqueteiros.dto.usuario;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.model.tipo_usuario.TipoUsuario;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        TipoUsuario tipoUsuario,
        List<TelefoneDTO> telefone,
        List<EnderecoDTO> endereco,
        List<CartaoResponseDTO> cartoes,
        TipoUsuario idPerfil) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        Objects.requireNonNull(usuario, "Usuario não pode ser null");

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getTipoUsuario(),
                Optional.ofNullable(usuario.getTelefones())
                        .map(telefones -> telefones.stream()
                        .map(TelefoneDTO::from) // Usando from em vez de valueOf
                        .collect(Collectors.toList()))
                        .orElse(Collections.emptyList()),
                Optional.ofNullable(usuario.getEnderecos())
                        .map(enderecos -> enderecos.stream()
                        .map(end -> new EnderecoDTO( // Construindo diretamente
                        end.getCep(),
                        end.getQuadra(),
                        end.getRua(),
                        end.getNumero(),
                        end.getComplemento(),
                        end.getCidade().getId()))
                        .collect(Collectors.toList()))
                        .orElse(Collections.emptyList()),
                Optional.ofNullable(usuario.getCartoes())
                        .map(cartoes -> cartoes.stream()
                        .map(CartaoResponseDTO::valueOf)
                        .collect(Collectors.toList()))
                        .orElse(Collections.emptyList()),
                usuario.getTipoUsuario()
        );
    }
}
