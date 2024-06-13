package br.unitins.joaovittor.basqueteiros.Produto.resource;

import java.util.List;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.joaovittor.basqueteiros.Cor.resource.CorResource;
import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoDTO;
import br.unitins.joaovittor.basqueteiros.Produto.dto.ProdutoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Produto.service.ProdutoFileServiceImpl;
import br.unitins.joaovittor.basqueteiros.Produto.service.ProdutoService;
import br.unitins.joaovittor.basqueteiros.form.ImageForm;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/produtos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProdutoResource {
    
    @Inject
    public ProdutoService service;

    @Inject
    public ProdutoFileServiceImpl fileService;

    private static final Logger LOG = Logger.getLogger(CorResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(ProdutoDTO dto){
        return Response.ok(service.create(dto)).build();
    }

    @GET
    @RolesAllowed({"Cliente", "Funcionario"})
    public Response findAll(){
        return Response.ok(service.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    @RolesAllowed({"Cliente", "Funcionario"})
    public Response findByNome(@PathParam("nome") String nome) {
        List<ProdutoResponseDTO> lista = service.findByNome(nome);
        if(lista != null)
            return Response.ok(lista).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Funcionario")
    public Response findById(@PathParam("id") long id){
        return Response.ok(service.findById(id)).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed("Funcionario")
    public Response update(@PathParam("id") Long id, ProdutoDTO dto) {
        service.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("Funcionario")
    public Response delete(@PathParam("id") Long id) {
        if(service.delete(id))
            return Response.status(Status.NO_CONTENT).build();
        return Response.status(Status.NOT_FOUND).build();
    }

    @PATCH
    @Path("/{id}/imagem/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed("Funcionario")
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        fileService.upload(id, form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @RolesAllowed({"Cliente", "Funcionario"})
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        return Response.ok(fileService.download(nomeImagem))
               .header("Content-Disposition", "attachment;filename=" + nomeImagem).build();
    }

}
