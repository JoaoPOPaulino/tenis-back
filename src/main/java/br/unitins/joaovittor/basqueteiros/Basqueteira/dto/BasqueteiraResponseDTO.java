package br.unitins.joaovittor.basqueteiros.Basqueteira.dto;

import br.unitins.joaovittor.basqueteiros.Basqueteira.model.Basqueteira;
import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoResponseDTO;

public record BasqueteiraResponseDTO(
    Long id,
    String nome,
    Double peso,
    String descricao,
    int quantidade,
    Double precoCompra,
    Double precoVenda,
    FornecedorResponseDTO fornecedor,
    MarcaResponseDTO marca,
    TamanhoResponseDTO tamanho
) {
    public static BasqueteiraResponseDTO valueof(Basqueteira basqueteira){
        return new BasqueteiraResponseDTO(basqueteira.getId(), 
        basqueteira.getNome(), 
        basqueteira.getPeso(), 
        basqueteira.getDescricao(), 
        basqueteira.getQuantidade(), 
        basqueteira.getPrecoCompra(), 
        basqueteira.getPrecoVenda(), 
        FornecedorResponseDTO.valueof(basqueteira.getFornecedor()), 
        MarcaResponseDTO.valueof(basqueteira.getMarca()), 
        TamanhoResponseDTO.valueof(basqueteira.getTamanho()));
    }
}
