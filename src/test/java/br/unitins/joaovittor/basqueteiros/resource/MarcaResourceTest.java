package br.unitins.joaovittor.basqueteiros.resource;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;

import br.unitins.topicos2.basqueteiros.Marca.dto.MarcaDTO;
import br.unitins.topicos2.basqueteiros.Marca.dto.MarcaResponseDTO;
import br.unitins.topicos2.basqueteiros.Marca.service.MarcaService;

import static org.junit.jupiter.api.Assertions.assertNull;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class MarcaResourceTest {
    
    @Inject
    MarcaService service;

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindAll(){

        given()
        .when()
            .get("/marcas")
        .then()
            .statusCode(200)
            .body("nome", hasItem(is("nike")));

    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindById() {
        given()
        .when()
            .get("/marcas/search/id/1")
        .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindByNome(){
        given()
        .when()
            .get("marcas/search/nome/n")
        .then()
            .statusCode(200)
            .body("nome", hasItem(is("nike")));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testCreate(){
        MarcaDTO dto = new MarcaDTO("puma");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/marcas")
        .then()
            .statusCode(200)
            .body("id", is(3));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testUpdate(){
        MarcaDTO dto = new MarcaDTO("under armor");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/marcas/3")
        .then()
            .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testDelete(){
        
        MarcaResponseDTO response = service.create(new MarcaDTO("mizuno"));

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/marcas/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}
