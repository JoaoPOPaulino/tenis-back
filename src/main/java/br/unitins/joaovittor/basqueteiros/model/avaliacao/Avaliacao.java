package br.unitins.joaovittor.basqueteiros.model.avaliacao;

import java.time.LocalDateTime;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Avaliacao extends DefaultEntity {

    private String titulo; // Título da avaliação
    private String texto; // Conteúdo da avaliação
    private Integer nota; // Nota de 1 a 5
    private LocalDateTime dataCriacao; // Data de criação da avaliação

    @ManyToOne
    @JoinColumn(name = "id_usuarios")
    private Usuario usuario; // Usuário que fez a avaliação

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A nota deve ser entre 1 e 5.");
        }
        this.nota = nota;
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
}
