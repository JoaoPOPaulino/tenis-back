package br.unitins.joaovittor.basqueteiros.Usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioDTO(

    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    @Size(min = 4, max = 60, message = "O tamanho do nome deve ser entre 2 e 60 caracteres.")
    String nome,

    String login,

    @Email(message = "O email deve ser válido!")
    String email,

    String telefone,

    @NotNull
    String cpf,
    Integer diaAniv,
    Integer mesAniv,
    Integer anoAniv
) { }  

