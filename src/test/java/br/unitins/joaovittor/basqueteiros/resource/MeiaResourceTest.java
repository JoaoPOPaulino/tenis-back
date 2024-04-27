package br.unitins.joaovittor.basqueteiros.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaDTO;
import br.unitins.joaovittor.basqueteiros.Meia.dto.MeiaResponseDTO;
import br.unitins.joaovittor.basqueteiros.Meia.service.MeiaService;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class MeiaResourceTest {
    
    @Inject
    MeiaService service;

    @Test
    public void testFindAll(){

        given()
        .when()
            .get("/meias")
        .then()
            .statusCode(200)
            .body("id", not(2));

    }

    @Test
    public void testFindById() {
        given()
        .when()
            .get("/meias/search/id/1")
        .then()
            .statusCode(404);
    }

    @Test
    public void testFindByNome() {
        given()
        .when()
            .get("/meias/search/nome/teste")
        .then()
            .statusCode(404);
        
    }

    @Test
    public void testCreate(){

        MeiaDTO dto = new MeiaDTO("meiaTeste", "descTeste", 10, 4, 20.0, 80.0, 1l, 2l, 1l);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/meias")
        .then()
            .statusCode(200)
            .body("id", is(1));
    }

    @Test
    public void testUpdate(){

        MeiaDTO dto = new MeiaDTO("meiaTestee", "descTestee", 15, 2, 25.0, 90.0, 2l, 1l, 1l);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/meias/1")
        .then()
            .statusCode(204);
    }

    @Test
    public void testDelete(){

        MeiaDTO dto = new MeiaDTO("meiadoida", "descdoida", 15, 2, 25.0, 90.0, 2l, 1l, 1l);

        MeiaResponseDTO response = service.create(dto);

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/meias/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}
