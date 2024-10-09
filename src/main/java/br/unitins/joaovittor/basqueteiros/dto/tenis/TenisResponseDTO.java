package br.unitins.joaovittor.basqueteiros.dto.tenis;

import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.tamanho.TamanhoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;

import java.util.Set;
import java.util.stream.Collectors;

public record TenisResponseDTO(
        Long id,
        String nome,
        float preco,
        String descricao,
        MarcaResponseDTO marca,
        Set<TamanhoResponseDTO> tamanhos) {

    public static TenisResponseDTO valueOf(Tenis tenis) {
        return new TenisResponseDTO(
                tenis.getId(),
                tenis.getNome(),
                tenis.getPreco(),
                tenis.getDescricao(),
                MarcaResponseDTO.valueOf(tenis.getMarca()),
                tenis.getTamanho().stream()
                        .map(TamanhoResponseDTO::valueOf)
                        .collect(Collectors.toSet()));
    }
}
