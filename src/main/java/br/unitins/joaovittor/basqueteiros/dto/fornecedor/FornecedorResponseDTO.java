package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String cnpj,
        List<EnderecoDTO> enderecos) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                List.of(EnderecoDTO.valueOf(fornecedor.getEndereco())));
    }
}
