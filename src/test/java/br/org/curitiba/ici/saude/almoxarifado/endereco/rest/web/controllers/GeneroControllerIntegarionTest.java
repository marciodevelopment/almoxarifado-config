package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.controllers;



import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import br.org.curitiba.ici.saude.almoxarifado.AlmoxarifadoApplication;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view.GeneroPesquisaView;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.GeneroAtualizacaoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.GeneroNovoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses.GeneroResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.transaction.annotation.Transactional;


@Sql({"/db/init-script2.sql"})
@TestMethodOrder(OrderAnnotation.class)
@ActiveProfiles("test-pg")
@SpringBootTest(classes = AlmoxarifadoApplication.class,
    webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
class GeneroControllerIntegarionTest {
  @LocalServerPort
  private int port;

  private String url = "http://localhost:%s/generos";
  private static Integer cdGeneroCriado = null;

  @BeforeEach
  void beforeEach() {
    url = String.format(url, port);
  }

  @Order(1)
  @Test
  void deveSalvarUmNovoGenero() throws Exception {
    GeneroNovoRequest request = new GeneroNovoRequest();
    request.setDsGenero("novo genero");
    request.setSgGenero("transsex");
    Response httpResponse = given().contentType(ContentType.JSON).body(request).when().post(url);
    httpResponse.then().statusCode(HttpStatus.CREATED.value()).log().ifError();
    cdGeneroCriado = httpResponse.getBody().as(Integer.class).intValue();

  }

  @Order(2)
  @Test
  void deveAtualizarUmGenero() throws Exception {
    GeneroAtualizacaoRequest request = new GeneroAtualizacaoRequest();
    request.setDsGenero("novo genero editado");
    request.setSgGenero("transsex editado");
    String urlPut = url + "/" + cdGeneroCriado;
    given().contentType(ContentType.JSON).body(request).when().put(urlPut).then()
        .statusCode(HttpStatus.OK.value()).log().ifError();
  }

  @Order(3)
  @Test
  void deveRetornarUmGenero() throws Exception {
    String urlGet = url + "/" + cdGeneroCriado;
    Response httpResponse = given().contentType(ContentType.JSON).when().get(urlGet);
    httpResponse.then().statusCode(HttpStatus.OK.value()).log().ifError();
    GeneroResponse fabricanteResponse = httpResponse.getBody().as(GeneroResponse.class);
    assertEquals(cdGeneroCriado, fabricanteResponse.getCdGenero());
  }

  @Order(4)
  @Test
  void deveEncontrarUmaGeneroParaPrimeiraPaginaDeTamanho1() {
    List<GeneroPesquisaView> request =
        given().contentType(ContentType.JSON).when().queryParam("page", 0).queryParam("size", 1)
            .get(url).then().extract().jsonPath().get("content");
    Assertions.assertEquals(1, request.size());
  }

  @Order(5)
  @Test
  void deveDeletarUmGenero() throws Exception {
    String urlDelete = url + "/" + cdGeneroCriado;
    given().contentType(ContentType.JSON).when().delete(urlDelete).then()
        .statusCode(HttpStatus.OK.value()).log().ifError();
  }

}

