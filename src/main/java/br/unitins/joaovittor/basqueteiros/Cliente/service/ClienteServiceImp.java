package br.unitins.joaovittor.basqueteiros.Cliente.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteResponseDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.model.Cliente;
import br.unitins.joaovittor.basqueteiros.Cliente.repository.ClienteRepository;
import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.Endereco.model.Endereco;
import br.unitins.joaovittor.basqueteiros.Hash.service.HashService;
import br.unitins.joaovittor.basqueteiros.PessoaFisica.model.PessoaFisica;
import br.unitins.joaovittor.basqueteiros.PessoaFisica.repository.PessoaFisicaRepository;
import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import br.unitins.joaovittor.basqueteiros.Usuario.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@ApplicationScoped
public class ClienteServiceImp implements ClienteService {
    
    @Inject
    ClienteRepository repository;

    @Inject
    HashService hashService;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @Override
    @Transactional
    public ClienteResponseDTO create(@Valid ClienteDTO dto) {
        
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        // fazer hash da senha
        usuario.setPassword(hashService.getHashSenha(dto.senha()));

        usuarioRepository.persist(usuario);



        PessoaFisica pf = new PessoaFisica();
        pf.setNome(dto.nome());
        pf.setTelefone(dto.telefone());
        pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
        pf.setCpf(dto.cpf());
        pf.setUsuario(usuario);

        pessoaFisicaRepository.persist(pf);


        Cliente cliente = new Cliente();

        cliente.setListaEndereco(new ArrayList<Endereco>());
        for(EnderecoDTO endereco : dto.listaEndereco()){
            Endereco end = new Endereco();
            end.setCep(endereco.cep());
            end.setRua(endereco.rua());
            end.setComplemento(endereco.complemento());
            cliente.getListaEndereco().add(end);
        }

        cliente.setSaldo(dto.saldo());
        cliente.setPessoaFisica(pf);

        repository.persist(cliente);
        
        return ClienteResponseDTO.valueof(cliente);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(Long id, ClienteDTO dto) {
        
        
    }

    @Override
    public List<ClienteResponseDTO> findAll() {
        return repository.findAll()
                                .stream()
                                .map(e -> ClienteResponseDTO.valueof(e)).toList();
    }

    @Override
    public ClienteResponseDTO findById(Long id) {

        Cliente cor = repository.findById(id);

        if(cor != null)
            return ClienteResponseDTO.valueof(repository.findById(id));
        return null;       
    }

    @Override
    public List<ClienteResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                         .stream()
                         .map(e -> ClienteResponseDTO.valueof(e)).toList();
    }

    @Override
    public ClienteResponseDTO findByUsername(String username) {

        Cliente cliente = repository.findByUsername(username);

        if(cliente != null)
            return ClienteResponseDTO.valueof(cliente);
        return null;       
    }

    @Override
    public ClienteResponseDTO findByCpf(String cpf) {

        Cliente cliente = repository.findByCpf(cpf);

        if(cliente != null)
            return ClienteResponseDTO.valueof(cliente);
        return null;       
    }
}
