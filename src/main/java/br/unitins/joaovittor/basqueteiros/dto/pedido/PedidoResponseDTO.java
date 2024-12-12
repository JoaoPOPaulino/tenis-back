package br.unitins.joaovittor.basqueteiros.dto.pedido;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.cartao.CartaoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.item.ItemResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.pedido.Pedido;

public record PedidoResponseDTO(
        Long id,
        LocalDateTime data,
        Float total,
        List<ItemResponseDTO> itens,
        EnderecoResponseDTO endereco, // Mudando para EnderecoResponseDTO
        CartaoResponseDTO cartao) {

    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getData(),
                pedido.getTotal(),
                pedido.getItens().stream()
                        .map(ItemResponseDTO::valueOf)
                        .collect(Collectors.toList()),
                EnderecoResponseDTO.valueOf(pedido.getEndereco()),
                CartaoResponseDTO.valueOf(pedido.getCartao())
        );
    }
}
