package br.unitins.joaovittor.basqueteiros.dto.endereco;

import br.unitins.joaovittor.basqueteiros.dto.cidade.CidadeResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.endereco.Endereco;

public record EnderecoResponseDTO(
        Long id,
        String cep,
        String quadra,
        String rua,
        String numero,
        String complemento,
        CidadeResponseDTO cidade,
        boolean principal,
        boolean ativo) {

    public static EnderecoResponseDTO valueOf(Endereco endereco) {
        return new EnderecoResponseDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getQuadra(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                CidadeResponseDTO.valueOf(endereco.getCidade()),
                endereco.isPrincipal(),
                endereco.isAtivo()
        );
    }
}
