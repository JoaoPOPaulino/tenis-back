package br.unitins.joaovittor.basqueteiros.Tamanho.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoResponseDTO;
import jakarta.validation.Valid;

public interface TamanhoService {
    
    public TamanhoResponseDTO create(@Valid TamanhoDTO dto);
    public void update(Long id, TamanhoDTO dto);
    public void delete(Long id);
    public List<TamanhoResponseDTO> findAll();
    public TamanhoResponseDTO findById(Long id);
    public List<TamanhoResponseDTO> findByNumeracao(Integer numeracao);
}
