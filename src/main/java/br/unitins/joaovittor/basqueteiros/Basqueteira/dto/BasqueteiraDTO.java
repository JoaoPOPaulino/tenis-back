package br.unitins.joaovittor.basqueteiros.Basqueteira.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BasqueteiraDTO(
    @NotBlank(message = "O nome nao pode estar vazio ou nulo")
    String nome,
    String descricao,
    Double peso,
    int quantidade,

    @NotNull
    Double precoCompra,

    @NotNull
    Double precoVenda,

    Long idFornecedor,
    Long idMarca,
    Long idTamanho
) { }
