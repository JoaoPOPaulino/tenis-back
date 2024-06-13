package br.unitins.joaovittor.basqueteiros.Basqueteira.model;

import br.unitins.joaovittor.basqueteiros.EnumTamanhoCano.model.TamanhoCano;
import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;
import br.unitins.joaovittor.basqueteiros.Tamanho.model.Tamanho;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Basqueteira extends Produto{
    
    @ManyToOne
    @JoinColumn(name = "id_tamanho")
    private Tamanho tamanho;

    @Column(name = "tamanho_cano")
    private TamanhoCano tamanhoCano;

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

    public TamanhoCano getTamanhoCano() {
        return tamanhoCano;
    }

    public void setTamanhoCano(TamanhoCano tamanhoCano) {
        this.tamanhoCano = tamanhoCano;
    }    
}
