package br.unitins.joaovittor.basqueteiros.dto.tenis;

import br.unitins.joaovittor.basqueteiros.dto.estoque.EstoqueResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.fornecedor.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.dto.marca.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;

import java.util.Set;
import java.util.stream.Collectors;

public record TenisResponseDTO(
        Long id,
        String nome,
        float preco,
        String descricao,
        MarcaResponseDTO marca,
        Set<FornecedorResponseDTO> fornecedores,
        Set<EstoqueResponseDTO> estoque) {

    public static TenisResponseDTO valueOf(Tenis tenis) {
        return new TenisResponseDTO(
                tenis.getId(),
                tenis.getNome(),
                tenis.getPreco(),
                tenis.getDescricao(),
                MarcaResponseDTO.valueOf(tenis.getMarca()),
                tenis.getFornecedor().stream()
                        .map(FornecedorResponseDTO::valueOf)
                        .collect(Collectors.toSet()),
                tenis.getEstoque().stream()
                        .map(EstoqueResponseDTO::valueOf)
                        .collect(Collectors.toSet())
        );
    }
}
