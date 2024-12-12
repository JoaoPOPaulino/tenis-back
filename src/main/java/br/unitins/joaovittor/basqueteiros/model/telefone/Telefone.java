package br.unitins.joaovittor.basqueteiros.model.telefone;

import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Telefone extends DefaultEntity {

    @NotBlank(message = "DDD é obrigatório")
    @Pattern(regexp = "\\d{2}", message = "DDD deve ter 2 dígitos")
    @Column(length = 2, nullable = false)
    private String ddd;

    @NotBlank(message = "Número é obrigatório")
    @Pattern(regexp = "\\d{8,9}", message = "Número deve ter 8 ou 9 dígitos")
    @Column(length = 9, nullable = false)
    private String numero;

    public Telefone() {
    }

    public Telefone(String ddd, String numero) {
        this.ddd = ddd.replaceAll("[^0-9]", "");
        this.numero = numero.replaceAll("[^0-9]", "");
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd.replaceAll("[^0-9]", "");
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero.replaceAll("[^0-9]", "");
    }

    public String getNumeroFormatado() {
        return "(" + ddd + ") "
                + (numero.length() == 9
                ? numero.substring(0, 5) + "-" + numero.substring(5)
                : numero.substring(0, 4) + "-" + numero.substring(4));
    }
}
