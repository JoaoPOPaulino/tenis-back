package br.unitins.joaovittor.basqueteiros.dto.item;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.item.Item;

public record ItemResponseDTO(
        Integer quantidade,
        Float preco,
        Long idTenis,
        String modelo) {

    public static ItemResponseDTO valueOf(Item item) {
        return new ItemResponseDTO(
                item.getQuantidade(),
                item.getPreco(),
                item.getTenis().getId(),
                item.getTenis().getModelo());
    }

    public static List<ItemResponseDTO> valueOf(List<Item> item) {
        return item.stream().map(itens -> ItemResponseDTO.valueOf(itens)).toList();
    }
}
