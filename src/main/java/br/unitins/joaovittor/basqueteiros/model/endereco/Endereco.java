package br.unitins.joaovittor.basqueteiros.model.endereco;

import br.unitins.joaovittor.basqueteiros.model.cidade.Cidade;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "id_cidade")
    private Cidade cidade;
    
    private String cep;

    private String quadra;

    private String rua;

    private String numero;

    private String complemento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Endereco() {
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

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
