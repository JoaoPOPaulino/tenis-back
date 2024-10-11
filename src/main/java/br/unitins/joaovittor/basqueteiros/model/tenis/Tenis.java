package br.unitins.joaovittor.basqueteiros.model.tenis;

import java.util.Set;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.estoque.Estoque;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Tenis extends DefaultEntity {

    private String nome;
    private float preco;
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @OneToMany(mappedBy = "tenis", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Estoque> estoque;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(name = "tenis_fornecedor", joinColumns = @JoinColumn(name = "id_tenis"), inverseJoinColumns = @JoinColumn(name = "id_fornecedor"))
    private Set<Fornecedor> fornecedor;

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

    public Set<Estoque> getEstoque() {
        return estoque;
    }

    public void setEstoque(Set<Estoque> estoque) {
        this.estoque = estoque;
    }

    public Set<Fornecedor> getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Set<Fornecedor> fornecedor) {
        this.fornecedor = fornecedor;
    }

}
