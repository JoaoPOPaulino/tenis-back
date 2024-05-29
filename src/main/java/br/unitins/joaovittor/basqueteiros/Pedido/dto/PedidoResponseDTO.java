package br.unitins.joaovittor.basqueteiros.Pedido.dto;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteResponseDTO;
import br.unitins.joaovittor.basqueteiros.ItemPedido.dto.ItemPedidoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Pedido.model.Pedido;

public record PedidoResponseDTO(
    Long id,
    ClienteResponseDTO cliente,
    Double total,
    List<ItemPedidoResponseDTO> itens
) 
    {
        public static PedidoResponseDTO valueOf(Pedido pedido){
            List<ItemPedidoResponseDTO> lista = pedido.getItens()
                                                .stream()
                                                .map(ItemPedidoResponseDTO::valueOf)
                                                .toList();

            return new PedidoResponseDTO(
                pedido.getId(), 
                ClienteResponseDTO.valueof(pedido.getCliente()),
                pedido.getValorTotal(),
                lista);
        }
    } 