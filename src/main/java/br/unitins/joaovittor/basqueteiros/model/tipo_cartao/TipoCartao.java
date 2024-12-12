package br.unitins.joaovittor.basqueteiros.model.tipo_cartao;

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

    public String getLabel() {
        return label;
    }
}
