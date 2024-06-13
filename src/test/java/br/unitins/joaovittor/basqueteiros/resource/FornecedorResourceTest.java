package br.unitins.joaovittor.basqueteiros.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorDTO;
import br.unitins.joaovittor.basqueteiros.Fornecedor.dto.FornecedorResponseDTO;
import br.unitins.joaovittor.basqueteiros.Fornecedor.service.FornecedorService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class FornecedorResourceTest {
    
    @Inject
    FornecedorService service;

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindAll(){

        given()
        .when()
            .get("/fornecedores")
        .then()
            .statusCode(200)
            .body("nomeEmpresa", hasItem(is("nike")));

    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindById() {
        given()
        .when()
            .get("/fornecedores/search/id/2")
        .then()
            .statusCode(200)
            .body("telefone", is("888"));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testFindByNomeEmpresa(){
        given()
        .when()
            .get("/fornecedores/search/nome_empresa/nike")
        .then()
            .statusCode(200)
            .body("telefone", hasItem(is("999")));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testCreate(){
        
        FornecedorDTO dto = new FornecedorDTO("testeNome", "testeempresa@gmail.com", "999999999999", "123");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .post("/fornecedores")
        .then()
            .statusCode(200)
            .body("id", is(3));
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testUpdate(){
        
        FornecedorDTO dto = new FornecedorDTO("update", "testeempresaupdate@gmail.com", "999999999999", "123");

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(dto)
        .when()
            .put("/fornecedores/3")
        .then()
            .statusCode(204);
    }

    @Test
    @TestSecurity(user = "tester", roles = "Funcionario")
    public void testDelete(){

        FornecedorDTO dto = new FornecedorDTO("testeDelet", "testeDelet@gmail.com", "999999999999", "123");

        FornecedorResponseDTO response = service.create(dto);

        given()
        .when()
            .pathParam("id", response.id())
            .delete("/fornecedores/{id}")
        .then()
            .statusCode(204);

        service.delete(response.id());
        assertNull(service.findById(response.id()));
    }

}
