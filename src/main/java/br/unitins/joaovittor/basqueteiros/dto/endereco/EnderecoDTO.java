package br.unitins.joaovittor.basqueteiros.dto.endereco;

import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        @NotBlank(message = "O estado não pode ser nulo.")
        String estado,
        @NotBlank(message = "A cidade não pode ser nula.")
        String cidade,
        @NotBlank(message = "A quadra não pode ser nula.")
        String quadra,
        @NotBlank(message = "A rua não pode ser nula.")
        @Size(max = 100, message = "O nome da rua não pode ultrapassar 100 caracteres.")
        String rua,
        @NotBlank(message = "O número não pode ser nulo.")
        @Pattern(regexp = "^\\d+$", message = "O número deve ser numérico.")
        Integer numero) {

    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getEstado(),
                endereco.getCidade(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero());
    }

}
