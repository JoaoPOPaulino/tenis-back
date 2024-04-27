package br.unitins.joaovittor.basqueteiros.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.dto.TamanhoResponseDTO;
import br.unitins.joaovittor.basqueteiros.Tamanho.service.TamanhoService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class TamanhoResourceTest {
    
    @Inject
    TamanhoService service;

    @Test
    public void testFindAll(){

        given()
        .when()
            .get("/tamanhos")
        .then()
            .statusCode(200)
            .body("numeracao", hasItem(is(40)));

    }

    @Test
    public void testFindById() {
        given()
        .when()
            .get("/tamanhos/search/id/1")
        .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testFindByNumeracao() {
        given()
        .when()
            .get("/tamanhos/search/numeracao/37")
        .then()
            .statusCode(200)
            .body("id", hasItem(is(1)));
    }

    @Test
    public void testCreate(){
        TamanhoDTO dto = new TamanhoDTO(20, "15");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/tamanhos")
        .then()
            .statusCode(200)
            .body("id", is(8));
    }

    @Test
    public void testUpdate(){
        TamanhoDTO dto = new TamanhoDTO(25, "18");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/tamanhos/8")
        .then()
            .statusCode(204);
    }

    @Test
    public void testDelete(){
        
        TamanhoResponseDTO response = service.create(new TamanhoDTO(22, "16"));

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/tamanhos/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}
