package br.unitins.joaovittor.basqueteiros.dto.usuario;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O e-mail é obrigatório")
        String email,
        @NotBlank(message = "O login é obrigatório")
        String login,
        @NotBlank(message = "A senha é obrigatória")
        String senha,
        List<EnderecoDTO> enderecos,
        List<CartaoResponseDTO> cartoes,
        List<TelefoneDTO> telefones,
        @NotNull(message = "O perfil é obrigatória.")
        Integer idPerfil) {

}
