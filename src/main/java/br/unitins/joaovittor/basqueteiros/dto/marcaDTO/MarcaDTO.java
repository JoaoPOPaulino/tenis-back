package br.unitins.joaovittor.basqueteiros.dto.marcaDTO;

import jakarta.validation.constraints.NotBlank;

public record MarcaDTO (
    
    Long id,
    @NotBlank(message = "O campo não pode ser nulo.")
    String nome
    ) {
    

}
