package br.unitins.joaovittor.basqueteiros.Cliente.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cliente.model.Cliente;
import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoResponseDTO;

public record ClienteResponseDTO(
    Long id,
    String nome,
    String telefone,
    LocalDate dataNascimento,
    String cpf,
    Double saldo,
    String username,
    String senha,
    LocalDateTime dataCadastro,
    LocalDateTime dataAlteracao,
    List<EnderecoResponseDTO> listaEndereco
) {
    public static ClienteResponseDTO valueof(Cliente cliente){

        List<EnderecoResponseDTO> lista = cliente.getListaEndereco()
                                        .stream()
                                        .map(EnderecoResponseDTO::valueof)
                                        .toList();


        return new ClienteResponseDTO(cliente.getId(), 
                                cliente.getPessoaFisica().getNome(),
                                cliente.getPessoaFisica().getTelefone(),
                                cliente.getPessoaFisica().getDataNascimento(),
                                cliente.getPessoaFisica().getCpf(),
                                cliente.getSaldo(), 
                                cliente.getPessoaFisica().getUsuario().getUsername(),
                                cliente.getPessoaFisica().getUsuario().getPassword(),
                                cliente.getDataCadastro(), 
                                cliente.getDataAlteracao(),
                                lista);
    }
}
