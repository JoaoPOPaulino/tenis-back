package br.unitins.joaovittor.basqueteiros.Funcionario.service;

import java.time.LocalDate;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.dto.FuncionarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Funcionario.model.Funcionario;
import br.unitins.joaovittor.basqueteiros.Funcionario.repository.FuncionarioRepository;
import br.unitins.joaovittor.basqueteiros.Hash.service.HashService;
import br.unitins.joaovittor.basqueteiros.PessoaFisica.model.PessoaFisica;
import br.unitins.joaovittor.basqueteiros.PessoaFisica.repository.PessoaFisicaRepository;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import br.unitins.joaovittor.basqueteiros.Usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class FuncionarioServiceImp implements FuncionarioService {
    
    @Inject
    FuncionarioRepository repository;

    @Inject
    HashService hashService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    @Transactional
    public FuncionarioResponseDTO create(@Valid FuncionarioDTO dto) {
        
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setPassword(hashService.getHashSenha(dto.senha()));

        usuarioRepository.persist(usuario);


        PessoaFisica pf = new PessoaFisica();
        pf.setNome(dto.nome());
        pf.setTelefone(dto.telefone());
        pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
        pf.setCpf(dto.cpf());
        pf.setUsuario(usuario);

        pessoaFisicaRepository.persist(pf);


        Funcionario funcionario = new Funcionario();

        funcionario.setCodigoContrato(dto.codigoContrato());
        LocalDate dataAdmissao = LocalDate.of(dto.anoAdmissao(), dto.mesAdmissao(), dto.diaAdmissao());
        funcionario.setDataAdmissao(dataAdmissao);
        funcionario.setPessoaFisica(pf);

        repository.persist(funcionario);
        
        return FuncionarioResponseDTO.valueof(funcionario);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, FuncionarioDTO dto) {
        
        
    }

    // tive que abrir transactional p funcinar (???)
    @Override
    public List<FuncionarioResponseDTO> findAll() {
        return repository.findAll()
                                .stream()
                                .map(e -> FuncionarioResponseDTO.valueof(e)).toList();
    }

    @Override
    public FuncionarioResponseDTO findById(Long id) {

        Funcionario cor = repository.findById(id);

        if(cor != null)
            return FuncionarioResponseDTO.valueof(repository.findById(id));
        return null;       
    }

    @Override
    public List<FuncionarioResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                         .stream()
                         .map(e -> FuncionarioResponseDTO.valueof(e)).toList();
    }

    @Override
    public FuncionarioResponseDTO findByUsername(String username) {

        Funcionario funcionario = repository.findByUsername(username);

        if(funcionario != null)
            return FuncionarioResponseDTO.valueof(funcionario);
        return null;       
    }

    @Override
    public FuncionarioResponseDTO findByCpf(String cpf) {

        Funcionario funcionario = repository.findByCpf(cpf);

        if(funcionario != null)
            return FuncionarioResponseDTO.valueof(funcionario);
        return null;       
    }

    @Override
    public UsuarioResponseDTO login(String username, String senha) {
        Funcionario funcionario = repository.findByUsernameAndSenha(username, senha);
        // verificar se existe ou não
        if(funcionario != null)
            return UsuarioResponseDTO.valueof(funcionario.getPessoaFisica());
        return null;
    }
}
