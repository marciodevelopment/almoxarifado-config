package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Data
public class GeneroNovoRequest {
  @Size(min = 5, max = 255, message = "Genero")
  private String dsGenero;
  @Size(min = 5, max = 255, message = "Genero")
  private String sgGenero;

}
