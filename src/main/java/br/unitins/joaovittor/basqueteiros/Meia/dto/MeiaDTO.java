package br.unitins.joaovittor.basqueteiros.Meia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MeiaDTO(
    @NotBlank(message = "O nome nao pode estar vazio ou nulo")
    String nome,
    String descricao,
    int quantidade,
    int qtdPares,
    @NotNull
    Double precoCompra,
    @NotNull
    Double precoVenda,
    Long idFornecedor,
    Long idMarca,
    Long idCor
  
) { }
