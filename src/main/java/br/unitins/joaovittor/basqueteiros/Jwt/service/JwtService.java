package br.unitins.joaovittor.basqueteiros.Jwt.service;

import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;

public interface JwtService {
    String generateJwt(UsuarioResponseDTO dto);
}
