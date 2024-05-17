package br.unitins.joaovittor.basqueteiros.Funcionario.dto;

import jakarta.validation.constraints.NotBlank;

public record FuncionarioDTO(
    // Parte Pessoa
    @NotBlank
    String nome,
    String telefone,
    int diaNasc,
    int mesNasc,
    int anoNasc,

    // Parte PessoaFisica
    String cpf,
    String username,
    String senha,

    // Parte Funcionario
    String codigoContrato,
    int diaAdmissao,
    int mesAdmissao,
    int anoAdmissao
) {}
