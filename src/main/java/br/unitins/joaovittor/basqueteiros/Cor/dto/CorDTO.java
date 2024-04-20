package br.unitins.joaovittor.basqueteiros.Cor.dto;

import jakarta.validation.constraints.NotBlank;

public record CorDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    String nome
) {}
