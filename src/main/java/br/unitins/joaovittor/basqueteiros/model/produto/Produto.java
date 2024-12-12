package br.unitins.joaovittor.basqueteiros.model.produto;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.fornecedor.Fornecedor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Produto extends DefaultEntity {

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Float preco;

    @Column(nullable = false)
    private int estoque;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Fornecedor fornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

}
