package br.unitins.joaovittor.basqueteiros.model.tipoUsuario;


public enum TipoUsuario {
    ADMINISTRADOR(1, "Administrador"),
    USUARIO(2, "Usuario");

    //@Id
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

    public static TipoUsuario valueOf(Integer id) throws IllegalArgumentException {
        if ((id == null)) {
            return null;
        }
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            if (tipoUsuario.getId().equals(id)) {
                return tipoUsuario;
            }
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }

}
