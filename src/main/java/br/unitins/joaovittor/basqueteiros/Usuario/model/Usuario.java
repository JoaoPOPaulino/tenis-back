package br.unitins.joaovittor.basqueteiros.Usuario.model;

import java.time.LocalDate;

import br.unitins.joaovittor.basqueteiros.DefaultEntity.model.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Usuario extends DefaultEntity{

    //tirar (botar em Pessoa)
    @Column(name = "nome")
    private String nome;

    //mudar p username
    @Column(name = "login")
    private String login;

    // add senha

    // ver o que fzr com esse email
    @Column(name = "email")
    private String email;

    // vai p/ Classe (Pessoa)
    @Column(name = "telefone")
    private String telefone;

    // vai p/ Classe (PessoaFisica)
    @Column(name = "cpf", unique = true)
    private String cpf;

    // vai p/ Classe (Pessoa)
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
}
