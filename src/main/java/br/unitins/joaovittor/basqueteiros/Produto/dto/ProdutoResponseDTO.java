package br.unitins.joaovittor.basqueteiros.Produto.dto;

import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;

public record ProdutoResponseDTO(
    Long id,
    String nome,
    String descricao,
    int quantidade,
    Double precoCompra,
    Double precoVenda,
    String nomeImagem,
    FornecedorResponseDTO fornecedor,
    MarcaResponseDTO marca
) {
    public static ProdutoResponseDTO valueof(Produto produto){
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(), 
            produto.getDescricao(), 
            produto.getQuantidade(), 
            produto.getPrecoCompra(), 
            produto.getPrecoVenda(), 
            produto.getNomeImagem(),
            FornecedorResponseDTO.valueof(produto.getFornecedor()),
            MarcaResponseDTO.valueof(produto.getMarca())
        );
    }
}
