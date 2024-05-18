package br.unitins.joaovittor.basqueteiros.Pedido.dto;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.ItemPedido.dto.ItemPedidoDTO;

public record PedidoDTO(
    Long idCliente,
    List<ItemPedidoDTO> itens
) { }
