package br.unitins.joaovittor.basqueteiros.model.cartao;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.tipo_cartao.TipoCartao;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cartao extends DefaultEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCartao tipoCartao;

    @Column(nullable = false, unique = true)
    private String numero;

    @Column(length = 3, nullable = false)
    private String cvv;

    @Column(nullable = false)
    private LocalDate validade;

    @Column(nullable = false)
    private String titular;

    @Column(nullable = false)
    private String cpf;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;

    private boolean ativo = true;

    public TipoCartao getTipoCartao() {
        return tipoCartao;
    }

    public void setTipoCartao(TipoCartao tipoCartao) {
        this.tipoCartao = tipoCartao;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
