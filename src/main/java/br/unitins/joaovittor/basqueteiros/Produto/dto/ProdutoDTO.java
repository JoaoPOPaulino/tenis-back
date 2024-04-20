package br.unitins.joaovittor.basqueteiros.Produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoDTO(

    @NotBlank(message = "O nome nao pode estar vazio ou nulo")
    String nome,
    String descricao,
    int quantidade,

    @NotNull
    Double precoCompra,

    @NotNull
    Double precoVenda,

    Long idFornecedor,
    Long idMarca
) {
}