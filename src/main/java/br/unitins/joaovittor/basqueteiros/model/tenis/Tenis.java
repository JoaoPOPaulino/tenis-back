package br.unitins.joaovittor.basqueteiros.model.tenis;

import java.util.Set;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.model.tamanho.Tamanho;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

public class Tenis extends DefaultEntity {

    private String nome;
    private float preco;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToMany
    @JoinTable(name = "tenis_tamanho", joinColumns = @JoinColumn(name = "tenis_id"), inverseJoinColumns = @JoinColumn(name = "tamanho_id"))
    private Set<Tamanho> tamanho;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
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
        this.marca = marca;
    }

    public Set<Tamanho> getTamanho() {
        return tamanho;
    }

    public void setTamanho(Set<Tamanho> tamanho) {
        this.tamanho = tamanho;
    }

}
