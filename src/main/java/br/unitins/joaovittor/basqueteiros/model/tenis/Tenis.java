package br.unitins.joaovittor.basqueteiros.model.tenis;

import java.util.Objects;

import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.model.produto.Produto;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tenis extends Produto {

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_marca", nullable = false)
    private Marca marca;

    @Column(nullable = false)
    private String modelo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tamanho tamanho;

    public Tenis() {
    }

    public Tenis(String modelo, Marca marca, Tamanho tamanho) {
        this.modelo = modelo;
        this.marca = marca;
        this.tamanho = tamanho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = Objects.requireNonNull(marca, "Marca não pode ser nula");
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = Objects.requireNonNull(modelo, "Modelo não pode ser nulo");
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = Objects.requireNonNull(tamanho, "Tamanho não pode ser nulo");
    }
}
