package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;

import java.util.List;

public record FornecedorResponseDTO(
        Long id,
        String nome,
        String cnpj,
        Endereco endereco,
        String email,
        List<Telefone> telefones,
        List<Tenis> produtosFornecidos) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor) {
        return new FornecedorResponseDTO(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getCnpj(),
                fornecedor.getEndereco(),
                fornecedor.getEmail(),
                fornecedor.getListaTelefone(),
                fornecedor.getProdutosFornecidos()
        );
    }
}
