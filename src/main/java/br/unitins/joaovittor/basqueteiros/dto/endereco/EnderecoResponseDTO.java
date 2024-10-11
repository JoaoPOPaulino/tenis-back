package br.unitins.joaovittor.basqueteiros.dto.endereco;

import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;

public record EnderecoResponseDTO (

    String estado,
    String cidade,
    String quadra,
    String rua,
    Integer numero)  
    {
    public static EnderecoResponseDTO valueOf (Endereco endereco) {
        return new EnderecoResponseDTO(
            endereco.getEstado(),
            endereco.getCidade(),
           endereco.getQuadra(),
            endereco.getRua(),
            endereco.getNumero() );

    }
}
