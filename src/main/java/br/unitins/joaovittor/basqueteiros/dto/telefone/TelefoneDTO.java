package br.unitins.joaovittor.basqueteiros.dto.telefone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;

public record TelefoneDTO(
        @NotBlank(message = "O código de área não pode ser nulo.")
        @Pattern(regexp = "^\\d{2}$", message = "O código de área deve conter 2 dígitos numéricos.")
        String codigoArea,
        @NotBlank(message = "O número não pode ser nulo.")
        @Pattern(regexp = "^\\d{4,5}-\\d{4}$", message = "Formato inválido para o número. Use o formato XXXX-XXXX ou XXXXX-XXXX.")
        String numero) {

    public static TelefoneDTO valueOf(Telefone telefone) {
        return new TelefoneDTO(
                telefone.getCodigoArea(),
                telefone.getNumero());
    }
}
