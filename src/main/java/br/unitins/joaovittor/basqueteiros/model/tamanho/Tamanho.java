package br.unitins.joaovittor.basqueteiros.model.tamanho;

public enum Tamanho {
    PP(38),
    P(39),
    M(40),
    G(41),
    GG(42),
    X(43),
    XG(44);

    private final Integer numero;

    Tamanho(Integer numero) {
        this.numero = numero;
    }

    public Integer getNumero() {
        return numero;
    }

}
