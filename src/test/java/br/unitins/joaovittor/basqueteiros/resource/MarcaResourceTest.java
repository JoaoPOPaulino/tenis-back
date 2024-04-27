package br.unitins.joaovittor.basqueteiros.resource;

import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaDTO;
import br.unitins.joaovittor.basqueteiros.Marca.dto.MarcaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Marca.service.MarcaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class MarcaResourceTest {
    
    @Inject
    MarcaService service;

    @Test
    public void testFindAll(){

        given()
        .when()
            .get("/marcas")
        .then()
            .statusCode(200)
            .body("nome", hasItem(is("nike")));

    }

    @Test
    public void testFindById() {
        given()
        .when()
            .get("/marcas/search/id/1")
        .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNome(){
        given()
        .when()
            .get("marcas/search/nome/n")
        .then()
            .statusCode(200)
            .body("nome", hasItem(is("nike")));
    }

    @Test
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
