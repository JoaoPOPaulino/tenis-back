package br.unitins.joaovittor.basqueteiros.dto.tenis;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;

public record TenisResponseDTO(
        Long id,
        MarcaResponseDTO marca,
        String nome,
        float preco,
        Integer estoque,
        String modelo,
        String descricao) {

    public static TenisResponseDTO valueOf(Tenis tenis) {
        return new TenisResponseDTO(
                tenis.getId(),
                MarcaResponseDTO.valueOf(tenis.getMarca()),
                tenis.getNome(),
                tenis.getPreco(),
                tenis.getEstoque(),
                tenis.getModelo(),
                tenis.getDescricao()
        );
    }
}
