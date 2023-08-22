package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests;

import br.org.curitiba.ici.saude.almoxarifado.common.ParametrosPesquisaRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class GeneroPesquisaRequest extends ParametrosPesquisaRequest {
  private String dsGenero;
  private String sgGenero;

}
