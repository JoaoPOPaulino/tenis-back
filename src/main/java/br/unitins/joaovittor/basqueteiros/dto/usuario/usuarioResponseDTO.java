package br.unitins.joaovittor.basqueteiros.dto.usuario;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.tipoUsuario.TipoUsuario;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nome,
        String email,
        String login,
        String senha,
        TipoUsuario tipoUsuario,
        List<TelefoneDTO> listaTelefone,
        Endereco endereco) {

    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getTipoUsuario(),
                usuario.getListaTelefone()
                        .stream()
                        .map(t -> TelefoneDTO.valueOf(t)).toList(),
                usuario.getEndereco());
    }
}
