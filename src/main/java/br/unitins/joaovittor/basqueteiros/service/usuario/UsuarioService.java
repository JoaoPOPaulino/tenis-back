package br.unitins.joaovittor.basqueteiros.service.usuario;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoDTO;
import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.telefone.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponseDTO create(@Valid UsuarioDTO dto);

    UsuarioResponseDTO update(Long id, UsuarioDTO dto);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    List<UsuarioResponseDTO> findByNome(String nome, int page, int pageSize);

    List<UsuarioResponseDTO> findAll(int page, int pageSize);

    UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO findByLogin(String login);

    EnderecoDTO findEnderecoByUsuarioId(Long id, Long enderecoId);

    UsuarioResponseDTO createEnderecos(Long id, List<EnderecoDTO> enderecosDTO);

    UsuarioResponseDTO updateEndereco(Long id, Long enderecoId, EnderecoDTO enderecosDTO);

    UsuarioResponseDTO removeEnderecos(Long id, Long enderecoId);

    CartaoResponseDTO findCartaoByUsuarioId(Long id, Long cartaoId);

    UsuarioResponseDTO createCartao(Long id, List<CartaoDTO> cartaoDTO);

    UsuarioResponseDTO updateCartao(Long id, Long cartaoId, CartaoDTO cartaoDTO);

    UsuarioResponseDTO deleteCartao(Long id, Long cartaoId);

    UsuarioResponseDTO createTelefones(Long usuarioId, List<TelefoneDTO> telefonesDTO);

    UsuarioResponseDTO updateTelefone(Long id, Long telefoneId, TelefoneDTO dto);

    UsuarioResponseDTO removeTelefone(Long id, Long telefoneId);

    List<TelefoneDTO> findTelefonesByUsuarioId(Long id);
}
