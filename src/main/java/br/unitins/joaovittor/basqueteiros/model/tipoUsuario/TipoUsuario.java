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

    public static TipoUsuario fromId(Integer id) throws IllegalArgumentException {
        // Verifica se o ID é nulo ou menor/igual a zero
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID inválido: " + id);
        }
    
        // Itera sobre os valores de TipoUsuario
        for (TipoUsuario tipoUsuario : TipoUsuario.values()) {
            if (tipoUsuario.getId().equals(id)) {
                return tipoUsuario;
            }
        }
    
        // Se não encontrar, lança exceção
        throw new IllegalArgumentException("ID não corresponde a nenhum TipoUsuario: " + id);
    }

}
