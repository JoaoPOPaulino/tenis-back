package br.unitins.joaovittor.basqueteiros.Meia.dto;

import br.unitins.joaovittor.basqueteiros.Cor.dto.CorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Meia.model.Meia;

public record MeiaResponseDTO(
    Long id,
    String nome,
    int qtdPares,
    String descricao,
    int quantidade,
    Double precoCompra,
    Double precoVenda,
    FornecedorResponseDTO fornecedor,
    MarcaResponseDTO marca,
    CorResponseDTO cor
) {
    public static MeiaResponseDTO parse(Meia meia){
        return new MeiaResponseDTO(meia.getId(), 
        meia.getNome(), 
        meia.getQtdPares(), 
        meia.getDescricao(), 
        meia.getQuantidade(), 
        meia.getPrecoCompra(), 
        meia.getPrecoVenda(), 
        FornecedorResponseDTO.parse(meia.getFornecedor()), 
        MarcaResponseDTO.parse(meia.getMarca()), 
        CorResponseDTO.parse(meia.getCor()));
    }
}
