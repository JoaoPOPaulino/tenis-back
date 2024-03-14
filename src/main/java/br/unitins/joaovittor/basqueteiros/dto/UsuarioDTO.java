package br.unitins.joaovittor.basqueteiros.dto;

public record UsuarioDTO(
    String nome,
    String email,
    String telefone,
    String cpf,
    Integer diaAniv,
    Integer mesAniv,
    Integer anoAniv
) { }  

