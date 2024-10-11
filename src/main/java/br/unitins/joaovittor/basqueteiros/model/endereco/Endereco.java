package br.unitins.joaovittor.basqueteiros.model.endereco;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco extends DefaultEntity {

    @Column(name = "estado", length = 50, nullable = false)
    private String estado;

    @Column(name = "cidade", length = 100, nullable = false)
    private String cidade;

    @Column(name = "quadra", length = 50, nullable = true)
    private String quadra;

    @Column(name = "rua", length = 100, nullable = false)
    private String rua;

    @Column(name = "numero", nullable = true)
    private Integer numero;

    public Endereco() {

    }

    public Endereco(EnderecoDTO dto) {
        this.estado = dto.estado();
        this.cidade = dto.cidade();
        this.quadra = dto.quadra();
        this.quadra = dto.quadra();
        this.rua = dto.rua();
        this.numero = dto.numero();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getQuadra() {
        return quadra;
    }

    public void setQuadra(String quadra) {
        this.quadra = quadra;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
