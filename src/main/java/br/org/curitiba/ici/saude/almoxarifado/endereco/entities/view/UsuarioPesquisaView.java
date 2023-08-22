package br.org.curitiba.ici.saude.almoxarifado.endereco.entities.view;

import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioPesquisaView {
  private Integer cdUsuario;
  private String nmUsuario;
  private String nmMae;
  private SexoType idSexo;
  private String dsEmail;

}
