package br.unitins.joaovittor.basqueteiros.Cliente.dto;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
    // Parte Pessoa
    @NotBlank
    String nome,

    String telefone,
    int diaNasc,
    int mesNasc,
    int anoNasc,

    // Parte PessoaFisica
    String cpf,

    @NotBlank
    String username,
    
    @NotBlank
    String senha,

    // Parte Cliente
    List<EnderecoDTO> listaEndereco
) {}
