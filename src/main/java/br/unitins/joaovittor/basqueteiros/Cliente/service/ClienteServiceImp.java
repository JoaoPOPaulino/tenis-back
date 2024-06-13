package br.unitins.joaovittor.basqueteiros.Cliente.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteResponseDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteSaldoUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClientePasswordUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.dto.ClienteUsernameUpdateDTO;
import br.unitins.joaovittor.basqueteiros.Cliente.model.Cliente;
import br.unitins.joaovittor.basqueteiros.Cliente.repository.ClienteRepository;
import br.unitins.joaovittor.basqueteiros.Endereco.dto.EnderecoDTO;
import br.unitins.joaovittor.basqueteiros.Endereco.model.Endereco;
import br.unitins.joaovittor.basqueteiros.Hash.service.HashService;
import br.unitins.joaovittor.basqueteiros.PessoaFisica.model.PessoaFisica;
import br.unitins.joaovittor.basqueteiros.PessoaFisica.repository.PessoaFisicaRepository;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.model.Usuario;
import br.unitins.joaovittor.basqueteiros.Usuario.repository.UsuarioRepository;
import br.unitins.joaovittor.basqueteiros.Usuario.service.UsuarioService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.xml.bind.ValidationException;

@ApplicationScoped
public class ClienteServiceImp implements ClienteService {
    
    @Inject
    ClienteRepository repository;

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Inject
    JsonWebToken jwt;

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

        cliente.setSaldo(0d);
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
    public void update(Long id, ClienteDTO dto) throws ValidationException {

        Usuario usuario = repository.findById(id).getPessoaFisica().getUsuario();
        if(usuario != null){
            usuario.setUsername(dto.username());
            // fazer hash da nova senha
            usuario.setPassword(hashService.getHashSenha(dto.senha()));
        } else {
            throw new ValidationException("Cliente inexistente");
        }

        PessoaFisica pf = repository.findById(id).getPessoaFisica();
        if(pf != null){
            pf.setNome(dto.nome());
            pf.setTelefone(dto.telefone());
            pf.setDataNascimento(LocalDate.of(dto.anoNasc(),dto.mesNasc(),dto.diaNasc()));
            pf.setCpf(dto.cpf());
            pf.setUsuario(usuario);
        } else {
            throw new ValidationException("Cliente inexistente");
        }
        
        Cliente cliente = repository.findById(id);

        if(cliente != null){
            cliente.setSaldo(0d);
            cliente.setPessoaFisica(pf);

            // Assim apagaria a lista antiga:
            // cliente.setListaEndereco(new ArrayList<>());

            cliente.setListaEndereco(cliente.getListaEndereco());
            for(EnderecoDTO endereco : dto.listaEndereco()){
                Endereco end = new Endereco();
                end.setCep(endereco.cep());
                end.setRua(endereco.rua());
                end.setComplemento(endereco.complemento());
                cliente.getListaEndereco().add(end);
            }
        } else {
            throw new ValidationException("Cliente inexistente");
        }
        
    }

    @Transactional
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

    @Override
    public UsuarioResponseDTO login(String username, String senha) {
        Cliente cliente = repository.findByUsernameAndSenha(username, senha);
        // verificar se existe ou não
        if(cliente != null)
            return UsuarioResponseDTO.valueof(cliente.getPessoaFisica());
        return null;
    }

    @Override
    @Transactional
    public void updateSaldo(ClienteSaldoUpdateDTO dto){
        
        Usuario usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Cliente cliente = repository.findByIdUsuario(usuario.getId());

        cliente.setSaldo(cliente.getSaldo() + dto.acrescimoSaldo());
        repository.persist(cliente);
    }

    @Override
    @Transactional
    public void updateUsuarioPassword(ClientePasswordUpdateDTO passwordUpdateDTO) {

        Usuario usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Cliente cliente = repository.findByIdUsuario(usuario.getId());
        if (usuario == null || cliente == null) {
            throw new InternalError();
        }

        if(usuario.getPassword().equals(hashService.getHashSenha(passwordUpdateDTO.oldPassword()))){
            usuario.setDataAlteracao(LocalDateTime.now());
            usuario.setPassword(hashService.getHashSenha(passwordUpdateDTO.newPassword()));
            usuarioService.update(usuario);
        }
    }

    @Override
    @Transactional
    public void updateUsuarioUsername(ClienteUsernameUpdateDTO usernameUpdateDTO) {
        Usuario usuario = usuarioRepository.findById(Long.valueOf(jwt.getClaim("userId").toString()));
        Cliente cliente = repository.findByIdUsuario(usuario.getId());
        if (usuario == null || cliente == null) {
            throw new InternalError();
        }
        cliente.getPessoaFisica().getUsuario().setUsername(usernameUpdateDTO.newUsername());
        usuarioService.update(cliente.getPessoaFisica().getUsuario());
        repository.persist(cliente);
    }
}
