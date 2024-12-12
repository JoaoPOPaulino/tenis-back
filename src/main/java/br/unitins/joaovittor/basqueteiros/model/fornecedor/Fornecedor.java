package br.unitins.joaovittor.basqueteiros.model.fornecedor;

import java.util.ArrayList;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Fornecedor extends DefaultEntity {

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false, length = 50)
    private String nome;

    @NotBlank(message = "CNPJ é obrigatório")
    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "fornecedor_endereco",
            joinColumns = @JoinColumn(name = "id_fornecedor"),
            inverseJoinColumns = @JoinColumn(name = "id_endereco"))
    private List<Endereco> enderecos = new ArrayList<>();

    // Construtores
    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj != null ? cnpj.replaceAll("\\D", "") : null;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void addEndereco(Endereco endereco) {
        if (endereco != null) {
            this.enderecos.add(endereco);
        }
    }

    public void removeEndereco(Endereco endereco) {
        this.enderecos.remove(endereco);
    }
}
