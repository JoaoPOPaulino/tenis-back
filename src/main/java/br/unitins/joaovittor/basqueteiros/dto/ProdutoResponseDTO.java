package br.unitins.joaovittor.basqueteiros.dto;

import br.unitins.joaovittor.basqueteiros.model.Produto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String descricao,
    int quantidade,
    Double precoCompra,
    Double precoVenda,
    FornecedorResponseDTO fornecedor
) {
    public static ProdutoResponseDTO parse(Produto produto){
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(), 
            produto.getDescricao(), 
            produto.getQuantidade(), 
            produto.getPrecoCompra(), 
            produto.getPrecoVenda(), 
            FornecedorResponseDTO.parse(produto.getFornecedor())
        );
    }
}
