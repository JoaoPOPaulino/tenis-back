package br.unitins.joaovittor.basqueteiros.Funcionario.service;

import java.util.List;

import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioPasswordUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioUsernameUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

public interface FuncionarioService {
    
    public FuncionarioResponseDTO create(@Valid FuncionarioDTO dto);
    public void update(Long id, FuncionarioDTO dto) throws ValidationException;
    public void updateUsuarioPassword(FuncionarioPasswordUpdateDTO passwordUpdateDTO);
    public void updateUsuarioUsername(FuncionarioUsernameUpdateDTO usernameUpdateDTO);
    public boolean delete(Long id);
    public List<FuncionarioResponseDTO> findAll();
    public FuncionarioResponseDTO findById(Long id);
    public List<FuncionarioResponseDTO> findByNome(String nome);
    public FuncionarioResponseDTO findByUsername(String username);
    public FuncionarioResponseDTO findByCpf(String cpf);
    public UsuarioResponseDTO login(String username, String hashSenha);
}
