package br.unitins.joaovittor.basqueteiros.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos2.basqueteiros.Basqueteira.dto.BasqueteiraDTO;
import br.unitins.topicos2.basqueteiros.Basqueteira.dto.BasqueteiraResponseDTO;
import br.unitins.topicos2.basqueteiros.Basqueteira.service.BasqueteiraService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class BasqueteiraResourceTest {
    
    @Inject
    BasqueteiraService service;

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindAll(){

        given()
        .when()
            .get("/basqueteiras")
        .then()
            .statusCode(200)
            .body("id", not(2));

    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindById() {
        given()
        .when()
            .get("/basqueteiras/search/id/1")
        .then()
            .statusCode(404);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindByNome() {
        given()
        .when()
            .get("/basqueteiras/search/nome/qualquerNome")
        .then()
            .statusCode(404);
        
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testCreate(){

        BasqueteiraDTO dto = new BasqueteiraDTO("basqueteiraTop", "desc", 100d, 2, 15, 150d, 300d, 1l, 2l, 5l);
        
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/basqueteiras")
        .then()
            .statusCode(200)
            .body("peso", is(100f));

    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testUpdate(){

        BasqueteiraDTO dto = new BasqueteiraDTO("basqueteiraTop", "desc", 100d, 2, 15, 150d, 300d, 1l, 2l, 5l);
        
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/basqueteiras/1")
        .then()
            .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testDelete(){

        BasqueteiraDTO dto = new BasqueteiraDTO("basqueteiraLendaria", "desc", 100d, 2, 15, 150d, 300d, 1l, 2l, 5l);
        
        BasqueteiraResponseDTO response = service.create(dto);

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/basqueteiras/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
    
}
