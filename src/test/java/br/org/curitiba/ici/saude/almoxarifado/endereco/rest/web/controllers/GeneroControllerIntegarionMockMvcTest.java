package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.controllers;


import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.GeneroEntity;
import br.org.curitiba.ici.saude.almoxarifado.endereco.repositories.GeneroRepository;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.GeneroAtualizacaoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.GeneroNovoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

@Sql({"/db/init-script2.sql"})
@ActiveProfiles("test-pg")
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class GeneroControllerIntegarionMockMvcTest {

  private Integer cdGeneroTest = 1;
  @Autowired
  private MockMvc mockMvc;
  private String url = "/generos";
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private GeneroRepository generoRepository;

  @BeforeEach
  void beforeEach() {

  }

  @Test
  void deveSalvarUmNovoGenero() throws Exception {
    GeneroNovoRequest request = new GeneroNovoRequest();
    request.setDsGenero("novo genero");
    request.setSgGenero("transsex");
    String cdGenero = this.mockMvc.perform(
            post(url).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andDo(print())
        .andReturn().getResponse().getContentAsString();
    assertTrue(generoRepository.findById(Integer.parseInt(cdGenero)).isPresent());
  }

  @Test
  void deveAtualizarUmGenero() throws Exception {
    GeneroAtualizacaoRequest request = new GeneroAtualizacaoRequest();
    request.setDsGenero("novo genero editado");
    request.setSgGenero("transsex editado");
    String urlPut = url + "/" + cdGeneroTest;
    ResultActions response = this.mockMvc.perform(
            put(urlPut).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andDo(print());
    Optional<GeneroEntity> possivelGenero = generoRepository.findById(
        cdGeneroTest);
    assertTrue(possivelGenero.isPresent());
    assertEquals(possivelGenero.get().getDsGenero(),
        request.getDsGenero());
    assertEquals(possivelGenero.get().getSgGenero(),
        request.getSgGenero());
  }

  @Test
  void deveRetornarUmGenero() throws Exception {
    String urlGet = url + "/" + cdGeneroTest;
    this.mockMvc.perform(get(urlGet))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.cdGenero", is(cdGeneroTest)));
  }

  @Test
  void deveEncontrarUmaGeneroParaPrimeiraPaginaDeTamanho1() throws Exception {
    this.mockMvc.perform(get(this.url).queryParam("page", "0")
            .queryParam("size", "1"))
        .andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$.size", is(1)));
  }

  @Test
  void deveDeletarUmGenero() throws Exception {
    String urlDelete = url + "/" + cdGeneroTest;
    this.mockMvc.perform(delete(urlDelete))
        .andExpect(status().isOk())
        .andDo(print());
  }
}

