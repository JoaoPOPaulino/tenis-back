package br.unitins.joaovittor.basqueteiros.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.dto.UsuarioResponseDTO;
import br.unitins.joaovittor.basqueteiros.Usuario.service.UsuarioService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class UsuarioResourceTest {
    
    @Inject
    UsuarioService service;

    @Test
    public void testFindAll(){

        given()
        .when()
            .get("/usuarios")
        .then()
            .statusCode(200)
            .body("login", hasItem(is("joo")));

    }

    @Test
    public void testFindById() {
        given()
        .when()
            .get("/usuarios/search/id/2")
        .then()
            .statusCode(200)
            .body("cpf", is("999.999.999-99"));
    }

    @Test
    public void testFindByNome(){
        given()
        .when()
            .get("/usuarios/search/nome/j")
        .then()
            .statusCode(200)
            .body("login", hasItem(is("joo")));
    }

    @Test
    public void testCreate(){

        UsuarioDTO dto = new UsuarioDTO(
        "teste", "test",
        "teste@gmail.com", "000",
        "555.555.555-00", 5, 5, 1955);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/usuarios")
        .then()
            .statusCode(200)
            .body("id", is(4));
    }

    @Test
    public void testUpdate(){
        UsuarioDTO dto = new UsuarioDTO(
        "teste2", "test2",
        "teste2@gmail.com", "000",
        "555.555.555-10", 10, 10, 1985);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/usuarios/4")
        .then()
            .statusCode(204);
    }

    @Test
    public void testDelete(){
        
        UsuarioResponseDTO response = service.create(new UsuarioDTO(
            "teste3", "test3",
            "teste2@gmail.com", "000",
            "555.555.555-30", 20, 5, 2020));

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/usuarios/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}
