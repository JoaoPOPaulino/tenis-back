package br.unitins.joaovittor.basqueteiros.Basqueteira.model;

import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;
import br.unitins.joaovittor.basqueteiros.Tamanho.model.Tamanho;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Basqueteira extends Produto{
    
    @ManyToOne
    @JoinColumn(name = "id_tamanho")
    private Tamanho tamanho;

    @Column(name = "peso")
    private Double peso;

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
}
