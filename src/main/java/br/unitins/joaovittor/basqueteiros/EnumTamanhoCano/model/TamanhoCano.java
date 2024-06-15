package br.unitins.joaovittor.basqueteiros.EnumTamanhoCano.model;

public enum TamanhoCano {

    BAIXO(1, "BAIXO"),
    MEDIO(2, "MEDIO"),
    ALTO(3, "ALTO");

    private int id;
    private String descricao;

    TamanhoCano(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public static TamanhoCano valueOf(int id) throws IllegalArgumentException{
        for(TamanhoCano tamanhoCano : TamanhoCano.values()){
            if(tamanhoCano.id == id)
                return tamanhoCano;
        }
        throw new IllegalArgumentException("id de tamanhoCano invalido");
    }

    // GETTERS & SETTERS
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
