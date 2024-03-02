package br.unitins.joaovittor.basqueteiros.dto;

public record UsuarioDTO(
    String nome,
    String email,
    String telefone,
    String cpf,
    Integer dia,
    Integer mes,
    Integer ano
) { }  

