package br.unitins.joaovittor.basqueteiros.service.jwt;

import br.unitins.joaovittor.basqueteiros.dto.UsuarioResponseDTO;

public interface JwtService {

    String generateJwt(UsuarioResponseDTO dto);
}
