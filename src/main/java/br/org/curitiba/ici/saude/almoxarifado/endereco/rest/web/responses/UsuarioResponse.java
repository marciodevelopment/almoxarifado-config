package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses;

import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
public class UsuarioResponse {
  private Integer cdUsuario;
  private String nmUsuario;
  private String nmMae;
  private String nmPai;
  private SexoType idSexo;

}
