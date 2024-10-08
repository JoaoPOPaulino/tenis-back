package br.unitins.joaovittor.basqueteiros.service.usuario;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.dto.usuario.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    void delete(Long id);

    UsuarioResponseDTO findById(Long id);

    List<UsuarioResponseDTO> findByNome(String nome);

    List<UsuarioResponseDTO> findByAll();

    UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    UsuarioResponseDTO findByLogin(String login);

    // UsuarioResponseDTO updateNome(@Valid NomeUpdateDTO dto, String login);
    // UsuarioResponseDTO updateLogin(@Valid LoginUpdateDTO dto, Long id);
    // UsuarioResponseDTO updateEmail(@Valid EmailUpdateDTO dto, Long id);
    // UsuarioResponseDTO updateSenha(@Valid SenhaUpdateDTO dto, String login);
    // UsuarioResponseDTO insertTelefone(@Valid TelefoneDTO dto, Long id);
    // UsuarioResponseDTO insertEndereco(@Valid EnderecoDTO dto, Long id);
    // UsuarioResponseDTO updateTelefone(@Valid TelefoneUpdateDTO dto, String login);
    // UsuarioResponseDTO updateEndereco(@Valid EnderecoUpdateDTO dto, Long id);
    UsuarioResponseDTO updatePerfil(long id, Integer perfil);
}
