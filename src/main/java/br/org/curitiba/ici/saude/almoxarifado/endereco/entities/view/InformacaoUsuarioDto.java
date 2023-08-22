package br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view;

import br.org.curitiba.ici.saude.almoxarifado.common.entity.type.SituacaoType;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;

public interface InformacaoUsuarioDto {
  Integer getCdUsuario();

  String getNmMae();

  String getNmPai();

  Integer getIdSexo();

  Integer getIdSituacao();

  default SexoType getSexoType() {
    return SexoType.toType(this.getIdSexo());
  }

  default SituacaoType getDituacao() {
    return SituacaoType.toType(this.getIdSituacao());
  }
}
