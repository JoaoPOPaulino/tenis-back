package br.unitins.joaovittor.basqueteiros.model.avaliacao;

import java.time.LocalDateTime;
import java.util.Objects;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.tenis.Tenis;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Avaliacao extends DefaultEntity {

    @NotNull(message = "O tênis é obrigatório")
    @ManyToOne
    @JoinColumn(name = "tenis_id", nullable = false)
    private Tenis tenis;

    @NotNull(message = "O usuário é obrigatório")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @NotBlank(message = "O conteúdo da avaliação é obrigatório")
    @Size(min = 10, max = 500, message = "O conteúdo deve ter entre 10 e 500 caracteres")
    @Column(nullable = false, length = 500)
    private String conteudo;

    @NotNull(message = "A nota é obrigatória")
    @Min(value = 1, message = "A nota mínima é 1")
    @Max(value = 5, message = "A nota máxima é 5")
    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;

    @Column(nullable = false)
    private boolean ativa = true;

    // Construtor padrão
    public Avaliacao() {
        this.dataAvaliacao = LocalDateTime.now();
    }

    // Getters e Setters com validações
    public Tenis getTenis() {
        return tenis;
    }

    public void setTenis(Tenis tenis) {
        this.tenis = Objects.requireNonNull(tenis, "Tênis não pode ser nulo");
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = Objects.requireNonNull(usuario, "Usuário não pode ser nulo");
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = Objects.requireNonNull(conteudo, "Conteúdo não pode ser nulo");
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = Objects.requireNonNull(nota, "Nota não pode ser nula");
        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e 5");
        }
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    // Equals e HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Avaliacao avaliacao = (Avaliacao) o;
        return Objects.equals(usuario, avaliacao.usuario)
                && Objects.equals(tenis, avaliacao.tenis)
                && Objects.equals(dataAvaliacao, avaliacao.dataAvaliacao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuario, tenis, dataAvaliacao);
    }

    @Override
    public String toString() {
        return "Avaliacao{"
                + "id=" + getId()
                + ", usuario=" + (usuario != null ? usuario.getId() : null)
                + ", tenis=" + (tenis != null ? tenis.getId() : null)
                + ", nota=" + nota
                + ", dataAvaliacao=" + dataAvaliacao
                + ", ativa=" + ativa
                + '}';
    }
}
