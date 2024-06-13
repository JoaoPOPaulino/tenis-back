package br.unitins.joaovittor.basqueteiros.Usuario.service;

import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;

public interface UsuarioService {
    public Usuario findByUsername(String username);
    public void update(Usuario usuario);
}
