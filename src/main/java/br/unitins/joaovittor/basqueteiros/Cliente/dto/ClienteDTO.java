package br.unitins.joaovittor.basqueteiros.Cliente.dto;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoDTO;

public record ClienteDTO(
    // Parte Pessoa
    String nome,
    String telefone,
    int diaNasc,
    int mesNasc,
    int anoNasc,

    // Parte PessoaFisica
    String cpf,
    String username,
    String senha,

    // Parte Cliente
    Double saldo,
    List<EnderecoDTO> listaEndereco
) {}
