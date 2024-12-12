package br.unitins.joaovittor.basqueteiros.dto.item;

import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.item.Item;

public record ItemResponseDTO(
        Long id,
        Integer quantidade,
        Float preco,
        Float subTotal,
        TenisResponseDTO tenis) {

    public static ItemResponseDTO valueOf(Item item) {
        return new ItemResponseDTO(
                item.getId(),
                item.getQuantidade(),
                item.getPreco(),
                item.getSubTotal(),
                TenisResponseDTO.valueOf(item.getTenis())
        );
    }
}
