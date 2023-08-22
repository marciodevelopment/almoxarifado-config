


package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.org.curitiba.ici.saude.almoxarifado.common.PageResponse;
import br.org.curitiba.ici.saude.almoxarifado.common.PaginationRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.mappers.UsuarioMapper;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.UsuarioAtualizacaoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.UsuarioNovoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.UsuarioPesquisaRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses.UsuarioPesquisaResponse;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses.UsuarioResponse;
import br.org.curitiba.ici.saude.almoxarifado.endereco.services.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
@RequestMapping("usuarios")
public class UsuarioController {
  private final UsuarioService usuarioService;
  private final UsuarioMapper usuarioMapper;


  @ResponseStatus(value = HttpStatus.CREATED)
  @PostMapping
  public Integer salvar(@RequestBody @Valid UsuarioNovoRequest request) {
    return usuarioService.salvar(usuarioMapper.toEntity(request)).getCdUsuario();
  }


  @PutMapping(path = "/{id}")
  public void atualizar(@RequestBody @Valid UsuarioAtualizacaoRequest request,
      @NotNull @PathVariable("id") Integer id) {
    usuarioService
        .atualizar(usuarioMapper.toEntity(usuarioService.buscarPorIdOuThrow(id), request));
  }

  @Transactional(readOnly = true)
  @GetMapping(path = "/{id}")
  public UsuarioResponse buscarPorId(@NotNull @PathVariable("id") Integer id) {
    return usuarioMapper.toResponse(usuarioService.buscarPorIdOuThrow(id));
  }

  @DeleteMapping(path = "/{id}")
  public void deletar(@NotNull @PathVariable("id") Integer id) {
    usuarioService.deletarPorId(id);
  }

  @Transactional(readOnly = true)
  @GetMapping
  public PageResponse<UsuarioPesquisaResponse> pesquisar(UsuarioPesquisaRequest request,
      PaginationRequest paginationRequest) {
    return usuarioMapper.toResponse(
        usuarioService.pesquisarView(paginationRequest.toPageable(), request.toParametros()));
  }

}

