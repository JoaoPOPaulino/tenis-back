package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import jakarta.validation.constraints.NotBlank;

public record FornecedorDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank(message = "CNPJ é obrigatório")
        String cnpj,
        List<EnderecoDTO> enderecos) {

}
