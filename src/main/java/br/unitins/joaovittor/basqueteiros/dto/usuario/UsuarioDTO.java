package br.unitins.joaovittor.basqueteiros.dto.usuario;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;

public record UsuarioDTO(
        String nome,
        String email,
        String login,
        String senha,
        List<EnderecoDTO> enderecos,
        List<TelefoneDTO> telefones,
        List<CartaoDTO> cartoes,
        Integer idPerfil) {

}
