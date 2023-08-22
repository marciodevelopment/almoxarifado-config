package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests;

import br.org.curitiba.ici.saude.almoxarifado.common.ParametrosPesquisaRequest;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

                                                                        @NoArgsConstructor
@Setter
@Getter
public class UsuarioPesquisaRequest extends ParametrosPesquisaRequest {
  private String     nmUsuario ;
  private String     nmMae ;
  private SexoType idSexo; 

}
