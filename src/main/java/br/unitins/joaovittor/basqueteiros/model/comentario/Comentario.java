package br.unitins.joaovittor.basqueteiros.model.comentario;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Comentario extends DefaultEntity {

    private String texto;
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private Usuario usuario;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConteudo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
