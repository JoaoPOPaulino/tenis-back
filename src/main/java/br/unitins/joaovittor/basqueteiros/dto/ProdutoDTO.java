package br.unitins.joaovittor.basqueteiros.dto;


public record ProdutoDTO(
    String nome,
    String descricao,
    int quantidade,
    Double precoCompra,
    Double precoVenda,
    Long idFornecedor
) {
}