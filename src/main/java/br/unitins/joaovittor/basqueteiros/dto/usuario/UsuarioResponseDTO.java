package br.unitins.joaovittor.basqueteiros.dto.usuario;

import java.util.Collections;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.model.tipoUsuario.TipoUsuario;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        String senha,
        TipoUsuario tipoUsuario,
        List<TelefoneDTO> telefone,
        List<EnderecoDTO> endereco,
        List<CartaoResponseDTO> cartoes,
        TipoUsuario idPerfil) {

        public static UsuarioResponseDTO valueOf(Usuario usuario) {
                if (usuario == null) {
                    throw new IllegalArgumentException("Usuario não pode ser null");
        
        
        }

        List<EnderecoDTO> enderecos = null;

        if (usuario.getEndereco() != null && !usuario.getEndereco().isEmpty()) {
            enderecos = usuario.getEndereco().stream().map(e -> EnderecoDTO.valueOf(e)).toList();
        }

        List<CartaoResponseDTO> cartoes = null;

        if (usuario.getCartoes() != null && !usuario.getCartoes().isEmpty()) {
            cartoes = usuario.getCartoes().stream().map(c -> CartaoResponseDTO.valueOf(c)).toList();
        }

        List<TelefoneDTO> telefones = usuario.getTelefone() == null ? Collections.emptyList() :
                usuario.getTelefone().stream().map(TelefoneDTO::valueOf).toList();

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getTipoUsuario(),
                telefones,
                //usuario.getTelefone()
                        //.stream()
                        //.map(t -> TelefoneDTO.valueOf(t)).toList(),
                enderecos,
                cartoes,
                usuario.getTipoUsuario());
    }
}
