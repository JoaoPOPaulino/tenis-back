package br.unitins.joaovittor.basqueteiros.dto.cartao;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.tipoCartao.Tipo;

public record CartaoResponseDTO(
        Long id,
        Tipo tipo,
        String numero,
        String cvv,
        LocalDate validade,
        String titular,
        String cpf) {

    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getTipo(),
                cartao.getNumero(),
                cartao.getCvv(),
                cartao.getValidade(),
                cartao.getTitular(),
                cartao.getCpf());
    }
}
