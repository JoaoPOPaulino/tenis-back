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
    FornecedorResponseDTO fornecedor,
    MarcaResponseDTO marca
) {
    public static ProdutoResponseDTO parse(Produto produto){
        return new ProdutoResponseDTO(
            produto.getId(),
            produto.getNome(), 
            produto.getDescricao(), 
            produto.getQuantidade(), 
            produto.getPrecoCompra(), 
            produto.getPrecoVenda(), 
            FornecedorResponseDTO.parse(produto.getFornecedor()),
            MarcaResponseDTO.parse(produto.getMarca())
        );
    }
}
