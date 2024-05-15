package br.unitins.joaovittor.basqueteiros.Endereco.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoResponseDTO;
import jakarta.validation.Valid;

public interface EnderecoService {

    public EnderecoResponseDTO create(@Valid EnderecoDTO dto);
    public void update(Long id, EnderecoDTO dto);
    public boolean delete(Long id);
    public List<EnderecoResponseDTO> findAll();
    public EnderecoResponseDTO findById(Long id);
    public EnderecoResponseDTO findByCep(String cep);

} 