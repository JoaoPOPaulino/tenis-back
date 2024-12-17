package br.unitins.joaovittor.basqueteiros.model.tipo_usuario;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoUsuario {
    ADMINISTRADOR(1, "Administrador"),
    USUARIO(2, "Usuario");

    private final Integer id;
    private final String label;

    TipoUsuario(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public boolean isAdmin() {
        return this == ADMINISTRADOR;
    }

    @JsonCreator
    public static TipoUsuario fromId(@JsonProperty("id") Integer id) {
        return Arrays.stream(values())
                .filter(t -> t.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("ID de Tipo de Usuário inválido: " + id));
    }
}