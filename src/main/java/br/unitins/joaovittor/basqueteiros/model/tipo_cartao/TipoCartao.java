package br.unitins.joaovittor.basqueteiros.model.tipo_cartao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCartao {
    CREDITO(1, "Cartão de Crédito"),
    DEBITO(2, "Cartão de Débito");

    private final int id;
    private final String label;

    TipoCartao(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    public static TipoCartao fromId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID não pode ser nulo");
        }

        for (TipoCartao tipo : values()) {
            if (tipo.getId() == id) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("ID de tipo de cartão inválido: " + id);
    }
}
