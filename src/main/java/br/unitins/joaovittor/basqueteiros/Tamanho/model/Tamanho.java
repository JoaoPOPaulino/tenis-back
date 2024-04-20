package br.unitins.joaovittor.basqueteiros.Tamanho.model;

import br.unitins.joaovittor.basqueteiros.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Tamanho extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numeracao")
    private Integer numeracao;

    @Column(name = "tamanho_em_cm")
    private String tamanhoEmCm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(Integer numeracao) {
        this.numeracao = numeracao;
    }

    public String getTamanhoEmCm() {
        return tamanhoEmCm;
    }

    public void setTamanhoEmCm(String tamanhoEmCm) {
        this.tamanhoEmCm = tamanhoEmCm;
    }

}
