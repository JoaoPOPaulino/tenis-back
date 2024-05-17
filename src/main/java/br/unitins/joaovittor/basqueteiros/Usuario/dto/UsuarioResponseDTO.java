package br.unitins.joaovittor.basqueteiros.Usuario.dto;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.PessoaFisica.model.PessoaFisica;

public record UsuarioResponseDTO (
    Long id,
    String username,
    String nome,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
){
    
    public static UsuarioResponseDTO valueof(PessoaFisica pf){
        return new UsuarioResponseDTO(
            pf.getId(),
            pf.getUsuario().getUsername(),
            pf.getNome(),
            pf.getDataCadastro(),
            pf.getDataAlteracao()
        );
    }

}
