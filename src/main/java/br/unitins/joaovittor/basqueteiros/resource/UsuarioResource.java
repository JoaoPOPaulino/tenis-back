package br.unitins.joaovittor.basqueteiros.resource;

import java.time.LocalDate;
import java.util.List;

import br.unitins.joaovittor.basqueteiros.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.model.Usuario;
import br.unitins.joaovittor.basqueteiros.repository.UsuarioRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {

    @Inject
    public UsuarioRepository usuarioRepository;

    @POST
    @Transactional
    public UsuarioResponseDTO create(UsuarioDTO dto) {
        Usuario user = new Usuario();
        user.setNome(dto.nome());
        user.setEmail(dto.email());
        user.setCpf(dto.cpf());
        user.setTelefone(dto.telefone());
        user.setDataNascimento(LocalDate.of(dto.ano(), dto.mes(), dto.dia()));

        usuarioRepository.persist(user);

        return UsuarioResponseDTO.parse(user);
    }

    @GET
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(e -> UsuarioResponseDTO.parse(e)).toList();
    }

    @GET
    @Path("/search/nome/{nome}")
    public List<UsuarioResponseDTO> findByNome(@PathParam("nome") String nome) {
        return usuarioRepository.findByNome(nome).stream()
        .map(e -> UsuarioResponseDTO.parse(e)).toList();
    }

    @GET
    @Path("/search/nome/{cpf}")
    public List<UsuarioResponseDTO> findByCPF(@PathParam("cpf") String cpf) {
        return usuarioRepository.findByCPF(cpf).stream()
        .map(e -> UsuarioResponseDTO.parse(e)).toList();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public void update(@PathParam("id") Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id);

        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setCpf(dto.cpf());
        usuario.setTelefone(dto.telefone());
        LocalDate dataNasc = LocalDate.of(dto.ano(), dto.mes(), dto.dia());
        usuario.setDataNascimento(dataNasc == null ? dataNasc : null);

    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public void delete(@PathParam("id") Long id) {
        usuarioRepository.deleteById(id);
    }
}
