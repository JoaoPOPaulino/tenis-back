package br.unitins.joaovittor.basqueteiros.dto.cartao;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.tipo_cartao.TipoCartao;

public record CartaoResponseDTO(
        Long id,
        TipoCartao tipoCartao,
        String numeroMascarado,
        LocalDate validade,
        String titular) {

    public static CartaoResponseDTO valueOf(Cartao cartao) {
        return new CartaoResponseDTO(
                cartao.getId(),
                cartao.getTipoCartao(),
                "****" + cartao.getNumero().substring(12),
                cartao.getValidade(),
                cartao.getTitular()
        );
    }
}
