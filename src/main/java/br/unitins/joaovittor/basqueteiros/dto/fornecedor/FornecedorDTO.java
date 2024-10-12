package br.unitins.joaovittor.basqueteiros.dto.fornecedor;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.tenis.TenisDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record FornecedorDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        @NotBlank(message = "O CNPJ é obrigatório")
        @Pattern(regexp = "\\d{14}", message = "CNPJ inválido")
        String cnpj,
        @NotNull(message = "Informe o(s) endereco(s)")
        List<EnderecoDTO> enderecos,
        List<TenisDTO> tenis) {

}
