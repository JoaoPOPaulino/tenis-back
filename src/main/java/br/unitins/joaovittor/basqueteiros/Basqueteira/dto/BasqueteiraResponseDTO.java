package br.unitins.joaovittor.basqueteiros.Basqueteira.dto;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.Basqueteira.model.Basqueteira;
import br.unitins.joaovittor.basqueteiros.EnumTamanhoCano.model.TamanhoCano;
import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoResponseDTO;

public record BasqueteiraResponseDTO(
    Long id,
    String nome,
    TamanhoCano tamanhoCano,
    Double peso,
    String descricao,
    int quantidade,
    Double precoCompra,
    Double precoVenda,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao,
    FornecedorResponseDTO fornecedor,
    MarcaResponseDTO marca,
    TamanhoResponseDTO tamanho
) {
    public static BasqueteiraResponseDTO valueof(Basqueteira basqueteira){
        return new BasqueteiraResponseDTO(basqueteira.getId(), 
        basqueteira.getNome(),
        basqueteira.getTamanhoCano(), 
        basqueteira.getPeso(), 
        basqueteira.getDescricao(), 
        basqueteira.getQuantidade(), 
        basqueteira.getPrecoCompra(), 
        basqueteira.getPrecoVenda(), 
        basqueteira.getDataCadastro(),
        basqueteira.getDataAlteracao(),
        FornecedorResponseDTO.valueof(basqueteira.getFornecedor()), 
        MarcaResponseDTO.valueof(basqueteira.getMarca()), 
        TamanhoResponseDTO.valueof(basqueteira.getTamanho()));
    }
}
