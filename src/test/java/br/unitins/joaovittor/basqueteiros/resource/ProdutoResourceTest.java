package br.unitins.joaovittor.basqueteiros.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.topicos2.basqueteiros.Produto.dto.ProdutoDTO;
import br.unitins.topicos2.basqueteiros.Produto.dto.ProdutoResponseDTO;
import br.unitins.topicos2.basqueteiros.Produto.service.ProdutoService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ProdutoResourceTest {
    
    @Inject
    ProdutoService service;

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindAll(){

        given()
        .when()
            .get("/produtos")
        .then()
            .statusCode(200)
            .body("id", not(2));

    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindById() {
        given()
        .when()
            .get("/produtos/search/id/1")
        .then()
            .statusCode(404);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindByNome() {
        given()
        .when()
            .get("/produtos/search/nome/teste")
        .then()
            .statusCode(200);
        
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testCreate(){

        ProdutoDTO dto = new ProdutoDTO("produto", "desc", 10, 100d, 200d, 1l, 2l);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/produtos")
        .then()
            .statusCode(200);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testUpdate(){

        ProdutoDTO dto = new ProdutoDTO("naosei", "desc", 15, 100d, 200d, 1l, 2l);

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/produtos/1")
        .then()
            .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testDelete(){

        ProdutoResponseDTO response = service.create(new ProdutoDTO("testt", "desc2", 22, 105d, 250d, 1l, 2l));

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/produtos/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }
}
