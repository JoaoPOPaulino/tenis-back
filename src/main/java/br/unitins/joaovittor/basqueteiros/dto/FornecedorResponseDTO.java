package br.unitins.joaovittor.basqueteiros.dto;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.model.Fornecedor;

public record FornecedorResponseDTO (
    Long id,
    String nome,
    String email,
    String telefone,
    LocalDate dataCadastro
){
    
    public static FornecedorResponseDTO parse(Fornecedor fornecedor){
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNomeEmpresa(),
            fornecedor.getEmail(),
            fornecedor.getTelefone(),
            fornecedor.getDataCadastro()
        );
    }

}