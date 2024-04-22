package br.unitins.joaovittor.basqueteiros.Usuario.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {
    
    public UsuarioResponseDTO create(@Valid UsuarioDTO dto);
    public void update(Long id, UsuarioDTO dto);
    public boolean delete(Long id);
    public List<UsuarioResponseDTO> findAll();
    public UsuarioResponseDTO findById(Long id);
    public List<UsuarioResponseDTO> findByNome(String nome);
    
}
