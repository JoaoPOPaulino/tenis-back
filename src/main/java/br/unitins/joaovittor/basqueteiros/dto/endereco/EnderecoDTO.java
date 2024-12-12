package br.unitins.joaovittor.basqueteiros.dto.endereco;

public record EnderecoDTO(
        String cep,
        String quadra,
        String rua,
        String numero,
        String complemento,
        Long idCidade) {

}
