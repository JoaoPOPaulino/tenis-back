package br.unitins.joaovittor.basqueteiros.Basqueteira.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Basqueteira.dto.BasqueteiraDTO;
import br.unitins.joaovittor.basqueteiros.Basqueteira.dto.BasqueteiraResponseDTO;
import jakarta.validation.Valid;

public interface BasqueteiraService {
    
    public BasqueteiraResponseDTO create(@Valid BasqueteiraDTO dto);
    public void update(Long id, BasqueteiraDTO dto);
    public boolean delete(Long id);
    public List<BasqueteiraResponseDTO> findAll();
    public BasqueteiraResponseDTO findById(Long id);
    public List<BasqueteiraResponseDTO> findByNome(String nome);
}
