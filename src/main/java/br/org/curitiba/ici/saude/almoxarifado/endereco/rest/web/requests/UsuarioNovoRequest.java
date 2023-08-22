package br.org.curitiba.ici.saude.almoxarifado.endereco.rest.web.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import br.org.curitiba.ici.saude.almoxarifado.common.web.UppercaseDeserializer;
import br.org.curitiba.ici.saude.almoxarifado.endereco.entities.types.SexoType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Data
public class UsuarioNovoRequest {
  @JsonDeserialize(using = UppercaseDeserializer.class)
  @NotNull
  @Size(min = 5, max = 255, message = "Nome")
  private String nmUsuario;
  @NotNull
  @Size(min = 5, max = 255, message = "Nome Mãe")
  private String nmMae;
  @JsonDeserialize(using = UppercaseDeserializer.class)
  private String nmPai;
  @NotNull(message = "Sexo")
  private SexoType idSexo;
  @NotNull
  @Size(min = 5, max = 255, message = "Nome Mãe")
  private String nrDocumento;
  @NotNull
  private Integer cdGenero;
  @NotEmpty
  @Email
  private String dsEmail;
}
