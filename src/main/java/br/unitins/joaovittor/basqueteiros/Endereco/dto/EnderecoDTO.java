package br.unitins.joaovittor.basqueteiros.Endereco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
    
    @NotBlank(message = "O cep não pode ser nulo ou vazio")
    @Size(min = 7, max = 8, message = "O tamanho do cep deve ser de 8 caracteres.")
    String cep,
    
    @NotBlank(message = "A rua não pode ser nulo ou vazio")
    String rua,

    @NotBlank(message = "O complemento não pode ser nulo ou vazio")
    String complemento
) {
    
}
