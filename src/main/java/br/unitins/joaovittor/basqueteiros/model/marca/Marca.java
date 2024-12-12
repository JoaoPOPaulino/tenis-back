package br.unitins.joaovittor.basqueteiros.model.marca;

import java.util.Objects;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Marca extends DefaultEntity {

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false, length = 100, unique = true)
    private String nome;

    public Marca() {
    }

    public Marca(String nome) {
        this.nome = nome;
    }

    // Getter e Setter
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo").trim();
    }
}
