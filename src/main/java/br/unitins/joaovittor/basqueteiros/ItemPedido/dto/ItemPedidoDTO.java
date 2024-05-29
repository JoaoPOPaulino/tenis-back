package br.unitins.joaovittor.basqueteiros.ItemPedido.dto;

public record ItemPedidoDTO(
    Double desconto, // depois, não será o cliente que define o desconto
    Integer quantidade,
    Long idProduto
) {
}
