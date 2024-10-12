package br.unitins.joaovittor.basqueteiros.model.tenis;

import br.unitins.joaovittor.basqueteiros.model.marca.Marca;
import br.unitins.joaovittor.basqueteiros.model.produto.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tenis extends Produto {

    @Column(length = 100000)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    private String modelo;

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

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
