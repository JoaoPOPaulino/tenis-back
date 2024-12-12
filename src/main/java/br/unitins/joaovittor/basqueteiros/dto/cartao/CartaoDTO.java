package br.unitins.joaovittor.basqueteiros.dto.cartao;

import java.time.LocalDate;

public record CartaoDTO(
        Integer tipoCartao,
        String numero,
        String cvv,
        LocalDate validade,
        String titular,
        String cpf) {

}
