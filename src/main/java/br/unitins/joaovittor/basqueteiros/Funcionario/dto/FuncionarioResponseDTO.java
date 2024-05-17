package br.unitins.joaovittor.basqueteiros.Funcionario.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.Funcionario.model.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    String nome,
    String telefone,
    LocalDate dataNascimento,
    String cpf,
    String codigoContrato,
    LocalDate dataAdmissao,
    String username,
    String senha,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static FuncionarioResponseDTO valueof(Funcionario funcionario){

        return new FuncionarioResponseDTO(funcionario.getId(), 
                                funcionario.getPessoaFisica().getNome(),
                                funcionario.getPessoaFisica().getTelefone(),
                                funcionario.getPessoaFisica().getDataNascimento(),
                                funcionario.getPessoaFisica().getCpf(),
                                funcionario.getCodigoContrato(), 
                                funcionario.getDataAdmissao(),
                                funcionario.getPessoaFisica().getUsuario().getUsername(),
                                funcionario.getPessoaFisica().getUsuario().getPassword(),
                                funcionario.getDataCadastro(), 
                                funcionario.getDataAlteracao()
                                );
    }
}
