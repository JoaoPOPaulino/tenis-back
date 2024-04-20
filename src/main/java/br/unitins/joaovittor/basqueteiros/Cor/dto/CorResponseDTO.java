package br.unitins.joaovittor.basqueteiros.Cor.dto;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.Cor.model.Cor;

public record CorResponseDTO(
    Long id,
    String nome,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao
) {
    public static CorResponseDTO parse(Cor cor){
        return new CorResponseDTO(cor.getId(), 
                                cor.getNome(), 
                                cor.getDataCadastro(), 
                                cor.getDataAlteracao());
    }
}
