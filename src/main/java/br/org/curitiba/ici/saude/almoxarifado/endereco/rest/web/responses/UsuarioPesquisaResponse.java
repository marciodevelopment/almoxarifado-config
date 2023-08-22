package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.responses;

import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
public class UsuarioPesquisaResponse {
  private String nmUsuario;
  private String nmMae;
  private SexoType idSexo;
  private String dsEmail;
  private Integer cdUsuario;

}
