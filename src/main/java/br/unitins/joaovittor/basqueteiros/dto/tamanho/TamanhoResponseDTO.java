package br.unitins.joaovittor.basqueteiros.dto.tamanho;

import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;

public record TamanhoResponseDTO(
        Long id,
        Integer numero,
        Integer qntEstoque) {

    public static TamanhoResponseDTO valueOf(Tamanho tamanho) {
        return new TamanhoResponseDTO(
                tamanho.getId(),
                tamanho.getNumero(),
                tamanho.getQntEstoque());
    }

}
