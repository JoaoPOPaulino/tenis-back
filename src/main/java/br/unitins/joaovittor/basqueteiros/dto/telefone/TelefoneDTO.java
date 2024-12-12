package br.unitins.joaovittor.basqueteiros.dto.telefone;

import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TelefoneDTO(
        @NotBlank(message = "DDD é obrigatório")
        @Pattern(regexp = "\\d{2}", message = "DDD deve ter 2 dígitos")
        String ddd,
        @NotBlank(message = "Número é obrigatório")
        @Pattern(regexp = "\\d{8,9}", message = "Número deve ter 8 ou 9 dígitos")
        String numero) {

    public static TelefoneDTO from(Telefone telefone) {
        return new TelefoneDTO(
                telefone.getDdd(),
                telefone.getNumero()
        );
    }
}
