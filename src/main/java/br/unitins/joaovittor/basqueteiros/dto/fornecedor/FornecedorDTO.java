package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;

public record FornecedorDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CNPJ é obrigatório")
        String cnpj,
        @NotNull
        EnderecoDTO endereco) {

}
