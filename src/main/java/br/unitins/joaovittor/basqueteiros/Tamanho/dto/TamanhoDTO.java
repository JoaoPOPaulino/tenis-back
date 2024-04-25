package br.unitins.joaovittor.basqueteiros.Tamanho.dto;

import jakarta.validation.constraints.NotBlank;

public record TamanhoDTO(

    @NotBlank(message = "a numeração não pode ser nula ou vazia")
    Integer numeracao,

    String tamanhoEmCm
) { }
