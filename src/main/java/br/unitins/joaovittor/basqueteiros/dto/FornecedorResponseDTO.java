package br.unitins.joaovittor.basqueteiros.dto;

import br.unitins.joaovittor.basqueteiros.model.Fornecedor;

public record FornecedorResponseDTO (
    Long id,
    String nome,
    String email,
    String telefone
){
    
    public static FornecedorResponseDTO parse(Fornecedor fornecedor){
        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getEmail(),
            fornecedor.getTelefone()
        );
    }

}