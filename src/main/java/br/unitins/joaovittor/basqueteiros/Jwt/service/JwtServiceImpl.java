package br.unitins.joaovittor.basqueteiros.Jwt.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import io.smallrye.jwt.build.Jwt;

public class JwtServiceImpl implements JwtService{

    private static final Duration EXPIRATION_TIME = Duration.ofHours(1);

    @Override
    public String generateJwt(UsuarioResponseDTO dto) {
        
        Instant expiryDate = Instant.now().plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();
        roles.add("Cliente");

        return Jwt.issuer("teste-jwt")
            .subject(dto.username())
            .groups(roles)
            .expiresAt(expiryDate)
            .sign();
    }
    
}
