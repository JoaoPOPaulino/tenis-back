package br.unitins.joaovittor.basqueteiros.Marca.dto;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.Marca.model.Marca;

public record MarcaResponseDTO (
    Long id,
    String nome,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao    
){
    public static MarcaResponseDTO parse(Marca marca) {
        return new MarcaResponseDTO(
                                    marca.getId(), 
                                    marca.getNome(),
                                    marca.getDataCadastro(),
                                    marca.getDataAlteracao());
    }
}
