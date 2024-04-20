package br.unitins.joaovittor.basqueteiros.Marca.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaResponseDTO;
import jakarta.validation.Valid;

public interface MarcaService {
    
    public MarcaResponseDTO create(@Valid MarcaDTO dto);
    public void update(Long id, MarcaDTO dto);
    public void delete(Long id);
    public List<MarcaResponseDTO> findAll();
    public MarcaResponseDTO findById(Long id);
    public List<MarcaResponseDTO> findByNome(String nome);
}
