package br.unitins.joaovittor.basqueteiros.resource;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.joaovittor.basqueteiros.Cor.dto.CorDTO;
import br.unitins.joaovittor.basqueteiros.Cor.dto.CorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Cor.service.CorService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class CorResourceTest {
    
    @Inject
    CorService service;

    @Test
    public void testFindAll(){

        given()
        .when()
            .get("/cores")
        .then()
            .statusCode(200)
            .body("nome", hasItem(is("branco")));

    }

    @Test
    public void testFindById() {
        given()
        .when()
            .get("/cores/search/id/1")
        .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome(){
        given()
        .when()
            .get("cores/search/nome/p")
        .then()
            .statusCode(200)
            .body("nome", hasItem(is("preto")));
    }

    @Test
    public void testCreate(){
        CorDTO dto = new CorDTO("cinza");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("cores/")
        .then()
            .statusCode(200)
            .body("id", is(3));
    }

    @Test
    public void testUpdate(){
        CorDTO dto = new CorDTO("amarelo");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("cores/3")
        .then()
            .statusCode(204);
    }

    @Test
    public void testDelete(){
        
        CorResponseDTO response = service.create(new CorDTO("vermelho"));

        given()
        .when()
            .pathParam("id", response.id())
            .delete("cores/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}
