package br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types;

import br.org.curitiba.ici.saude.almoxarifado.common.BaseEnum;
import br.org.curitiba.ici.saude.almoxarifado.common.EnumUtil;



public enum SexoType implements BaseEnum {
  MASCULINO(1, "Masculino"), FEMININO(2, "Feminino");

  private final int id;
  private final String description;

  private SexoType(int id, String description) {
    this.id = id;
    this.description = description;
  }

  public static SexoType toType(Integer id) {
    return EnumUtil.toType(id, SexoType.class);
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
