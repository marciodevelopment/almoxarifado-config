package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.mappers;

import java.util.Collection;
import java.util.stream.Stream;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import br.org.curitiba.ici.saude.almoxarifado.common.PageResponse;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.GeneroEntity;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view.GeneroPesquisaView;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.GeneroAtualizacaoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests.GeneroNovoRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses.GeneroPesquisaResponse;
import br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses.GeneroResponse;

@Mapper(componentModel = "spring",
    implementationPackage = "br.org.curitiba.ici.saude.almoxarifado.endereco.rest.mappers.impl")
public abstract class GeneroMapper {
  public GeneroEntity toEntity(GeneroNovoRequest request) {
    return new GeneroEntity(request.getDsGenero(), request.getSgGenero());
  }

  public GeneroEntity toEntity(GeneroEntity genero, GeneroAtualizacaoRequest request) {
    genero.atualizar(request.getDsGenero(), request.getSgGenero());
    return genero;
  }

  public abstract GeneroResponse toResponse(GeneroEntity genero);

  public abstract Collection<GeneroPesquisaResponse> toResponse(Stream<GeneroPesquisaView> generos);

  public PageResponse<GeneroPesquisaResponse> toResponse(
      Page<GeneroPesquisaView> resultadosPesquisa) {
    return new PageResponse<>(this.toResponse(resultadosPesquisa.get()), resultadosPesquisa);
  }

}
