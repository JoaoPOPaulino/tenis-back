package br.unitins.joaovittor.basqueteiros.Meia.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaDTO;
import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaResponseDTO;
import jakarta.validation.Valid;

public interface MeiaService {

    public MeiaResponseDTO create(@Valid MeiaDTO dto);
    public void update(Long id, MeiaDTO dto);
    public boolean delete(Long id);
    public List<MeiaResponseDTO> findAll();
    public MeiaResponseDTO findById(Long id);
    public List<MeiaResponseDTO> findByNome(String nome);
}
