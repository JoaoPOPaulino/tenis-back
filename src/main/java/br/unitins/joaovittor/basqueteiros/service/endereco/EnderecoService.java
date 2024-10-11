package br.unitins.joaovittor.basqueteiros.service.endereco;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.dto.endereco.EnderecoResponseDTO;

public interface EnderecoService {
    List<EnderecoResponseDTO> findAll();
    EnderecoResponseDTO findById(Long id);
    void create(EnderecoDTO enderecoDTO);
    void update(Long id, EnderecoDTO enderecoDTO);
    void delete(Long id);
}
