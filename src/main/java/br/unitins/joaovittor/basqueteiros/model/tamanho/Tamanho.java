package br.unitins.joaovittor.basqueteiros.model.tamanho;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import jakarta.persistence.Entity;

@Entity
public class Tamanho extends DefaultEntity {

    private Integer numero;
    private Integer qntEstoque;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getQntEstoque() {
        return qntEstoque;
    }

    public void setQntEstoque(Integer qntEstoque) {
        if (qntEstoque < 0) {
            throw new IllegalArgumentException("Quantidade de estoque não pode ser negativa");
        }

        this.qntEstoque = qntEstoque;
    }

}
