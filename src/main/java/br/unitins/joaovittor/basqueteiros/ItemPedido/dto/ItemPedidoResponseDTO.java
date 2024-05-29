package br.unitins.joaovittor.basqueteiros.ItemPedido.dto;

import br.unitins.joaovittor.basqueteiros.ItemPedido.model.ItemPedido;

public record ItemPedidoResponseDTO (
    Long id,
    String nomeProduto,
    Double valorProduto,
    String nomeMarcaProduto,
    Double desconto,
    Integer quantidade
) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getId(), 
            item.getProduto().getNome(), 
            item.getValor(),
            item.getProduto().getMarca().getNome(),
            item.getDesconto(),
            item.getQuantidade());
    }
}
