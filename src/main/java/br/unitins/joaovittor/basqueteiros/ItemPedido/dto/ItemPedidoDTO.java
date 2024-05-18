package br.unitins.joaovittor.basqueteiros.ItemPedido.dto;

public record ItemPedidoDTO(
    Double preco,
    Double desconto,
    Integer quantidade,
    Long idProduto
) {
}
