package br.unitins.joaovittor.basqueteiros.model.estado;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Estado extends DefaultEntity {

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = true, length = 2)
    private String sigla;

    private boolean ativo = true;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
