package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String cnpj,
        List<EnderecoResponseDTO> enderecos) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getEnderecos().stream()
                        .map(EnderecoResponseDTO::valueOf)
                        .collect(Collectors.toList())
        );
    }
}
