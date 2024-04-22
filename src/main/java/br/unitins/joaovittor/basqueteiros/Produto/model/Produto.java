package br.unitins.joaovittor.basqueteiros.Produto.model;

import br.unitins.joaovittor.basqueteiros.DefaultEntity.model.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.Fornecedor.model.Fornecedor;
import br.unitins.joaovittor.basqueteiros.Marca.model.Marca;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Produto extends DefaultEntity{
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;
    
    @Column(name = "quantidade")
    private int quantidade;

    @Column(name = "preco_compra")
    private Double precoCompra;

    @Column(name = "preco_venda")
    private Double precoVenda;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")  
    private Fornecedor fornecedor;

    @ManyToOne
    @JoinColumn(name = "id_marca")  
    private Marca marca;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(Double precoCompra) {
        this.precoCompra = precoCompra;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
