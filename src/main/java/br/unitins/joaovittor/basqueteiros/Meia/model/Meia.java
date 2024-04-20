package br.unitins.joaovittor.basqueteiros.Meia.model;

import br.unitins.joaovittor.basqueteiros.Cor.model.Cor;
import br.unitins.joaovittor.basqueteiros.Produto.model.Produto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Meia extends Produto{
    
    @Column(name = "qtd_pares")
    private Integer qtdPares;

    @ManyToOne
    @JoinColumn(name = "id_cor")
    private Cor cor;

    public Integer getQtdPares() {
        return qtdPares;
    }

    public void setQtdPares(Integer qtdPares) {
        this.qtdPares = qtdPares;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }
}
