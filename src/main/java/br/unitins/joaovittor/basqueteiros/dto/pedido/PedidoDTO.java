package br.unitins.joaovittor.basqueteiros.dto.pedido;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.item.ItemDTO;
import jakarta.validation.constraints.NotNull;

public record PedidoDTO(
        List<ItemDTO> itens,
        @NotNull(message = "O endereço é obrigatório")
        Long idEndereco,
        @NotNull
        Long idCartao) {

}
