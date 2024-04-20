package br.unitins.joaovittor.basqueteiros.Usuario.dto;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;

public record UsuarioResponseDTO (
    Long id,
    String nome,
    String email,
    String telefone,
    String cpf,
    LocalDate dataNascimento
){
    
    public static UsuarioResponseDTO parse(Usuario usuario){
        return new UsuarioResponseDTO(usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getTelefone(),
            usuario.getCpf(),
            usuario.getDataNascimento());
    }

}
