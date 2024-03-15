package br.unitins.joaovittor.basqueteiros.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setDataNascimento(LocalDate.of(dto.anoAniv(), dto.mesAniv(), dto.diaAniv()));
        usuario.setDataCadastro(LocalDate.now());

        usuarioRepository.persist(usuario);
        return UsuarioResponseDTO.parse(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) { usuarioRepository.deleteById(id); }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        usuario.setDataNascimento(LocalDate.of(dto.anoAniv(), dto.mesAniv(), dto.diaAniv()));
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                         .stream()
                         .map(e -> UsuarioResponseDTO.parse(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findById(Long id) { 
        return UsuarioResponseDTO.parse(usuarioRepository.findById(id));
    }

    @Override
    public List<UsuarioResponseDTO> findByNome(String nome) {
        return usuarioRepository.findByNome(nome)
                                .stream()
                                .map(e -> UsuarioResponseDTO.parse(e)).toList();
    }
    

}
