package br.unitins.joaovittor.basqueteiros.Cliente.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteResponseDTO;
import jakarta.validation.Valid;

public interface ClienteService {
    
    public ClienteResponseDTO create(@Valid ClienteDTO dto);
    public void update(Long id, ClienteDTO dto);
    public boolean delete(Long id);
    public List<ClienteResponseDTO> findAll();
    public ClienteResponseDTO findById(Long id);
    public List<ClienteResponseDTO> findByNome(String nome);
    public ClienteResponseDTO findByUsername(String username);
    public ClienteResponseDTO findByCpf(String cpf);
}
