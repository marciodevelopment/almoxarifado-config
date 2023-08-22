package br.org.curitiba.ici.saude.almoxarifado.common.entity.type;

import br.org.curitiba.ici.saude.almoxarifado.common.BaseEnum;
import br.org.curitiba.ici.saude.almoxarifado.common.EnumUtil;



public enum SituacaoType implements BaseEnum {
  ATIVO(1, "Nacional"), INATIVO(2, "Importado");

  private final int id;
  private final String description;

  private SituacaoType(int id, String description) {
    this.id = id;
    this.description = description;
  }

  public static SituacaoType toType(Integer id) {
    return EnumUtil.toType(id, SituacaoType.class);
  }

  @Override
  public Integer getId() {
    return this.id;
  }

  @Override
  public String getDescription() {
    return this.description;
  }
}
