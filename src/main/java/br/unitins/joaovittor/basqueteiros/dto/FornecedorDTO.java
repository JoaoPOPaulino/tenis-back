package br.unitins.joaovittor.basqueteiros.dto;

public record FornecedorDTO(
    String nome,
    String email,
    String telefone,
    //data_cadastro
    Integer dia,
    Integer mes,
    Integer ano
) {
    
}
