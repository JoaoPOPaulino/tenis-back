package br.unitins.joaovittor.basqueteiros.model.endereco;

import br.unitins.joaovittor.basqueteiros.dto.usuario.enderecoDTO.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;

import jakarta.persistence.Entity;

@Entity
public class Endereco extends DefaultEntity {

    private String estado;
    private String cidade;
    private String quadra;
    private String rua;
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
