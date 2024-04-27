package br.unitins.joaovittor.basqueteiros.Fornecedor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FornecedorDTO(
    
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 2 e 60 caracteres.")
    String nomeEmpresa,

    @Email
    String email,

    @NotBlank(message = "O cnpj não pode ser nulo ou vazio")
    @Size(min = 12, max = 20, message = "O tamanho do cnpj deve ser 14")
    String cnpj,

    String telefone
) {
    
}
