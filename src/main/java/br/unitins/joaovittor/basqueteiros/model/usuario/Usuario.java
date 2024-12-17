package br.unitins.joaovittor.basqueteiros.model.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.unitins.joaovittor.basqueteiros.model.cartao.Cartao;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import br.unitins.joaovittor.basqueteiros.model.telefone.Telefone;
import br.unitins.joaovittor.basqueteiros.model.tipo_usuario.TipoUsuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario extends DefaultEntity {

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String login;

    @NotBlank
    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_usuario", nullable = false)
    private TipoUsuario tipoUsuario;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "usuario_telefone")
    private List<Telefone> telefones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "usuario_cartao")
    private List<Cartao> cartoes = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Objects.requireNonNull(nome, "Nome não pode ser nulo");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = Objects.requireNonNull(login, "Login não pode ser nulo");
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = Objects.requireNonNull(senha, "Senha não pode ser nula");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Objects.requireNonNull(email, "Email não pode ser nulo");
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = Objects.requireNonNull(tipoUsuario, "Tipo de usuário não pode ser nulo");
    }

    // Defensive copy getters for collections
    public List<Telefone> getTelefones() {
        return new ArrayList<>(telefones);
    }

    public void addTelefone(Telefone telefone) {
        if (telefone != null && !this.telefones.contains(telefone)) {
            this.telefones.add(telefone);
        }
    }

    public List<Cartao> getCartoes() {
        return new ArrayList<>(cartoes);
    }

    public void addCartao(Cartao cartao) {
        if (cartao != null && !this.cartoes.contains(cartao)) {
            this.cartoes.add(cartao);
        }
    }

    public List<Endereco> getEnderecos() {
        return new ArrayList<>(enderecos);
    }

    public void addEndereco(Endereco endereco) {
        if (endereco != null && !this.enderecos.contains(endereco)) {
            this.enderecos.add(endereco);
        }
    }

    public boolean isAdmin() {
        return tipoUsuario != null && tipoUsuario.isAdmin();
    }

    public void removerTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }

    public void removerCartao(Cartao cartao) {
        cartoes.remove(cartao);
    }

    public void removerEndereco(Endereco endereco) {
        enderecos.remove(endereco);
    }

    @Override
    public String toString() {
        return "Usuario{"
                + "id=" + getId()
                + ", nome='" + nome + '\''
                + ", email='" + email + '\''
                + ", tipoUsuario=" + tipoUsuario
                + '}';
    }
}
