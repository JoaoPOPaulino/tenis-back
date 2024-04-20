package br.unitins.joaovittor.basqueteiros.Tamanho.dto;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.Tamanho.model.Tamanho;

public record TamanhoResponseDTO(
    Long id,
    Integer numeracao,
    String tamanhoEmCm,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static TamanhoResponseDTO parse(Tamanho tamanho){
        return new TamanhoResponseDTO(tamanho.getId(),
                                    tamanho.getNumeracao(), 
                                    tamanho.getTamanhoEmCm(), 
                                    tamanho.getDataCadastro(), 
                                    tamanho.getDataAlteracao());
    }
}
