package br.unitins.joaovittor.basqueteiros.Fornecedor.dto;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.Fornecedor.model.Fornecedor;

public record FornecedorResponseDTO (
    Long id,
    String nome,
    String cnpj,
    String email,
    String telefone,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
){
    
    public static FornecedorResponseDTO valueof(Fornecedor fornecedor){
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNomeEmpresa(),
            fornecedor.getCnpj(),
            fornecedor.getEmail(),
            fornecedor.getTelefone(),
            fornecedor.getDataCadastro(),
            fornecedor.getDataAlteracao()
        );
    }

}