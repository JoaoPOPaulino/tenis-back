package br.unitins.joaovittor.basqueteiros.dto.tenis;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;

public record TenisResponseDTO(
        Long id,
        String descricao,
        MarcaResponseDTO marca,
        String modelo,
        Tamanho tamanho,
        Float preco) {

    public static TenisResponseDTO valueOf(Tenis tenis) {
        return new TenisResponseDTO(
                tenis.getId(),
                tenis.getDescricao(),
                MarcaResponseDTO.valueOf(tenis.getMarca()),
                tenis.getModelo(),
                tenis.getTamanho(),
                tenis.getPreco()
        );
    }
}
