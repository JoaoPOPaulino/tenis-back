package br.unitins.joaovittor.basqueteiros.dto.endereco;

import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;
import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        Long id,
        @NotBlank(message = "CEP é obrigatório")
        String cep,
        @NotBlank(message = "Quadra é obrigatório")
        String quadra,
        @NotBlank(message = "Rua é obrigatório")
        String rua,
        @NotBlank(message = "Número é obrigatório")
        String numero,
        @NotBlank(message = "Complemento é obrigatório")
        String complemento,
        CidadeResponseDTO cidade) {

    public static EnderecoDTO valueOf(Endereco endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                CidadeResponseDTO.valueOf(endereco.getCidade()));
    }
}
