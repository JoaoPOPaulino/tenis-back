package br.unitins.joaovittor.basqueteiros.model.avaliacao;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import jakarta.persistence.ManyToOne;

public class Avaliacao extends DefaultEntity {

    @ManyToOne
    private Tenis tenis;

    private String conteudo;

    public Tenis getTenis() {
        return tenis;
    }

    public void setTenis(Tenis tenis) {
        this.tenis = tenis;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

}
