package br.unitins.joaovittor.basqueteiros.ItemPedido.dto;

import jakarta.validation.constraints.NotBlank;

public record ItemPedidoDTO(
    Double desconto,
    
    @NotBlank(message = "Defina uma quantidade de produto")
    Integer quantidade,

    @NotBlank(message = "Escolha um produto")
    Long idProduto
) {
}
