package br.unitins.joaovittor.basqueteiros.model.telefone;


import br.unitins.joaovittor.basqueteiros.dto.usuario.telefoneDTO.TelefoneDTO;
import br.unitins.joaovittor.basqueteiros.model.defaultEntity.DefaultEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Telefone extends DefaultEntity {
    @NotBlank
    @Size(min = 2, max = 2)
    private String codigoArea;
    @NotBlank
    @Size(min = 8, max = 12)
    private String numero;

    public Telefone() {
    }

    public Telefone(TelefoneDTO dto) {
        this.codigoArea = dto.codigoArea();
        this.numero = dto.numero();
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

}
