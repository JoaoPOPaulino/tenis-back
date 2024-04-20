package br.unitins.joaovittor.basqueteiros.Fornecedor.dto;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.Fornecedor.model.Fornecedor;

public record FornecedorResponseDTO (
    Long id,
    String nome,
    String cnpj,
    String email,
    String telefone,
    LocalDate dataCadastro
){
    
    public static FornecedorResponseDTO parse(Fornecedor fornecedor){
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNomeEmpresa(),
            fornecedor.getCnpj(),
            fornecedor.getEmail(),
            fornecedor.getTelefone(),
            fornecedor.getDataCadastro()
        );
    }

}