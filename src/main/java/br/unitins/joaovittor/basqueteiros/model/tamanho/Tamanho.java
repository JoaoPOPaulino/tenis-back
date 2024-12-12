package br.unitins.joaovittor.basqueteiros.model.tamanho;

public enum Tamanho {
    PP(38, "PP - 38"),
    P(39, "P - 39"),
    M(40, "M - 40"),
    G(41, "G - 41"),
    GG(42, "GG - 42"),
    X(43, "X - 43"),
    XG(44, "XG - 44");

    private final Integer numero;
    private final String descricao;

    Tamanho(Integer numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Tamanho fromNumero(Integer numero) {
        for (Tamanho t : values()) {
            if (t.getNumero().equals(numero)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Número de tamanho inválido: " + numero);
    }
}
