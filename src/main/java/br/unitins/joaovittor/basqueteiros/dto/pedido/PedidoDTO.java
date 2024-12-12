package br.unitins.joaovittor.basqueteiros.dto.pedido;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.item.ItemDTO;

public record PedidoDTO(
        Long idEndereco,
        Long idCartao,
        List<ItemDTO> itens) {

}
