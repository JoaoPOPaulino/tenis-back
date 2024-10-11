package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FornecedorDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O CNPJ é obrigatório")
        @Pattern(regexp = "\\d{14}", message = "CNPJ inválido")
        String cnpj,
        @NotBlank(message = "O endereco é obrigatório")
        Endereco endereco,
        @NotBlank(message = "O email é obrigatório")
        String email,
        List<Telefone> telefone,
        List<Long> produtosFornecidos) {

}
