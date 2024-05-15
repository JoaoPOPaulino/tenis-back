package br.unitins.joaovittor.basqueteiros.Usuario.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import br.unitins.joaovittor.basqueteiros.Usuario.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    UsuarioRepository repository;

    @Override
    @Transactional
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto) {
        
        Usuario usuario = new Usuario();

        verificarUsername(dto.username());

        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
        
        repository.persist(usuario);
        return UsuarioResponseDTO.valueof(usuario);
    }

    // testar p ver se unique(jpa) vem antes ou o method verificar

    public void verificarUsername(String login){
        Usuario u = repository.findByUsername(login);
        if(u != null)
            throw new ValidationException("login", "O login '"+login+"' ja existe");
    }

    @Override
    @Transactional
    public boolean delete(Long id) { 
        return repository.deleteById(id); 
    }

    @Override
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = repository.findById(id);

        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
    }

    @Override
    public List<UsuarioResponseDTO> findAll() {
        return repository.findAll()
                         .stream()
                         .map(e -> UsuarioResponseDTO.valueof(e)).toList();
    }

    @Override
    public UsuarioResponseDTO findById(Long id) { 
        Usuario usuario = repository.findById(id);
        if(usuario != null)
            return UsuarioResponseDTO.valueof(usuario);
        return null;
    }

    @Override
    public UsuarioResponseDTO findByUsername(String username) {
        return UsuarioResponseDTO.valueof(repository.findByUsername(username));
    }
    

}
