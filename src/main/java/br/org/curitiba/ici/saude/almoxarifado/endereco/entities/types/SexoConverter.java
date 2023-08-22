package br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types;


import jakarta.persistence.AttributeConverter;


public class SexoConverter implements AttributeConverter<SexoType, Integer> {

  @Override
  public Integer convertToDatabaseColumn(SexoType type) {
    if (type == null)
      return null;
    return type.getId();
  }

  @Override
  public SexoType convertToEntityAttribute(Integer id) {
    return SexoType.toType(id);
  }
}
