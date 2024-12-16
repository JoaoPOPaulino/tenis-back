package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String cnpj,
        EnderecoResponseDTO endereco) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getEndereco() != null
                ? EnderecoResponseDTO.valueOf(fornecedor.getEndereco())
                : null
        );
    }
}
